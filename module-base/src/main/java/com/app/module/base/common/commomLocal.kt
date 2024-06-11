package com.app.module.base.common

import androidx.compose.runtime.State
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.text.input.TextFieldValue
import com.app.module.base.bean.LoginInputStatus

val localAccount = compositionLocalOf <TextFieldValue>{ error("no value provided to localAccount") }
val localAccountChange = compositionLocalOf<(TextFieldValue) -> Unit>{  error("no value provided to localAccountChange")  }
val localOnAccountFocusChanged =
    compositionLocalOf<(FocusState) -> Unit> { error("no value provided to localOnAccountFocusChanged") }
val localAccountStatus =
    compositionLocalOf<LoginInputStatus> { error("no value provided to localOnAccountStatusChanged") }