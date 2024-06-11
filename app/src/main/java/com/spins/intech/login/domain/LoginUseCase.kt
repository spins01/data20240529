package com.spins.intech.login.domain

import android.content.Context
import android.util.Log
import androidx.annotation.UiContext
import androidx.compose.ui.text.input.TextFieldValue
import com.app.module.base.bean.LoginInputStatus
import com.xiaojinzi.reactive.anno.IntentProcess
import com.xiaojinzi.reactive.template.domain.BusinessUseCase
import com.xiaojinzi.reactive.template.domain.BusinessUseCaseImpl
import com.xiaojinzi.reactive.template.domain.CommonUseCase
import com.xiaojinzi.reactive.template.domain.CommonUseCaseImpl
import com.xiaojinzi.support.annotation.ViewModelLayer
import kotlinx.coroutines.flow.MutableStateFlow

sealed class LoginIntent {

    data class AccountFocusChange(@UiContext val context: Context, val isFocus: Boolean) :
        LoginIntent()
    data class PasswordFocusChange(@UiContext val context: Context, val isFocus: Boolean) :
        LoginIntent()

}

@ViewModelLayer
interface LoginUseCase : BusinessUseCase {
    val account: MutableStateFlow<TextFieldValue>
    val accountStatus: MutableStateFlow<LoginInputStatus>
    val accountErrorTips:MutableStateFlow<String>
    val password: MutableStateFlow<TextFieldValue>
    val passwordStatus:MutableStateFlow<LoginInputStatus>
    val passwordErrorTips:MutableStateFlow<String>
    val buttonIsEnabled:MutableStateFlow<Boolean>
}

@ViewModelLayer
class LoginUseCaseImpl(
    private val commonUseCase: CommonUseCase = CommonUseCaseImpl(),
) : BusinessUseCaseImpl(
    commonUseCase = commonUseCase,
), LoginUseCase {
    override val account: MutableStateFlow<TextFieldValue> =
        MutableStateFlow(value = TextFieldValue())
    override val accountStatus: MutableStateFlow<LoginInputStatus> =
        MutableStateFlow(value = LoginInputStatus.NORMAL)
    override val accountErrorTips: MutableStateFlow<String> = MutableStateFlow(value = "")

    override val password: MutableStateFlow<TextFieldValue> =
        MutableStateFlow(value = TextFieldValue())
    override val passwordStatus: MutableStateFlow<LoginInputStatus> = MutableStateFlow(value = LoginInputStatus.NORMAL)
    override val passwordErrorTips: MutableStateFlow<String> = MutableStateFlow(value = "")

    override val buttonIsEnabled: MutableStateFlow<Boolean> = MutableStateFlow(value = false)

    @IntentProcess
    @BusinessUseCase.AutoLoading
    private suspend fun accountFocusChange(intent: LoginIntent.AccountFocusChange) {
        if (intent.isFocus) {
            accountStatus.value = LoginInputStatus.FOCUS
        } else {
            accountStatus.value = LoginInputStatus.NORMAL
        }
    }
    @IntentProcess
    @BusinessUseCase.AutoLoading
    private suspend fun passwordFocusChange(intent: LoginIntent.PasswordFocusChange) {
        if (intent.isFocus) {
            passwordStatus.value = LoginInputStatus.FOCUS
        } else {
            passwordStatus.value = LoginInputStatus.NORMAL
        }
    }


    override fun destroy() {
        super.destroy()
        commonUseCase.destroy()
    }

}