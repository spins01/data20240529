package com.app.module.base.common

import androidx.compose.material3.DrawerState
import androidx.compose.runtime.State
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.text.input.TextFieldValue
import com.app.module.base.bean.LoginInputStatus
import com.google.accompanist.pager.PagerState

val localAccount =
    compositionLocalOf<State<TextFieldValue>> { error("no value provided to localAccount") }
val localAccountChange =
    compositionLocalOf<(TextFieldValue) -> Unit> { error("no value provided to localAccountChange") }
val localOnAccountFocusChanged =
    compositionLocalOf<(FocusState) -> Unit> { error("no value provided to localOnAccountFocusChanged") }
val localAccountStatus =
    compositionLocalOf<LoginInputStatus> { error("no value provided to localOnAccountStatusChanged") }
val localAccountErrorTips =
    compositionLocalOf<String> { error("no value provided to localAccountErrorTips") }
val localPassword =
    compositionLocalOf<State<TextFieldValue>> { error("no value provided to localPassword") }
val localPasswordChange =
    compositionLocalOf<(TextFieldValue) -> Unit> { error("no value provided to localPasswordChange") }
val localOnPasswordFocusChanged =
    compositionLocalOf<(FocusState) -> Unit> { error("no value provided to localOnAccountFocusChanged") }
val localPasswordStatus =
    compositionLocalOf<LoginInputStatus> { error("no value provided to localOnAccountStatusChanged") }
val localPasswordErrorTips =
    compositionLocalOf<String> { error("no value provided to localPasswordErrorTips") }


val localDrawerState =
    compositionLocalOf<DrawerState> { error("no value provided to localDrawerState") }
val localPagerState =
    compositionLocalOf<PagerState> { error("no value provided to localPagerState") }