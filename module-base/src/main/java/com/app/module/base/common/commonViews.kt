package com.app.module.base.common

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.module.base.bean.InputType

@Composable
fun InputErrorTips(inputType: InputType) {
    val tips = when (inputType) {
        InputType.Account -> localAccountErrorTips.current
        InputType.Password -> localPasswordErrorTips.current
    }
    Text(
        text = tips,
        modifier = Modifier.padding(start = 12.dp),
        style = TextStyle(
            fontSize = 12.sp,
            color = colorResource(id = com.res.R.color.res_f7391f)
        )
    )
}

@Composable
fun LoginInput(inputType: InputType) {
    val onAccountValueChange = localAccountChange.current
    val onPasswordValueChange = localPasswordChange.current
    val inputValue = when (inputType) {
        InputType.Account -> localAccount.current.value
        InputType.Password -> localPassword.current.value
    }
    Row {
        Spacer(modifier = Modifier.width(12.dp))
        BasicTextField(
            value = inputValue,
            onValueChange = { newValue ->
                when (inputType) {
                    InputType.Account -> onAccountValueChange(newValue)
                    InputType.Password -> onPasswordValueChange(newValue)
                }

            },
            textStyle = TextStyle.Default.copy(
                fontSize = 14.sp,
                color = colorResource(id = com.res.R.color.res_090E15),
                textAlign = TextAlign.Start
            ),
            singleLine = true,
            decorationBox = { innerTextField ->
                Row(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    innerTextField()
                }
            },
            modifier = Modifier
                .weight(1f)
                .loginInput(inputType)


        )
        Spacer(modifier = Modifier.width(12.dp))
    }
}
