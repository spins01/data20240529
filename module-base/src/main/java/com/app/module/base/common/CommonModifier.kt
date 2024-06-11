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
import com.app.module.base.bean.InputType
import com.app.module.base.bean.LoginInputStatus



/**
 * 登录输入框的样式
 */
fun Modifier.loginInput(
    inputType: InputType,
) = composed {
    val onAccountFocusChanged = localOnAccountFocusChanged.current
    val onPasswordFocusChange = localOnPasswordFocusChanged.current
    val loginInputStatus = when(inputType){InputType.Account-> localAccountStatus.current
    InputType.Password -> localPasswordStatus.current}

    val borderColor: Color= when (loginInputStatus) {
        LoginInputStatus.ERROR -> colorResource(id = com.res.R.color.res_f7391f)
        LoginInputStatus.NORMAL -> colorResource(id = com.res.R.color.res_d8e3ed)
        LoginInputStatus.FOCUS -> colorResource(id = com.res.R.color.res_0f64e3)
    }

    val borderWidth = when (loginInputStatus) {
        LoginInputStatus.ERROR, LoginInputStatus.FOCUS -> 2.dp
        LoginInputStatus.NORMAL -> 1.dp
    }

    Modifier
        .fillMaxWidth()
        .border(borderWidth, borderColor, RoundedCornerShape(6.dp))
        .background(Color.White, RoundedCornerShape(6.dp))
        .padding(start = 10.dp)
        .height(42.dp)
        .onFocusChanged { focusState ->
            when (inputType) {
                InputType.Account -> onAccountFocusChanged(focusState)
                InputType.Password -> onPasswordFocusChange(focusState)
            }

        }
}