package com.app.module.user.module.login.domain

import android.content.Context
import androidx.annotation.UiContext
import androidx.compose.ui.text.input.TextFieldValue
import com.app.module.base.support.AppServices
import com.xiaojinzi.reactive.anno.IntentProcess
import com.xiaojinzi.reactive.template.domain.BusinessUseCase
import com.xiaojinzi.reactive.template.domain.BusinessUseCaseImpl
import com.xiaojinzi.support.activity_stack.ActivityStack
import com.xiaojinzi.support.annotation.PublishHotObservable
import com.xiaojinzi.support.annotation.StateHotObservable
import com.xiaojinzi.support.ktx.MutableInitOnceData
import com.xiaojinzi.support.ktx.NormalMutableSharedFlow
import com.xiaojinzi.support.ktx.sharedStateIn
import com.xiaojinzi.support.ktx.toStringItemDto
import com.xiaojinzi.tally.lib.res.ui.APP_ACTIVITY_FLAG_LOGIN
import com.xiaojinzi.tally.lib.res.ui.APP_ACTIVITY_FLAG_MAIN
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.isActive

sealed class LoginIntent {

    data class LoginByCheckCode(
        @UiContext val context: Context
    ) : LoginIntent()

    data class LoginByBindWx(
        @UiContext val context: Context
    ) : LoginIntent()

    data class LoginByWx(
        @UiContext val context: Context
    ) : LoginIntent()

    data class SendCheckCode(
        val usage: Usage,
    ) : LoginIntent() {
        enum class Usage(
            val str: String,
        ) {
            LOGIN(
                str = "login",
            ),
            BIND_WX(
                str = "bindWx",
            ),
        }

    }
}

interface LoginUseCase : BusinessUseCase {

    /**
     * 微信绑定的 authId
     */
    val wxAuthIdInitData: MutableInitOnceData<String?>

    /**
     * 手机号焦点请求事件
     */
    @PublishHotObservable
    val phoneNumberRequestForceEvent: Flow<Unit>

    /**
     * 手机号
     */
    @StateHotObservable
    val phoneNumberStateOb: MutableStateFlow<TextFieldValue>

    /**
     * 验证码
     */
    @StateHotObservable
    val checkCodeStateOb: MutableStateFlow<TextFieldValue>

    /**
     * 可再次发送验证码的时间
     */
    @StateHotObservable
    val sendCheckCodeAvailableTimeStateOb: Flow<Long?>

    /**
     * 发送验证码的倒计时
     * 60, 59
     */
    @StateHotObservable
    val sendCheckCodeCountDownStateOb: Flow<Long?>

    /**
     * 是否可以发送验证码
     */
    @StateHotObservable
    val canSendCheckCodeStateOb: Flow<Boolean>

    /**
     * 是否可提交
     */
    @StateHotObservable
    val canSubmitStateOb: Flow<Boolean>

}

class LoginUseCaseImpl(
) : BusinessUseCaseImpl(), LoginUseCase {

    override val wxAuthIdInitData = MutableInitOnceData<String?>()

    override val phoneNumberRequestForceEvent = NormalMutableSharedFlow<Unit>()

    override val phoneNumberStateOb = MutableStateFlow(value = TextFieldValue(text = ""))

    override val checkCodeStateOb = MutableStateFlow(value = TextFieldValue(text = ""))

    override val sendCheckCodeAvailableTimeStateOb = MutableStateFlow<Long?>(value = null)

    @OptIn(ExperimentalCoroutinesApi::class)
    override val sendCheckCodeCountDownStateOb = sendCheckCodeAvailableTimeStateOb
        .flatMapLatest { targetTime ->
            if (targetTime == null) {
                flowOf(value = null)
            } else {
                var currentTime = System.currentTimeMillis()
                flow<Long?> {
                    while (currentCoroutineContext().isActive && targetTime > currentTime) {
                        val countDown = ((targetTime - currentTime) / 1000)
                        emit(value = countDown)
                        delay(timeMillis = 1000)
                        currentTime = System.currentTimeMillis()
                    }
                    emit(value = null)
                }
            }
        }
        .sharedStateIn(
            scope = scope,
            initValue = null,
        )

    override val canSendCheckCodeStateOb = sendCheckCodeCountDownStateOb
        .map {
            it == null
        }

    override val canSubmitStateOb = combine(
        phoneNumberStateOb,
        checkCodeStateOb,
    ) { name, password ->
        name.text.isNotBlank() && password.text.isNotBlank()
    }

    private suspend fun reset() {
        phoneNumberStateOb.emit(
            value = TextFieldValue(text = ""),
        )
        checkCodeStateOb.emit(
            value = TextFieldValue(text = ""),
        )
        sendCheckCodeAvailableTimeStateOb.emit(
            value = null,
        )
        phoneNumberRequestForceEvent.emit(
            value = Unit,
        )
    }

    @IntentProcess
    @BusinessUseCase.AutoLoading
    private suspend fun sendCheckCode(
        intent: LoginIntent.SendCheckCode,
    ) {
        val canSend = canSendCheckCodeStateOb.first()
        if (!canSend) {
            return
        }
        val phoneNumber = phoneNumberStateOb.first().text
        if (phoneNumber.isEmpty()) {
            tip(
                content = "手机号不能为空".toStringItemDto(),
            )
            return
        }
        // 模拟登录
        delay(1000)
        sendCheckCodeAvailableTimeStateOb.emit(
            value = System.currentTimeMillis() + 1000 * 60,
        )
        tip(
            content = "发送成功".toStringItemDto(),
        )
    }

    private suspend fun login(
        @UiContext context: Context,
        loginAction: suspend () -> Unit,
    ) {
        try {
            loginAction.invoke()
            tip(content = "登录成功".toStringItemDto())
            val mainView = ActivityStack
                .first {
                    it.hasFlag(flag = APP_ACTIVITY_FLAG_MAIN)
                }
            if (mainView == null) {
                // 去主界面
            } else {
                ActivityStack.finish {
                    it.hasFlag(flag = APP_ACTIVITY_FLAG_LOGIN)
                }
            }
        } catch (e: Exception) {
            throw e
        }
    }

    @IntentProcess
    @BusinessUseCase.AutoLoading
    private suspend fun loginByCheckCode(
        intent: LoginIntent.LoginByCheckCode,
    ) {
        login(
            context = intent.context,
        ) {
            val phoneNumber = phoneNumberStateOb.first().text
            val checkCode = checkCodeStateOb.first().text
            // 验证码登录
            confirmDialog(
                content = "phoneNumber = $phoneNumber\ncheckCode = $checkCode".toStringItemDto(),
            )
        }
    }

    @IntentProcess
    @BusinessUseCase.AutoLoading
    private suspend fun loginByBindWx(
        intent: LoginIntent.LoginByBindWx,
    ) {
        val wxAuthId = wxAuthIdInitData.awaitValue()
        if (wxAuthId != null) {
            login(
                context = intent.context,
            ) {
                val phoneNumber = phoneNumberStateOb.first().text
                val checkCode = checkCodeStateOb.first().text
                confirmDialog(
                    content = "wxAuthId = $wxAuthId\nphoneNumber = $phoneNumber\ncheckCode = $checkCode".toStringItemDto(),
                )
            }
        }
    }

    @IntentProcess
    @BusinessUseCase.AutoLoading
    private suspend fun loginByWx(
        intent: LoginIntent.LoginByWx,
    ) {
        login(
            context = intent.context,
        ) {
            AppServices
                .userSpi
                .loginByWx()
        }
    }

}

