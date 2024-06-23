package com.spins.intech.login.view

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.module.base.bean.InputType
import com.app.module.base.bean.LoginInputStatus
import com.app.module.base.common.CommonInterface
import com.app.module.base.common.CommonObjCallback
import com.app.module.base.common.GradientLoginButton
import com.app.module.base.common.InputErrorTips
import com.app.module.base.common.SpinsInput
import com.app.module.base.common.localAccount
import com.app.module.base.common.localAccountChange
import com.app.module.base.common.localAccountErrorTips
import com.app.module.base.common.localAccountStatus
import com.app.module.base.common.localOnAccountFocusChanged
import com.app.module.base.common.localOnPasswordFocusChanged
import com.app.module.base.common.localPassword
import com.app.module.base.common.localPasswordChange
import com.app.module.base.common.localPasswordErrorTips
import com.app.module.base.common.localPasswordStatus
import com.app.module.base.extension.SPINS_TOKEN
import com.app.module.base.extension.SharedPreferenceUtil
import com.app.module.base.support.AppRouterApi
import com.spins.intech.login.domain.LoginIntent
import com.xiaojinzi.component.impl.Router
import com.xiaojinzi.component.impl.service.ServiceManager
import com.xiaojinzi.reactive.template.view.BusinessContentView
import com.xiaojinzi.support.ktx.nothing
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch

@InternalCoroutinesApi
@ExperimentalMaterial3Api
@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Composable
private fun LoginView(
    needInit: Boolean? = null,
) {
    val context = LocalContext.current
    BusinessContentView<LoginViewModel>(
        needInit = needInit,
    ) { vm ->
        val scope = rememberCoroutineScope()
        //登录按钮
        val buttonIsEnabledOb by vm.buttonIsEnabled.collectAsState(initial = false)
        //账号状态
        val accountStatusOb by vm.accountStatus.collectAsState(initial = LoginInputStatus.NORMAL)
        //账号变化
        val onAccountChange: (TextFieldValue) -> Unit = { textFieldValue ->
            vm.account.value = textFieldValue.copy(text = textFieldValue.text.trim())
            if (textFieldValue.text.isEmpty()) {
                vm.accountStatus.value = LoginInputStatus.ERROR
                vm.accountErrorTips.value = context.getString(com.res.R.string.res_account_empty)
            } else {
                vm.accountStatus.value = LoginInputStatus.FOCUS
            }
            vm.buttonIsEnabled.value = vm.account.value.text.isNotBlank() && vm.password.value.text.isNotBlank()
        }

        //账号焦点变化
        val onAccountFocusChanged: (FocusState) -> Unit = { focusState ->
            vm.addIntent(
                intent = LoginIntent.AccountFocusChange(context, focusState.isFocused)
            )
        }
        //账号错误提示
        val accountErrorTipsOb by vm.accountErrorTips.collectAsState(initial = stringResource(id = com.res.R.string.res_account_empty))
        //密码状态
        val passwordStatusOb by vm.passwordStatus.collectAsState(initial = LoginInputStatus.NORMAL)
        //密码变化
        val onPasswordChange: (TextFieldValue) -> Unit = { textFieldValue ->
            vm.password.value = textFieldValue.copy(text = textFieldValue.text.trim())
            if (textFieldValue.text.isEmpty()) {
                vm.passwordStatus.value = LoginInputStatus.ERROR
                vm.passwordErrorTips.value = context.getString(com.res.R.string.res_password_empty)
            } else {
                vm.passwordStatus.value = LoginInputStatus.FOCUS
            }
            vm.buttonIsEnabled.value = vm.account.value.text.isNotBlank() && vm.password.value.text.isNotBlank()
        }
        //密码焦点变化
        val onPasswordFocusChanged: (FocusState) -> Unit = { focusState ->
            vm.addIntent(
                intent = LoginIntent.PasswordFocusChange(context, focusState.isFocused)
            )
        }

        //密码错误提示
        val passwordErrorTipsOb by vm.passwordErrorTips.collectAsState(initial = stringResource(id = com.res.R.string.res_password_empty))
        CompositionLocalProvider(
            localAccount provides vm.account.collectAsState(initial = TextFieldValue()),
            localAccountChange provides onAccountChange,
            localAccountStatus provides accountStatusOb,
            localOnAccountFocusChanged provides onAccountFocusChanged,
            localAccountErrorTips provides accountErrorTipsOb,
            localPassword provides vm.password.collectAsState(initial = TextFieldValue()),
            localPasswordChange provides onPasswordChange,
            localPasswordStatus provides passwordStatusOb,
            localOnPasswordFocusChanged provides onPasswordFocusChanged,
            localPasswordErrorTips provides passwordErrorTipsOb
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .nothing(),
            ) {
                Spacer(modifier = Modifier.weight(345f))
                Column(Modifier.wrapContentHeight()) {
                    //Welcome
                    Text(
                        text = stringResource(id = com.res.R.string.res_welcome),
                        style = TextStyle(
                            fontSize = 18.sp,
                            color = colorResource(id = com.res.R.color.res_0D2478)
                        ),
                        modifier = Modifier
                            .padding(start = 36.dp)
                            .align(Alignment.Start)
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = stringResource(id = com.res.R.string.res_sign_in_tips),
                        modifier = Modifier
                            .padding(start = 36.dp),
                        style = TextStyle(
                            fontSize = 14.sp,
                            color = colorResource(id = com.res.R.color.res_667382)
                        )
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                    ) {
                        Spacer(modifier = Modifier.width(36.dp))
                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .background(
                                    color = colorResource(id = com.res.R.color.res_f5f8fb),
                                    shape = RoundedCornerShape(6.dp)
                                )
                        ) {
                            Spacer(modifier = Modifier.height(12.dp))
                            if (accountStatusOb != LoginInputStatus.ERROR) {
                                Text(
                                    text = stringResource(id = com.res.R.string.res_account),
                                    style = TextStyle(
                                        fontSize = 12.sp,
                                        color = colorResource(id = com.res.R.color.res_667382)
                                    ),
                                    modifier = Modifier.padding(start = 12.dp)
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                            }

                            SpinsInput(InputType.Account)
                            if (accountStatusOb == LoginInputStatus.ERROR) {
                                Spacer(modifier = Modifier.height(4.dp))
                                  InputErrorTips(InputType.Account)
                            }
                            Spacer(modifier = Modifier.height(20.dp))
                            if(passwordStatusOb != LoginInputStatus.ERROR){
                                Text(
                                    text = stringResource(id = com.res.R.string.res_password),
                                    style = TextStyle(
                                        fontSize = 12.sp,
                                        color = colorResource(id = com.res.R.color.res_667382)
                                    ),
                                    modifier = Modifier.padding(start = 12.dp)
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                            }
                            //密码登录框
                            SpinsInput(InputType.Password)
                            if(passwordStatusOb == LoginInputStatus.ERROR){
                                Spacer(modifier = Modifier.height(4.dp))
                                InputErrorTips(InputType.Password)
                            }
                            Spacer(modifier = Modifier.height(32.dp))
                            //登录按钮
                            GradientLoginButton(buttonIsEnabledOb,
                                stringResource(id = com.res.R.string.res_sign_in)
                            ) {
                                vm.addIntent(LoginIntent.Login(context))
                            }
                            Spacer(modifier = Modifier.height(12.dp))
                        }
                        Spacer(modifier = Modifier.width(36.dp))
                    }

                }
                Spacer(modifier = Modifier.weight(374f))
            }
        }
    }
}

@InternalCoroutinesApi
@ExperimentalMaterial3Api
@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Composable
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
fun LoginViewWrap() {
    Scaffold(
        containerColor = Color.White
    )
    {
        Box(
            modifier = Modifier
                .padding(top = it.calculateTopPadding())

                .nothing(),
        ) {
            LoginView()
        }
    }
}

@InternalCoroutinesApi
@ExperimentalMaterial3Api
@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Preview
@Composable
private fun LoginViewPreview() {
    LoginView(
        needInit = false,
    )
}