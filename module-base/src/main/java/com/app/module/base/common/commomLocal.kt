package com.app.module.base.common

import androidx.compose.foundation.ScrollState
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.runtime.State
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.text.input.TextFieldValue
import com.app.module.base.bean.LoginInputStatus
import com.google.accompanist.pager.PagerState

//val localAccount =
//    compositionLocalOf<State<TextFieldValue>> { error("no value provided to localAccount") }
//val localAccountChange =
//    compositionLocalOf<(TextFieldValue) -> Unit> { error("no value provided to localAccountChange") }
//val localOnAccountFocusChanged =
//    compositionLocalOf<(FocusState) -> Unit> { error("no value provided to localOnAccountFocusChanged") }
//val localAccountStatus =
//    compositionLocalOf<LoginInputStatus> { error("no value provided to localOnAccountStatusChanged") }
//val localAccountErrorTips =
//    compositionLocalOf<String> { error("no value provided to localAccountErrorTips") }
//val localPassword =
//    compositionLocalOf<State<TextFieldValue>> { error("no value provided to localPassword") }
//val localPasswordChange =
//    compositionLocalOf<(TextFieldValue) -> Unit> { error("no value provided to localPasswordChange") }
//val localOnPasswordFocusChanged =
//    compositionLocalOf<(FocusState) -> Unit> { error("no value provided to localOnAccountFocusChanged") }
//val localPasswordStatus =
//    compositionLocalOf<LoginInputStatus> { error("no value provided to localOnAccountStatusChanged") }
//val localPasswordErrorTips =
//    compositionLocalOf<String> { error("no value provided to localPasswordErrorTips") }
//
//
//val localDrawerState =
//    compositionLocalOf<DrawerState> { error("no value provided to localDrawerState") }
//val localPagerState =
//    compositionLocalOf<PagerState> { error("no value provided to localPagerState") }
//val localMemberListScrollState = compositionLocalOf<ScrollState> { error("no value provided to localMemberListScrollState")  }
//val localRoleManagerScrollState = compositionLocalOf<ScrollState> { error("no value provided to localRoleManagerScrollState")  }
//val localMemberAccountChange = compositionLocalOf <(TextFieldValue)->Unit>{ error("no value provided to localMemberAccountChange") }
//val localTelephoneChange = compositionLocalOf <(TextFieldValue)->Unit>{ error("no value provided to localTelephoneAccount") }
//val localMemberAccount = compositionLocalOf<State<TextFieldValue>> { error("no value provided to localMemberAccount") }
//val localTelephone = compositionLocalOf<State<TextFieldValue>> { error("no value provided to localTelephone") }
//val localMemberAccountStatus = compositionLocalOf<LoginInputStatus> { error("no value provided to localMemberAccountStatus") }
//val localTelephoneStatus = compositionLocalOf<LoginInputStatus> { error("no value provided to localTelephoneStatus") }
//val localMemberAccountFocusChange = compositionLocalOf<(FocusState) ->Unit> { error("no value provided to localMemberAccountFocusChange") }
//val localTelephoneFocusChange = compositionLocalOf<(FocusState) ->Unit> { error("no value provided to localTelephoneFocusChange") }


val localAccount =
    compositionLocalOf<State<TextFieldValue>> { mutableStateOf(TextFieldValue("")) }
val localAccountChange =
    compositionLocalOf<(TextFieldValue) -> Unit> { { }}
val localOnAccountFocusChanged =
    compositionLocalOf<(FocusState) -> Unit> {{} }
val localAccountStatus =
    compositionLocalOf<LoginInputStatus> { LoginInputStatus.NORMAL}
val localAccountErrorTips =
    compositionLocalOf<String> { ""}
val localPassword =
    compositionLocalOf<State<TextFieldValue>> { mutableStateOf(TextFieldValue(""))}
val localPasswordChange =
    compositionLocalOf<(TextFieldValue) -> Unit> { {  } }
val localOnPasswordFocusChanged =
    compositionLocalOf<(FocusState) -> Unit> { {}}
val localPasswordStatus =
    compositionLocalOf<LoginInputStatus> { LoginInputStatus.NORMAL }
val localPasswordErrorTips =
    compositionLocalOf<String> { ""}


val localDrawerState =
    compositionLocalOf<DrawerState> { DrawerState(DrawerValue.Closed) }
val localPagerState =
    compositionLocalOf<PagerState> { PagerState(currentPage = 0)}
val localMemberListScrollState = compositionLocalOf<ScrollState> { ScrollState(initial = 0) }
val localRoleManagerScrollState = compositionLocalOf<ScrollState> { ScrollState(initial = 0) }
val localMemberAccountChange = compositionLocalOf <(TextFieldValue)->Unit>{ {} }
val localTelephoneChange = compositionLocalOf <(TextFieldValue)->Unit>{ {} }
val localMemberAccount = compositionLocalOf<State<TextFieldValue>> { mutableStateOf(TextFieldValue(""))}
val localTelephone = compositionLocalOf<State<TextFieldValue>> { mutableStateOf(TextFieldValue(""))}
val localMemberAccountStatus = compositionLocalOf<LoginInputStatus> { LoginInputStatus.NORMAL }
val localTelephoneStatus = compositionLocalOf<LoginInputStatus> { LoginInputStatus.NORMAL }
val localMemberAccountFocusChange = compositionLocalOf<(FocusState) ->Unit> { {} }
val localTelephoneFocusChange = compositionLocalOf<(FocusState) ->Unit> { {} }