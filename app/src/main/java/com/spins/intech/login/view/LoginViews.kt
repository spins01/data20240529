package com.spins.intech.login.view

import android.annotation.SuppressLint
import android.util.Log
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import com.app.module.base.bean.LoginInputStatus
import com.app.module.base.common.LoginInput
import com.app.module.base.common.localAccount
import com.app.module.base.common.localAccountChange
import com.app.module.base.common.localAccountStatus
import com.app.module.base.common.localOnAccountFocusChanged
import com.spins.intech.login.domain.LoginIntent
import com.xiaojinzi.reactive.template.view.BusinessContentView
import com.xiaojinzi.support.ktx.nothing
import kotlinx.coroutines.InternalCoroutinesApi

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
        val accountStatusOb by vm.accountStatus.collectAsState(initial = LoginInputStatus.NORMAL)
        val accountOB by vm.account.collectAsState(initial = TextFieldValue())
        //账号变化
        val onAccountChange: (TextFieldValue) -> Unit = { textFieldValue ->
            vm.account.value = textFieldValue.copy(text = textFieldValue.text.trim())
        }
        //账号焦点变化
        val onAccountFocusChanged: (FocusState) -> Unit = { focusState ->
            vm.addIntent(
                intent = LoginIntent.AccountFocusChange(context, focusState.isFocused)
            )
        }
        CompositionLocalProvider(
            localAccount provides accountOB,
            localAccountChange provides onAccountChange,
            localAccountStatus provides accountStatusOb,
            localOnAccountFocusChanged provides onAccountFocusChanged
        ) {
            Column(
//            horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .fillMaxSize()
                    .nothing(),
            ) {
                Spacer(modifier = Modifier.height(345.dp))
                Column(Modifier.weight(1f)) {
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
                            .padding(start = 36.dp)
                            .align(Alignment.Start),
                        style = TextStyle(
                            fontSize = 14.sp,
                            color = colorResource(id = com.res.R.color.res_667382)
                        )
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .align(Alignment.CenterHorizontally)
                            .background(
                                color = colorResource(id = com.res.R.color.res_f5f8fb),
                                shape = RoundedCornerShape(6.dp)
                            )
                    ) {
                        Spacer(modifier = Modifier.height(12.dp))
                        LoginInput()
                    }
                }
                Spacer(modifier = Modifier.height(374.dp))
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
//        topBar = {
//            AppbarNormalM3(
//                title = "hello".toStringItemDto(),
//            )
//        }
        Modifier.background(Color.White)
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