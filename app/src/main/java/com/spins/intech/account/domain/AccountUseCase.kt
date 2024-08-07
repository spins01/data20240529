package com.spins.intech.account.domain


import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.text.TextUtils
import android.util.Log
import androidx.annotation.UiContext
import androidx.compose.foundation.ScrollState
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.TextUnit
import androidx.core.app.ActivityCompat
import com.app.module.base.bean.LoginInputStatus
import com.app.module.base.bean.SearchBean
import com.app.module.base.common.CommonInterface
import com.app.module.base.common.CommonListCallback
import com.app.module.base.common.CommonNothingCallback
import com.app.module.base.extension.APP_ACTIVITY_FLAG_ACCOUNT
import com.app.module.base.extension.SPINS_TOKEN
import com.app.module.base.extension.SPINS_USER
import com.app.module.base.extension.SharedPreferenceUtil
import com.google.accompanist.pager.PagerState
import com.spins.intech.account.view.AccountAct
import com.spins.intech.sipsample.ui.MainActivity
import com.xiaojinzi.component.impl.service.ServiceManager
import com.xiaojinzi.reactive.anno.IntentProcess
import com.xiaojinzi.reactive.template.domain.BusinessUseCase
import com.xiaojinzi.reactive.template.domain.BusinessUseCaseImpl
import com.xiaojinzi.reactive.template.domain.CommonUseCase
import com.xiaojinzi.reactive.template.domain.CommonUseCaseImpl
import com.xiaojinzi.support.activity_stack.ActivityStack
import com.xiaojinzi.support.annotation.ViewModelLayer
import com.xiaojinzi.support.ktx.toStringItemDto
import kotlinx.coroutines.flow.MutableStateFlow


sealed class AccountIntent {

    data object Submit : AccountIntent()

    data class MemberAccountFocusChange(@UiContext val context: Context, val isFocus: Boolean) :
        AccountIntent()

    data class TelephoneFocusChange(@UiContext val context: Context, val isFocus: Boolean) :
        AccountIntent()

    data class CreateTimeLeftFocusChange(@UiContext val context: Context, val isFocus: Boolean) :
        AccountIntent()

    data class CreateTimeRightFocusChange(@UiContext val context: Context, val isFocus: Boolean) :
        AccountIntent()

    data class RoleNameLeftFocusChange(@UiContext val context: Context, val isFocus: Boolean) :
        AccountIntent()

    data class RoleNameRightFocusChange(@UiContext val context: Context, val isFocus: Boolean) :
        AccountIntent()

    data class Logout(@UiContext val context: Context) : AccountIntent()
    data class Search(
        @UiContext val context: Context,
        val isSearchMore: Boolean
    ) : AccountIntent()

    data class Call(@UiContext val context: Context, val userName: String) : AccountIntent()
}

@ViewModelLayer
interface AccountUseCase : BusinessUseCase {
    val pagerState: MutableStateFlow<PagerState>
    val memberListScrollState: MutableStateFlow<ScrollState>
    val roleManagerScrollState: MutableStateFlow<ScrollState>
    val drawerState: MutableStateFlow<DrawerState>
    val memberAccount: MutableStateFlow<TextFieldValue>
    val telephone: MutableStateFlow<TextFieldValue>
    val memberAccountStatus: MutableStateFlow<LoginInputStatus>
    val telephoneStatus: MutableStateFlow<LoginInputStatus>


    val createTimeInputLeftValue: MutableStateFlow<TextFieldValue>
    val createTimeInputRightValue: MutableStateFlow<TextFieldValue>
    val createTimeInputLeftStatus: MutableStateFlow<LoginInputStatus>
    val createTimeInputRightStatus: MutableStateFlow<LoginInputStatus>
    val roleNameInputLeftValue: MutableStateFlow<TextFieldValue>
    val roleNameInputRightValue: MutableStateFlow<TextFieldValue>
    val roleNameInputLeftStatus: MutableStateFlow<LoginInputStatus>
    val roleNameInputRightStatus: MutableStateFlow<LoginInputStatus>

    val startTimeValue: MutableStateFlow<String>
    val endTimeValue: MutableStateFlow<String>


    val currentPage: MutableStateFlow<Int>
    val pageSize: MutableStateFlow<Int>
    val totalPage: MutableStateFlow<Int>
    val searchList: MutableStateFlow<List<SearchBean?>>
}

@ViewModelLayer
class AccountUseCaseImpl(
    private val commonUseCase: CommonUseCase = CommonUseCaseImpl(),
) : BusinessUseCaseImpl(
    commonUseCase = commonUseCase,
), AccountUseCase {
    override val pagerState: MutableStateFlow<PagerState> =
        MutableStateFlow<PagerState>(PagerState())
    override val memberListScrollState: MutableStateFlow<ScrollState> =
        MutableStateFlow<ScrollState>(
            ScrollState(initial = 0)
        )
    override val roleManagerScrollState: MutableStateFlow<ScrollState> =
        MutableStateFlow<ScrollState>(ScrollState(initial = 0))
    override val drawerState: MutableStateFlow<DrawerState> =
        MutableStateFlow<DrawerState>(DrawerState(initialValue = DrawerValue.Closed))
    override val memberAccount: MutableStateFlow<TextFieldValue> =
        MutableStateFlow(TextFieldValue())

    override val telephone: MutableStateFlow<TextFieldValue> = MutableStateFlow(TextFieldValue())
    override val memberAccountStatus: MutableStateFlow<LoginInputStatus> =
        MutableStateFlow(LoginInputStatus.NORMAL)

    override val telephoneStatus: MutableStateFlow<LoginInputStatus> =
        MutableStateFlow(LoginInputStatus.NORMAL)

    override val createTimeInputLeftValue: MutableStateFlow<TextFieldValue> =
        MutableStateFlow(TextFieldValue())
    override val createTimeInputRightValue: MutableStateFlow<TextFieldValue> =
        MutableStateFlow(TextFieldValue())
    override val createTimeInputLeftStatus: MutableStateFlow<LoginInputStatus> =
        MutableStateFlow(LoginInputStatus.NORMAL)
    override val createTimeInputRightStatus: MutableStateFlow<LoginInputStatus> =
        MutableStateFlow(LoginInputStatus.NORMAL)
    override val roleNameInputLeftValue: MutableStateFlow<TextFieldValue> =
        MutableStateFlow(TextFieldValue())
    override val roleNameInputRightValue: MutableStateFlow<TextFieldValue> =
        MutableStateFlow(TextFieldValue())
    override val roleNameInputLeftStatus: MutableStateFlow<LoginInputStatus> =
        MutableStateFlow(LoginInputStatus.NORMAL)
    override val roleNameInputRightStatus: MutableStateFlow<LoginInputStatus> =
        MutableStateFlow(LoginInputStatus.NORMAL)


    override val startTimeValue: MutableStateFlow<String> = MutableStateFlow("")
    override val endTimeValue: MutableStateFlow<String> = MutableStateFlow("")


    override val currentPage: MutableStateFlow<Int> = MutableStateFlow(0)
    override val totalPage: MutableStateFlow<Int> = MutableStateFlow(1)
    override val pageSize: MutableStateFlow<Int> = MutableStateFlow(30)
    override val searchList: MutableStateFlow<List<SearchBean?>> = MutableStateFlow(mutableListOf())


    @IntentProcess
    @BusinessUseCase.AutoLoading
    private suspend fun submit(intent: AccountIntent.Submit) {

    }

    @IntentProcess
    @BusinessUseCase.AutoLoading
    private suspend fun call(intent: AccountIntent.Call) {
        ServiceManager.get(CommonInterface::class)
            ?.call(intent.userName, object : CommonNothingCallback {
                override fun onSuccess() {
                    Log.i("马超", "拨打电话成功")
                    requestPermissions(intent.context as AccountAct)

                }

                override fun onError(errorMessage: String) {
                    tip(errorMessage.toStringItemDto())
                    Log.i("马超", "拨打电话睡觉:${errorMessage}")
//                    intent.context.startActivity(Intent(intent.context,MainActivity::class.java))
                    requestPermissions(intent.context as AccountAct)
                }
            })
    }

    /**
     * 检查权限
     */
    fun requestPermissions(activity: Activity?) {
        // Check if we have write permission
        if (PackageManager.PERMISSION_GRANTED != ActivityCompat.checkSelfPermission(
                activity!!,
                Manifest.permission.CAMERA
            )
            || PackageManager.PERMISSION_GRANTED != ActivityCompat.checkSelfPermission(
                activity!!,
                Manifest.permission.RECORD_AUDIO
            )
        ) {
            ActivityCompat.requestPermissions(
                activity!!,
                arrayOf<String>(Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO),
//                REQ_DANGERS_PERMISSION
                2
            )
        } else {
            activity.startActivity(Intent(activity, MainActivity::class.java))
        }
    }

    @IntentProcess
    @BusinessUseCase.AutoLoading
    private suspend fun search(intent: AccountIntent.Search) {
        if (intent.isSearchMore) {
            if (currentPage.value >= totalPage.value) {
                tip("no more data".toStringItemDto())
                return
            }
            visitNetOfSearch(isSearchMore = true)
        } else {
            visitNetOfSearch(isSearchMore = false)
        }
    }

    private var preSearchTag = ""
    private suspend fun visitNetOfSearch(isSearchMore: Boolean) {
        val currentSearchTag = "${startTimeValue.value}__${endTimeValue.value}__${memberAccount.value.text}"
        val isSearchChanged = !TextUtils.equals(currentSearchTag, preSearchTag)
        val mCurrentPage: Int = if (isSearchChanged) {
            currentPage.value = 1
            1
        } else {
            if (isSearchMore) {
                currentPage.value + 1
            } else {
                1
            }
        }
        preSearchTag = currentSearchTag
        Log.e("Turbo", "startTime:${startTimeValue.value}__endTime:${endTimeValue.value}")
        ServiceManager.get(CommonInterface::class)
            ?.search(
                memberAccount.value.text,
                mCurrentPage,
                pageSize.value,
                startTimeValue.value,
                endTimeValue.value,
                object :
                    CommonListCallback<SearchBean> {
                    override fun onSuccess(
                        list: List<SearchBean>
                    ) {
                        if (list.isNotEmpty()) {
                            currentPage.value = list[0].current_page
                            totalPage.value = list[0].total_pages
                            if (isSearchMore && !isSearchChanged) {
                                searchList.value += list
                            } else {
                                searchList.value = list
//                            searchList.value.clear()
//                            searchList.value +=list
                            }
                        }
                    }


                    override fun onError(errorMessage: String) {
                        tip(errorMessage.toStringItemDto())
                    }
                })
    }

    @IntentProcess
    @BusinessUseCase.AutoLoading
    private suspend fun memberAccountFocusChange(intent: AccountIntent.MemberAccountFocusChange) {
        if (intent.isFocus) {
            memberAccountStatus.value = LoginInputStatus.FOCUS
        } else {
            memberAccountStatus.value = LoginInputStatus.NORMAL
        }
    }

    @IntentProcess
    @BusinessUseCase.AutoLoading
    private suspend fun telephoneFocusChange(intent: AccountIntent.TelephoneFocusChange) {
        if (intent.isFocus) {
            telephoneStatus.value = LoginInputStatus.FOCUS
        } else {
            telephoneStatus.value = LoginInputStatus.NORMAL
        }
    }

    @IntentProcess
    @BusinessUseCase.AutoLoading
    private suspend fun createTimeLeftFocusChange(intent: AccountIntent.CreateTimeLeftFocusChange) {
        if (intent.isFocus) {
            telephoneStatus.value = LoginInputStatus.FOCUS
        } else {
            telephoneStatus.value = LoginInputStatus.NORMAL
        }
    }

    @IntentProcess
    @BusinessUseCase.AutoLoading
    private suspend fun createTimeRightFocusChange(intent: AccountIntent.CreateTimeRightFocusChange) {
        if (intent.isFocus) {
            telephoneStatus.value = LoginInputStatus.FOCUS
        } else {
            telephoneStatus.value = LoginInputStatus.NORMAL
        }
    }

    @IntentProcess
    @BusinessUseCase.AutoLoading
    private suspend fun roleNameLeftFocusChange(intent: AccountIntent.RoleNameLeftFocusChange) {
        if (intent.isFocus) {
            telephoneStatus.value = LoginInputStatus.FOCUS
        } else {
            telephoneStatus.value = LoginInputStatus.NORMAL
        }
    }

    @IntentProcess
    @BusinessUseCase.AutoLoading
    private suspend fun roleNameRightFocusChange(intent: AccountIntent.RoleNameRightFocusChange) {
        if (intent.isFocus) {
            telephoneStatus.value = LoginInputStatus.FOCUS
        } else {
            telephoneStatus.value = LoginInputStatus.NORMAL
        }
    }

    @IntentProcess
    @BusinessUseCase.AutoLoading
    private suspend fun logout(intent: AccountIntent.Logout) {
        ServiceManager.get(CommonInterface::class)
            ?.logOut(object : CommonNothingCallback {
                override fun onSuccess() {
                    SharedPreferenceUtil.deleteValueForKey(SPINS_TOKEN)
                    SharedPreferenceUtil.deleteValueForKey(SPINS_USER)
                    ActivityStack.finish { it.hasFlag(flag = APP_ACTIVITY_FLAG_ACCOUNT) }
                }

                override fun onError(errorMessage: String) {
                    tip(errorMessage.toStringItemDto())
                }
            })
    }


    override fun destroy() {
        super.destroy()
        commonUseCase.destroy()
    }

}