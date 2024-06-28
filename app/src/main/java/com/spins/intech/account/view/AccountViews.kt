package com.spins.intech.account.view

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Context
import android.util.Log
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.app.module.base.bean.ButtonType
import com.app.module.base.bean.InputType
import com.app.module.base.bean.LoginInputStatus
import com.app.module.base.bean.SearchBean
import com.app.module.base.bean.ShowType
import com.app.module.base.common.DrawGradientLine
import com.app.module.base.common.GradientSearchCreateButton
import com.app.module.base.common.SpinsDatePickerDialog
import com.app.module.base.common.SpinsInput
import com.app.module.base.common.SpinsShow
import com.app.module.base.common.clickableNoRipple
import com.app.module.base.common.localDrawerState
import com.app.module.base.common.localEndTime
import com.app.module.base.common.localEndTimeClicked
import com.app.module.base.common.localMemberAccount
import com.app.module.base.common.localMemberAccountChange
import com.app.module.base.common.localMemberAccountFocusChange
import com.app.module.base.common.localMemberAccountStatus
import com.app.module.base.common.localMemberListScrollState
import com.app.module.base.common.localPagerState
import com.app.module.base.common.localRoleManagerScrollState
import com.app.module.base.common.localStartTime
import com.app.module.base.common.localStartTimeClicked
import com.app.module.base.common.localTelephone
import com.app.module.base.common.localTelephoneChange
import com.app.module.base.common.localTelephoneFocusChange
import com.app.module.base.common.localTelephoneStatus
import com.app.module.base.extension.SPINS_USER
import com.app.module.base.extension.SharedPreferenceUtil
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.spins.intech.account.domain.AccountIntent
import com.xiaojinzi.reactive.template.view.BusinessContentView
import com.xiaojinzi.support.ktx.nothing
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch
import java.util.Calendar
import java.util.Locale

@InternalCoroutinesApi
@ExperimentalMaterial3Api
@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Composable
private fun AccountView(
    needInit: Boolean? = null,
) {
    val context = LocalContext.current

    BusinessContentView<AccountViewModel>(
        needInit = needInit,
    ) { vm ->
        val pagerStateOb by vm.pagerState.collectAsState(initial = PagerState(currentPage = 0))
        val memberListScrollStateOb by vm.memberListScrollState.collectAsState(
            initial = ScrollState(
                initial = 0
            )
        )
        val roleManagerScrollStateOb by vm.roleManagerScrollState.collectAsState(
            initial = ScrollState(
                initial = 0
            )
        )
        val drawerStateOb by vm.drawerState.collectAsState(initial = DrawerState(DrawerValue.Closed))

        val onMemberAccountChange: (TextFieldValue) -> Unit = { textFieldValue ->
            vm.memberAccount.value = textFieldValue
        }

        val onTelephoneChange: (TextFieldValue) -> Unit = { textFieldValue ->

        }
        val memberAccountStatusOb by vm.memberAccountStatus.collectAsState(initial = LoginInputStatus.NORMAL)
        val telephoneStatusOb by vm.telephoneStatus.collectAsState(initial = LoginInputStatus.NORMAL)
        val onMemberAccountFocusChanged: (FocusState) -> Unit = { focusState ->
            vm.addIntent(AccountIntent.MemberAccountFocusChange(context, focusState.isFocused))
        }
        val onTelephoneFocusChanged: (FocusState) -> Unit = { focusState ->
            vm.addIntent(AccountIntent.TelephoneFocusChange(context, focusState.isFocused))
        }
        val logout: () -> Unit = {
            vm.addIntent(AccountIntent.Logout(context))
        }
        val search: (Boolean) -> Unit = { isSearchMore ->
            vm.addIntent(
                AccountIntent.Search(
                    context,
                    isSearchMore
                )
            )
        }
        val searchListOb by vm.searchList.collectAsState(initial = mutableListOf())

        val onStarTimeFocusChanged: () -> Unit = {
            showTimePicker(context) {
                vm.startTimeValue.value = it
            }
        }
        val onEndTimeFocusChanged: () -> Unit = {
            showTimePicker(context) {
                vm.endTimeValue.value = it
            }
        }

        CompositionLocalProvider(
            localDrawerState provides drawerStateOb,
            localPagerState provides pagerStateOb,
            localMemberListScrollState provides memberListScrollStateOb,
            localRoleManagerScrollState provides roleManagerScrollStateOb,
            localMemberAccountChange provides onMemberAccountChange,
            localTelephoneChange provides onTelephoneChange,
            localMemberAccount provides vm.memberAccount.collectAsStateWithLifecycle(),
            localTelephone provides vm.telephone.collectAsState(initial = TextFieldValue()),
            localMemberAccountStatus provides memberAccountStatusOb,
            localTelephoneStatus provides telephoneStatusOb,
            localMemberAccountFocusChange provides onMemberAccountFocusChanged,
            localTelephoneFocusChange provides onTelephoneFocusChanged,
            localStartTime provides vm.startTimeValue.collectAsStateWithLifecycle(),
            localEndTime provides vm.endTimeValue.collectAsStateWithLifecycle(),
            localStartTimeClicked provides onStarTimeFocusChanged,
            localEndTimeClicked provides onEndTimeFocusChanged
        ) {
            ModalNavigationDrawer(
                drawerContent = { DrawContent(logout) },
                drawerState = drawerStateOb,
                scrimColor = Color.Transparent,
            ) {
                HorizontalPager(
                    state = pagerStateOb,
                    count = 2,
                    userScrollEnabled = false
                ) { page ->

                    when (page) {
                        0 -> MemberList(search, searchListOb) { userName ->
                            vm.addIntent(AccountIntent.Call(context, userName))
                        }
//                        1 -> RowManage(vm)
                    }
                }
            }
        }
    }
}

private fun showTimePicker(context: Context, onDateSelected: (String) -> Unit) {
    val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)
    SpinsDatePickerDialog(context, { _, selectedYear, selectedMonth, selectedDay ->
        val date = "$selectedYear-${selectedMonth + 1}-$selectedDay 00:00:00"
        onDateSelected(date)
    }, year, month, day).show()
}

@InternalCoroutinesApi
@ExperimentalMaterial3Api
@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Composable
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
fun AccountViewWrap() {
    Scaffold(
        containerColor = Color.White
    ) {
        Box(
            modifier = Modifier
                .padding(top = it.calculateTopPadding())
                .nothing(),
        ) {
            AccountView()
        }
    }
}

@InternalCoroutinesApi
@ExperimentalMaterial3Api
@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Preview
@Composable
private fun AccountViewPreview() {
    AccountView(
        needInit = false,
    )
}

@Composable
private fun MemberList(
    search: (Boolean) -> Unit,
    searchList: List<SearchBean?>,
    call: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Spacer(
            modifier = Modifier
                .height(16.dp)

        )
        OpenDrawerIcon()
        Spacer(modifier = Modifier.height(31.dp))
        Text(
            text = stringResource(id = com.res.R.string.res_start_time),
            style = TextStyle(
                fontSize = 14.sp, color = colorResource(
                    id = com.res.R.color.res_667382
                )
            ),
            modifier = Modifier.padding(start = 14.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))
        SpinsShow(showType = ShowType.StartTime)


        Text(
            text = stringResource(id = com.res.R.string.res_end_time),
            style = TextStyle(
                fontSize = 14.sp, color = colorResource(
                    id = com.res.R.color.res_667382
                )
            ),
            modifier = Modifier.padding(start = 14.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))
        SpinsShow(showType = ShowType.EndTime)

        Text(
            text = stringResource(id = com.res.R.string.res_member_account),
            modifier = Modifier.padding(start = 14.dp),
            style = TextStyle(
                fontSize = 14.sp, color = colorResource(
                    id = com.res.R.color.res_667382
                )
            )
        )
        Spacer(modifier = Modifier.height(9.dp))

        SpinsInput(inputType = InputType.MemberAccount)

        Spacer(modifier = Modifier.height(11.dp))
        GradientSearchCreateButton(ButtonType.MemberAccount,
            stringResource(id = com.res.R.string.res_search),
            stringResource(id = com.res.R.string.res_bulk_import),
            { isSearchMore ->
                search(isSearchMore)
            },
            {
                Log.i("马超", "批量导入")
            })
        Spacer(modifier = Modifier.height(21.dp))
        Row {
            Spacer(modifier = Modifier.width(15.dp))
            LazyColumn(
                verticalArrangement = Arrangement.Top,
                modifier = Modifier
                    .weight(1f)
                    .border(
                        width = 1.dp,
                        color = colorResource(id = com.res.R.color.res_edf1f7),
                        shape = RoundedCornerShape(10.dp)
                    )
                    .horizontalScroll(state = localMemberListScrollState.current)
                    .clip(RoundedCornerShape(10.dp))
                    .wrapContentWidth()
                    .nothing()

            ) {

                item {
                    SearchItemHeader()
                }
                itemsIndexed(searchList) { index, item ->
                    if (index == searchList.size - 1) {
                        search(true)
                    }
                    Column(modifier = Modifier.wrapContentWidth()) {
                        Divider(
                            color = colorResource(id = com.res.R.color.res_edf1f7),
                            modifier = Modifier
                                .width(525.dp)
                                .height(1.dp)
                        )
                        SearchItem(
                            jing = item?.id.toString(),
                            status = when (item?.status) {
                                1 -> stringResource(id = com.res.R.string.res_called)
                                2 -> stringResource(id = com.res.R.string.res_not_called)
                                else -> stringResource(id = com.res.R.string.res_failed)
                            },
                            account = item?.name.toString(),
                            type = item?.dial_type.toString(),
                            commissioner = item?.commissioner.toString(),
                            call
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.width(15.dp))
        }
        Spacer(
            modifier = Modifier
                .height(52.dp)
                .navigationBarsPadding()
        )
    }

}

@Composable
private fun SearchItemHeader() {
    Row(
        modifier = Modifier
            .wrapContentWidth()
            .height(50.dp)
            .background(
                color = colorResource(id = com.res.R.color.res_edf1f7),
                shape = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp)
            )
    ) {
        TextItem(text = stringResource(id = com.res.R.string.res_jing), 56.dp)
        TextItem(
            text = stringResource(id = com.res.R.string.res_operation),
            width = 85.dp,
        )
        TextItem(text = stringResource(id = com.res.R.string.res_status), 59.dp)
        TextItem(text = stringResource(id = com.res.R.string.res_account_lower), 97.dp)
        TextItem(text = stringResource(id = com.res.R.string.res_type), 123.dp)
        TextItem(text = stringResource(id = com.res.R.string.res_commissioner), 125.dp)

    }
}

@Composable
private fun TextItem(
    text: String,
    width: Dp,
    isButton: Boolean = false,
    call: (String) -> Unit = {},
    callAccount: String = ""
) {
    if (isButton) {
        Column(modifier = Modifier.width(width)) {
            Spacer(
                modifier = Modifier
                    .height(10.dp)
                    .width(20.dp)
                    .nothing()
            )

            TextButton(
                onClick = { call(callAccount) },
                contentPadding = PaddingValues(0.dp),
                modifier = Modifier
                    .padding(0.dp)
                    .background(
                        color = colorResource(id = com.res.R.color.res_0f64e3),
                        shape = RoundedCornerShape(5.dp)
                    )
                    .height(30.dp)
                    .fillMaxSize()
            ) {
                Text(
                    text = stringResource(id = com.res.R.string.res_call),
                    style = TextStyle(color = Color.White, fontSize = 14.sp),
                )
            }
            Spacer(
                modifier = Modifier
                    .height(10.dp)
                    .width(20.dp)
            )
        }
    } else {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .width(width), contentAlignment = Alignment.Center
        ) {
            Text(
                text = text,
                style = TextStyle(
                    fontSize = 16.sp,
                    color = colorResource(id = com.res.R.color.res_333b43)
                ),
            )
        }
    }
}

@Composable
private fun SearchItem(
    jing: String,
    status: String,
    account: String,
    type: String,
    commissioner: String,
    call: (String) -> Unit
) {

    Row(
        modifier = Modifier
            .wrapContentWidth()
            .height(50.dp)
            .nothing()
    ) {
        TextItem(text = jing, 56.dp)
        TextItem(
            text = stringResource(id = com.res.R.string.res_call),
            width = 85.dp,
            isButton = true,
            callAccount = account,
            call = call
        )
        TextItem(text = status, 59.dp)
        TextItem(text = account, 97.dp)
        TextItem(text = type, 123.dp)
        TextItem(text = commissioner, 125.dp)
    }

}

@Composable
private fun RowManage(vm: AccountViewModel) {
    Column(modifier = Modifier.fillMaxSize()) {
        Spacer(modifier = Modifier.height(16.dp))
        OpenDrawerIcon()
        Spacer(modifier = Modifier.height(31.dp))
        Text(
            text = stringResource(id = com.res.R.string.res_manage),
            modifier = Modifier.padding(start = 14.dp),
            style = TextStyle(
                fontSize = 22.sp, color = colorResource(
                    id = com.res.R.color.res_0D2478
                ), fontWeight = FontWeight(600)
            )
        )
        Spacer(modifier = Modifier.height(23.dp))
        Text(
            text = stringResource(id = com.res.R.string.res_create_time),
            modifier = Modifier.padding(start = 14.dp),
            style = TextStyle(
                fontSize = 14.sp, color = colorResource(
                    id = com.res.R.color.res_667382
                )
            )
        )
//        ManageInput(
//            leftValue = vm.createTimeInputLeftValue.collectAsState(initial = TextFieldValue()),
//            rightValue = vm.
//        )
        Spacer(modifier = Modifier.height(9.dp))
        GradientSearchCreateButton(ButtonType.Manager,
            stringResource(id = com.res.R.string.res_search),
            stringResource(id = com.res.R.string.res_create),
            {
                Log.i("马超", "搜索")
            },
            {
                Log.i("马超", "创建")
            })
        Spacer(modifier = Modifier.height(23.dp))
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .background(Color.Green)
        ) {

        }
        Spacer(modifier = Modifier.height(52.dp))

    }

}

@Composable
private fun OpenDrawerIcon() {
    val scope = rememberCoroutineScope()
    val drawerState = localDrawerState.current
    Row(modifier = Modifier.padding(start = 14.dp)) {
        Image(painter = painterResource(id = com.res.R.drawable.open_drawer),
            contentDescription = "打开抽屉",
            modifier =
            Modifier.clickableNoRipple {
                scope.launch {
                    drawerState.open()
                }
            })
    }
}

@Composable
private fun DrawContent(logout: () -> Unit) {
    Row(
        modifier = Modifier
            .wrapContentWidth()
            .fillMaxHeight()
            .background(Color.White)
            .clickableNoRipple {
//禁止点击drawerContent关闭，空实现即可。同时去除水波纹
            }
            .nothing()
    ) {
        DrawColumnContent(logout)

        DrawGradientLine(listOf(colorResource(id = com.res.R.color.res_cbcfd4), Color.White))
    }
}

@Composable
private fun DrawColumnContent(logout: () -> Unit) {
    val scope = rememberCoroutineScope()
    val drawerState = localDrawerState.current
    val pagerState = localPagerState.current
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .width(327.dp)
    ) {
        Spacer(modifier = Modifier.height(18.dp))
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .height(57.dp)
        ) {
            val (imageAvatar, text, imageClose) = createRefs()
            Image(
                painter = painterResource(id = com.res.R.drawable.avator),
                contentDescription = "用户头像",
                modifier = Modifier
                    .width(50.dp)
                    .height(50.dp)
                    .constrainAs(imageAvatar) {
                        start.linkTo(parent.start, margin = 15.dp)
                        bottom.linkTo(parent.bottom)
                    }
                    .nothing(),
            )
            Text(
                "${SharedPreferenceUtil.getString(SPINS_USER)}",
                style = TextStyle(
                    fontSize = 20.sp,
                    color = colorResource(id = com.res.R.color.res_090E15),
                    fontWeight = FontWeight.W600
                ),
                modifier = Modifier.constrainAs(text) {
                    top.linkTo(imageAvatar.top)
                    bottom.linkTo(imageAvatar.bottom)
                    start.linkTo(imageAvatar.end, margin = 15.dp)
                }
            )
            Image(painter = painterResource(id = com.res.R.drawable.close),
                contentDescription = "关闭Drawer按钮",
                modifier = Modifier
                    .width(14.dp)
                    .height(14.dp)
                    .constrainAs(imageClose) {
                        top.linkTo(parent.top)
                        end.linkTo(parent.end, margin = 13.dp)
                    }
                    .clickable {
                        scope.launch {
                            drawerState.close()
                        }
                    }
                    .nothing())


        }
        Spacer(modifier = Modifier.height(24.dp))
        Row(
            Modifier
                .fillMaxWidth()
                .height(60.dp)
        ) {
            Spacer(modifier = Modifier.width(14.dp))
            Row(verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .weight(1f)
                    .height(60.dp)
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(
                                colorResource(id = com.res.R.color.res_2eafff),
                                colorResource(id = com.res.R.color.res_167AFF)
                            )
                        )
                    )
                    .clickableNoRipple {
                        scope.launch {
                            drawerState.close()
                            pagerState.scrollToPage(0)
                        }
                    }
            ) {
                Spacer(modifier = Modifier.width(19.dp))
                Image(
                    painter = painterResource(id = com.res.R.drawable.white_circle),
                    contentDescription = "白色圆圈",
                    modifier = Modifier
                        .width(13.dp)
                        .height(13.dp)
                )
                Spacer(modifier = Modifier.width(33.dp))
                Text(
                    text = stringResource(id = com.res.R.string.res_member_list),
                    style = TextStyle(color = Color.White, fontSize = 20.sp)
                )
            }
            Spacer(modifier = Modifier.width(14.dp))
        }
        /**
        Spacer(modifier = Modifier.height(10.dp))

        Row(
        Modifier
        .fillMaxWidth()
        .height(60.dp)
        ) {
        Spacer(modifier = Modifier.width(14.dp))
        Row(verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
        .weight(1f)
        .height(60.dp)
        .background(colorResource(id = com.res.R.color.res_edf1f7))
        .clickableNoRipple {
        scope.launch {
        drawerState.close()
        pagerState.scrollToPage(1)
        }
        }) {
        Spacer(modifier = Modifier.width(19.dp))
        Image(
        painter = painterResource(id = com.res.R.drawable.gray_circle),
        contentDescription = "灰色圆环",
        modifier = Modifier
        .width(13.dp)
        .height(13.dp)
        )
        Spacer(modifier = Modifier.width(33.dp))
        Text(
        text = stringResource(id = com.res.R.string.res_role_permission),
        style = TextStyle(
        color = colorResource(
        id = com.res.R.color.res_667382
        ), fontSize = 20.sp
        )
        )
        }
        Spacer(modifier = Modifier.width(14.dp))
        }
         */
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = stringResource(id = com.res.R.string.res_log_out),
            style = TextStyle(fontSize = 20.sp),
            color = colorResource(
                id = com.res.R.color.res_667382
            ),
            modifier = Modifier
                .padding(start = 15.dp)
                .clickableNoRipple {
                    logout()
                }
        )
    }
}