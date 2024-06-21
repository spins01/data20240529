package com.app.module.base.common

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.input.key.Key.Companion.W
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.module.base.bean.ButtonType
import com.app.module.base.bean.InputType
import com.app.module.base.bean.LoginInputStatus
import com.xiaojinzi.support.ktx.nothing

@Composable
fun DrawGradientLine(colors: List<Color>) {
    val gradient = Brush.horizontalGradient(
        colors = colors,
        tileMode = TileMode.Clamp
    )
    androidx.compose.foundation.Canvas(
        modifier = Modifier
            .fillMaxHeight()
            .width(4.dp)
    ) {
        drawLine(
            gradient,
            Offset(size.width / 2, 0f),
            Offset(size.width / 2, size.height),
            4.dp.toPx()
        )
    }
}

@Composable
fun GradientSearchCreateButton(
    buttonType: ButtonType,
    leftName: String,
    rightName: String,
    onClickLeft: () -> Unit,
    onClickRight: () -> Unit
) {
    Row() {
        Spacer(modifier = Modifier.width(14.dp))
        Button(
            onClick = onClickLeft,
            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
            contentPadding = PaddingValues(all = 0.dp),
            modifier = Modifier
                .background(
                    brush = Brush.linearGradient(
                        listOf(
                            colorResource(id = com.res.R.color.res_167AFF),
                            colorResource(id = com.res.R.color.res_2eafff)
                        )
                    ),
                    shape = RoundedCornerShape(999.dp)
                )
                .height(46.dp)
                .width(78.dp)
        ) {
            Text(text = leftName, color = Color.White, fontSize = 15.sp)
        }
        Spacer(modifier = Modifier.width(18.dp))
        when (buttonType) {
            ButtonType.MemberAccount -> {
                Button(
                    onClick = onClickRight,
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                    contentPadding = PaddingValues(all = 0.dp),
                    modifier = Modifier
                        .background(
                            color = colorResource(id = com.res.R.color.res_ebf1ff),
                            shape = RoundedCornerShape(999.dp)
                        )
                        .height(46.dp)
                        .width(0.dp),
//                        .width(113.dp),
                    border = BorderStroke(1.dp, colorResource(id = com.res.R.color.res_3a77ff))
                ) {
                    Text(
                        text = rightName,
                        color = colorResource(id = com.res.R.color.res_3a77ff),
                        fontSize = 15.sp
                    )
                }
            }

            ButtonType.Manager -> {
                Button(
                    onClick = onClickRight,
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                    contentPadding = PaddingValues(all = 0.dp),
                    modifier = Modifier
                        .background(
                            brush = Brush.linearGradient(
                                listOf(
                                    colorResource(id = com.res.R.color.res_167AFF),
                                    colorResource(id = com.res.R.color.res_2eafff)
                                )
                            ),
                            shape = RoundedCornerShape(999.dp)
                        )
                        .height(46.dp)
                        .width(78.dp)
                ) {
                    Text(text = rightName, color = Color.White, fontSize = 15.sp)
                }
            }
        }
    }
}

@Composable
fun GradientLoginButton(isEnabled: Boolean, name: String, onClick: () -> Unit) {
    val mBrush: Brush = if (isEnabled) {
        Brush.linearGradient(
            listOf(
                colorResource(id = com.res.R.color.res_167AFF),
                colorResource(id = com.res.R.color.res_2eafff)
            )
        )
    } else {
        Brush.linearGradient(
            listOf(
                colorResource(id = com.res.R.color.res_167AFF).copy(alpha = 0.4f),
                colorResource(id = com.res.R.color.res_2eafff).copy(alpha = 0.4f)
            )
        )
    }
    Row() {
        Spacer(modifier = Modifier.width(39.dp))
        Button(
            onClick = onClick,
            enabled = isEnabled,
            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
            contentPadding = PaddingValues(all = 0.dp),
            modifier = Modifier
                .background(
                    brush = mBrush,
                    shape = RoundedCornerShape(999.dp)
                )
                .height(46.dp)
                .weight(1f)
        ) {
            Text(text = name, color = Color.White, fontSize = 15.sp)
        }
        Spacer(modifier = Modifier.width(39.dp))
    }
}

@Composable
fun InputErrorTips(inputType: InputType) {
    val tips = when (inputType) {
        InputType.Account -> localAccountErrorTips.current
        InputType.Password -> localPasswordErrorTips.current
        else -> ""
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
fun SpinsInput(inputType: InputType) {
    val onAccountValueChange = localAccountChange.current
    val onPasswordValueChange = localPasswordChange.current
    val onMemberAccountValueChange = localMemberAccountChange.current
    val onTelephoneValueChange = localTelephoneChange.current
    val inputValue = when (inputType) {
        InputType.Account -> localAccount.current.value
        InputType.Password -> localPassword.current.value
        InputType.MemberAccount -> localMemberAccount.current.value
        InputType.Telephone -> localTelephone.current.value
    }
    val leftSpace = when (inputType) {
        InputType.Account, InputType.Password -> 12.dp
        InputType.MemberAccount, InputType.Telephone -> 14.dp
    }
    val rightSpace = when (inputType) {
        InputType.Account, InputType.Password -> 12.dp
        InputType.MemberAccount, InputType.Telephone -> 118.dp
    }
    Row {
        Spacer(modifier = Modifier.width(leftSpace))
        BasicTextField(
            value = inputValue,
            onValueChange = { newValue ->
                when (inputType) {
                    InputType.Account -> onAccountValueChange(newValue)
                    InputType.Password -> onPasswordValueChange(newValue)
                    InputType.MemberAccount -> onMemberAccountValueChange(newValue)
                    InputType.Telephone -> onTelephoneValueChange(newValue)
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
        Spacer(modifier = Modifier.width(rightSpace))
    }
}

@Composable
fun ManageInput(
    leftValue: TextFieldValue,
    rightValue: TextFieldValue,
    onValueChangeLeft: (TextFieldValue) -> Unit,
    onValueChangeRight: (TextFieldValue) -> Unit,
    onClickLeft: () -> Unit,
    onClickRight: () -> Unit,
    onFocusChangeLeft: (FocusState) -> Unit,
    onFocusChangeRight: (FocusState) -> Unit,
    leftInputStatus: LoginInputStatus,
    rightInputStatus: LoginInputStatus
) {
    val leftBorderWidth = when (leftInputStatus) {
        LoginInputStatus.ERROR, LoginInputStatus.FOCUS -> 2.dp
        LoginInputStatus.NORMAL -> 1.dp
    }
    val leftBorderColor: Color = when (leftInputStatus) {
        LoginInputStatus.ERROR -> colorResource(id = com.res.R.color.res_f7391f)
        LoginInputStatus.NORMAL -> colorResource(id = com.res.R.color.res_d8e3ed)
        LoginInputStatus.FOCUS -> colorResource(id = com.res.R.color.res_0f64e3)
    }
    val rightBorderWidth = when (rightInputStatus) {
        LoginInputStatus.ERROR, LoginInputStatus.FOCUS -> 2.dp
        LoginInputStatus.NORMAL -> 1.dp
    }
    val rightBorderColor: Color = when (rightInputStatus) {
        LoginInputStatus.ERROR -> colorResource(id = com.res.R.color.res_f7391f)
        LoginInputStatus.NORMAL -> colorResource(id = com.res.R.color.res_d8e3ed)
        LoginInputStatus.FOCUS -> colorResource(id = com.res.R.color.res_0f64e3)
    }
    Row() {
        Spacer(modifier = Modifier.width(14.dp))
        BasicTextField(
            value = leftValue,
            onValueChange = { newValue ->
                onValueChangeLeft(newValue)
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
                .width(230.dp)
                .border(leftBorderWidth, leftBorderColor, RoundedCornerShape(6.dp))
                .background(Color.White, RoundedCornerShape(6.dp))
                .padding(start = 10.dp)
                .height(42.dp)
                .onFocusChanged { focusState ->
                    onFocusChangeLeft(focusState)
                }
                .nothing()
        )
        Spacer(modifier = Modifier.width(14.dp))
        BasicTextField(
            value = rightValue,
            onValueChange = { newValue ->
                onValueChangeRight(newValue)
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
                .width(230.dp)
                .border(rightBorderWidth, rightBorderColor, RoundedCornerShape(6.dp))
                .background(Color.White, RoundedCornerShape(6.dp))
                .padding(start = 10.dp)
                .height(42.dp)
                .onFocusChanged { focusState ->
                    onFocusChangeRight(focusState)
                }
                .nothing()
        )
    }
}
