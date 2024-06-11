package com.app.module.user.spi

import com.app.module.base.spi.UserSpi
import com.app.module.base.support.AppRouterUserApi
import com.app.module.base.support.AppServices
import com.app.module.base.support.isInstallWx
import com.xiaojinzi.component.anno.ServiceAnno
import com.xiaojinzi.component.impl.routeApi
import com.xiaojinzi.module.common.base.spi.spObjectConverterPersistence
import com.xiaojinzi.module.common.base.spi.spPersistence
import com.xiaojinzi.support.activity_stack.ActivityStack
import com.xiaojinzi.support.ktx.AppScope
import com.xiaojinzi.support.ktx.LogSupport
import com.xiaojinzi.support.ktx.MutableSharedStateFlow
import com.xiaojinzi.support.ktx.orNull
import com.xiaojinzi.tally.lib.res.model.exception.NotLoggedInException
import com.xiaojinzi.tally.lib.res.model.exception.UserIdDoesNotMatchTheLastLoginException
import com.xiaojinzi.tally.lib.res.model.exception.WxNotInstallException
import com.xiaojinzi.tally.lib.res.model.user.UserInfoDto
import com.xiaojinzi.tally.lib.res.ui.APP_ACTIVITY_FLAG_LOGIN
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map

@ServiceAnno(UserSpi::class)
class UserSpiImpl : UserSpi {

    override val userTokenStateOb = MutableSharedStateFlow<String?>()
        .spPersistence(
            scope = AppScope,
            key = "userToken",
            def = null,
        )

    override val userTokenNow: String?
        get() = userTokenStateOb.value

    override val userInfoStateOb = MutableSharedStateFlow<UserInfoDto?>()
        .spObjectConverterPersistence(
            scope = AppScope,
            key = "userInfo",
            def = null,
        )

    override val isLoginStateOb = userInfoStateOb
        .map {
            it != null
        }

    override val latestUserIdStateOb = MutableSharedStateFlow<String?>()
        .spPersistence(
            scope = AppScope,
            key = "latestUserId",
            def = null,
        )

    override suspend fun requiredUserInfo(): UserInfoDto {
        return userInfoStateOb.firstOrNull() ?: throw NotLoggedInException()
    }

    override suspend fun requiredLastUserId(): String {
        return latestUserIdStateOb.firstOrNull() ?: throw NotLoggedInException()
    }

    override suspend fun loginByWx() {
        LogSupport.d(
            tag = UserSpi.TAG,
            content = "loginByWx start",
        )
        if (!isInstallWx()) {
            throw WxNotInstallException()
        }
    }

    private suspend fun afterLogin(
        userToken: String,
        userInfo: UserInfoDto,
    ) {
        val lastUserId = latestUserIdStateOb.firstOrNull().orNull()
        // 如果登录的和上次不同, 是不行的
        if (lastUserId != null && lastUserId != userInfo.id) {
            throw UserIdDoesNotMatchTheLastLoginException()
        }
        // 销毁一些使用了数据库的服务发现
        AppServices
            .destroySpiAboutTallyDatabase()
        userTokenStateOb.emit(
            value = userToken,
        )
        userInfoStateOb.emit(
            value = userInfo,
        )
        latestUserIdStateOb.emit(
            value = userInfo.id,
        )
    }

    override suspend fun clearUserInfo() {
        LogSupport.d(
            tag = UserSpi.TAG,
            content = "clearUserInfo start",
        )
        userTokenStateOb.emit(
            value = null,
        )
        userInfoStateOb.emit(
            value = null,
        )
        LogSupport.d(
            tag = UserSpi.TAG,
            content = "clearUserInfo end",
        )
    }

    override suspend fun logoutForBusinessLogic() {
        LogSupport.d(
            tag = UserSpi.TAG,
            content = "logoutForBusinessLogic start",
        )
        // 等待同步完成, 不想影响数据同步. 现在不会影响了. 管他成功失败呢
        /*AppServices
            .tallyDataSyncSpi
            .isSyncingStateOb
            .filter { !it }
            .first()*/
        userTokenStateOb.emit(
            value = null,
        )
        userInfoStateOb.emit(
            value = null,
        )
        latestUserIdStateOb.emit(
            value = null,
        )
        // 打开登录界面
        val topAct = ActivityStack.first { it.isAlive() }
        if (topAct == null) {
            // 打开登录界面
            AppRouterUserApi::class
                .routeApi()
                .toLoginViewInNewTaskSuspend()
            // 杀死所有界面, 除了登录界面
            ActivityStack.finish {
                it.noFlag(flag = APP_ACTIVITY_FLAG_LOGIN)
            }
        } else // 占位
        {
            // 打开登录界面
            AppRouterUserApi::class
                .routeApi()
                .toLoginViewSuspend(
                    context = topAct,
                )
            // 杀死所有界面, 除了登录界面
            ActivityStack.finish {
                it.noFlag(flag = APP_ACTIVITY_FLAG_LOGIN)
            }
        }
        LogSupport.d(
            tag = UserSpi.TAG,
            content = "logoutForBusinessLogic end",
        )
    }

}