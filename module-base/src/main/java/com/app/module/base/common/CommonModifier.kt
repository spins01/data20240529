package com.app.module.base.common


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.app.module.base.bean.LoginInputStatus


/**
 * 登录输入框的样式
 */
fun Modifier.loginInput(
    loginInputStatus: LoginInputStatus
) = composed{
    val onAccountFocusChanged = localOnAccountFocusChanged.current
    when(loginInputStatus){
        LoginInputStatus.ERROR ->{
            Modifier
                .border(2.dp, colorResource(id = com.res.R.color.res_f7391f),RoundedCornerShape (6.dp))
                .background(Color.White,RoundedCornerShape (6.dp))
                .padding(start = 10.dp)
                .height(42.dp)

                .onFocusChanged {
                    focusState -> onAccountFocusChanged(focusState)
                }
        }
        LoginInputStatus.NORMAL->{
            Modifier
                .fillMaxWidth()
                .border(1.dp, colorResource(id = com.res.R.color.res_d8e3ed),RoundedCornerShape (6.dp))
                .background(Color.White)
                .padding(start = 10.dp)
                .height(42.dp)
                .onFocusChanged {
                        focusState -> onAccountFocusChanged(focusState)
                }
        }
        LoginInputStatus.FOCUS->{
            Modifier
                .fillMaxWidth()
                .border(2.dp, colorResource(id = com.res.R.color.res_0f64e3),RoundedCornerShape (6.dp))
                .background(Color.White)
                .padding(start = 10.dp)
                .height(42.dp)
                .onFocusChanged {
                        focusState -> onAccountFocusChanged(focusState)
                }
        }
    }
}