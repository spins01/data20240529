package com.app.module.base.common

import android.util.Log
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.spins.module.base.R

@Composable
fun LoginInput() {
    val onValueChange = localAccountChange.current
    Row {
        Spacer(modifier = Modifier.width(12.dp))
        BasicTextField(
            value = localAccount.current.value,
            onValueChange = { newValue ->
                Log.i("马超","newValue${newValue}    ")
                onValueChange(newValue)
            },
            textStyle = TextStyle.Default.copy(
                fontSize = 14.sp,
                color = colorResource(id = com.res.R.color.res_090E15),
                textAlign = TextAlign.Start),
            singleLine = true,
            decorationBox = {innerTextField ->  Row(modifier = Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically){
                innerTextField()
            } },
            modifier = Modifier
                .weight(1f)
                .loginInput(localAccountStatus.current)


        )
        Spacer(modifier = Modifier.width(12.dp))
    }
}
