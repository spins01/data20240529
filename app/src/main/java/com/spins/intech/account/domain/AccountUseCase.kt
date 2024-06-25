package com.spins.intech.account.domain


import android.content.Context
import android.widget.Toast
import androidx.annotation.UiContext
import androidx.compose.foundation.ScrollState
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.ui.text.input.TextFieldValue
import com.app.module.base.bean.LoginInputStatus
import com.app.module.base.bean.SearchBean
import com.app.module.base.common.CommonInterface
import com.app.module.base.common.CommonListCallback
import com.app.module.base.common.CommonNothingCallback
import com.app.module.base.extension.APP_ACTIVITY_FLAG_ACCOUNT
import com.app.module.base.extension.SPINS_TOKEN
import com.app.module.base.extension.SharedPreferenceUtil
import com.google.accompanist.pager.PagerState
import com.xiaojinzi.component.impl.service.ServiceManager
import com.xiaojinzi.reactive.anno.IntentProcess
import com.xiaojinzi.reactive.template.domain.BusinessUseCase
import com.xiaojinzi.reactive.template.domain.BusinessUseCaseImpl
import com.xiaojinzi.reactive.template.domain.CommonUseCase
import com.xiaojinzi.reactive.template.domain.CommonUseCaseImpl
import com.xiaojinzi.support.activity_stack.ActivityStack
import com.xiaojinzi.support.annotation.ViewModelLayer
import kotlinx.coroutines.flow.MutableStateFlow


sealed class AccountIntent {

    data object Submit : AccountIntent()

    data class MemberAccountFocusChange(@UiContext val context: Context, val isFocus: Boolean):AccountIntent()
    data class TelephoneFocusChange(@UiContext val context: Context, val isFocus: Boolean):AccountIntent()
    data class CreateTimeLeftFocusChange(@UiContext val context: Context, val isFocus: Boolean):AccountIntent()
    data class CreateTimeRightFocusChange(@UiContext val context: Context, val isFocus: Boolean):AccountIntent()
    data class RoleNameLeftFocusChange(@UiContext val context: Context, val isFocus: Boolean):AccountIntent()
    data class RoleNameRightFocusChange(@UiContext val context: Context, val isFocus: Boolean):AccountIntent()
    data class Logout(@UiContext val context: Context) : AccountIntent()
    data class Search(@UiContext val context: Context,val currentPage:Int,val pageSize:Int) : AccountIntent()
}

@ViewModelLayer
interface AccountUseCase : BusinessUseCase {
    val pagerState: MutableStateFlow<PagerState>
    val memberListScrollState: MutableStateFlow<ScrollState>
    val roleManagerScrollState: MutableStateFlow<ScrollState>
    val drawerState: MutableStateFlow<DrawerState>
    val memberAccount: MutableStateFlow<TextFieldValue>
    val telephone: MutableStateFlow<TextFieldValue>
    val memberAccountStatus:MutableStateFlow<LoginInputStatus>
    val telephoneStatus:MutableStateFlow<LoginInputStatus>


    val createTimeInputLeftValue:MutableStateFlow<TextFieldValue>
    val createTimeInputRightValue:MutableStateFlow<TextFieldValue>
    val createTimeInputLeftStatus:MutableStateFlow<LoginInputStatus>
    val createTimeInputRightStatus:MutableStateFlow<LoginInputStatus>
    val roleNameInputLeftValue:MutableStateFlow<TextFieldValue>
    val roleNameInputRightValue:MutableStateFlow<TextFieldValue>
    val roleNameInputLeftStatus:MutableStateFlow<LoginInputStatus>
    val roleNameInputRightStatus:MutableStateFlow<LoginInputStatus>

    val currentPage:MutableStateFlow<Int>
    val pageSize:MutableStateFlow<Int>
    val searchList:MutableStateFlow<List<SearchBean?>>
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
    override val memberAccountStatus: MutableStateFlow<LoginInputStatus> = MutableStateFlow(LoginInputStatus.NORMAL)

    override val telephoneStatus: MutableStateFlow<LoginInputStatus> = MutableStateFlow(LoginInputStatus.NORMAL)

    override val createTimeInputLeftValue: MutableStateFlow<TextFieldValue> = MutableStateFlow(TextFieldValue())
    override val createTimeInputRightValue: MutableStateFlow<TextFieldValue> = MutableStateFlow(TextFieldValue())
    override val createTimeInputLeftStatus: MutableStateFlow<LoginInputStatus> = MutableStateFlow(LoginInputStatus.NORMAL)
    override val createTimeInputRightStatus: MutableStateFlow<LoginInputStatus> = MutableStateFlow(LoginInputStatus.NORMAL)
    override val roleNameInputLeftValue: MutableStateFlow<TextFieldValue> = MutableStateFlow(TextFieldValue())
    override val roleNameInputRightValue: MutableStateFlow<TextFieldValue> = MutableStateFlow(TextFieldValue())
    override val roleNameInputLeftStatus: MutableStateFlow<LoginInputStatus> = MutableStateFlow(LoginInputStatus.NORMAL)
    override val roleNameInputRightStatus: MutableStateFlow<LoginInputStatus> = MutableStateFlow(LoginInputStatus.NORMAL)
    override val currentPage: MutableStateFlow<Int> = MutableStateFlow(1)

    override val pageSize: MutableStateFlow<Int> = MutableStateFlow(4)
    override val searchList:MutableStateFlow<List<SearchBean?>> = MutableStateFlow(mutableListOf())


    @IntentProcess
    @BusinessUseCase.AutoLoading
    private suspend fun submit(intent: AccountIntent.Submit) {

    }
    @IntentProcess
    @BusinessUseCase.AutoLoading
    private suspend fun search(intent: AccountIntent.Search) {
         ServiceManager.get(CommonInterface::class)?.search(memberAccount.value.text,currentPage.value,pageSize.value,object :
             CommonListCallback<SearchBean> {
             override fun onSuccess(
                 list: List<SearchBean>
             ) {
                 if(list.isNotEmpty()){
                     searchList.value = list
                 }
             }


             override fun onError(errorMessage: String) {

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
            ?.logOut(object : CommonNothingCallback{
                override fun onSuccess() {
                    SharedPreferenceUtil.deleteValueForKey(SPINS_TOKEN)
                    ActivityStack.finish{it.hasFlag(flag = APP_ACTIVITY_FLAG_ACCOUNT)}
                }

                override fun onError(errorMessage: String) {
                     Toast.makeText(intent.context,errorMessage,Toast.LENGTH_LONG).show()
                }
            })
    }


    override fun destroy() {
        super.destroy()
        commonUseCase.destroy()
    }

}