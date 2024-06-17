package com.spins.intech.account.view

import android.annotation.SuppressLint
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.app.module.base.common.DrawGradientLine
import com.app.module.base.common.localDrawerState
import com.app.module.base.common.localPagerState
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.xiaojinzi.reactive.template.view.BusinessContentView
import com.xiaojinzi.support.ktx.nothing
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch

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
        CompositionLocalProvider(
            localDrawerState provides drawerStateOb,
            localPagerState provides pagerStateOb
        ) {
            ModalNavigationDrawer(
                drawerContent = { DrawContent() },
                drawerState = drawerStateOb,
                scrimColor = Color.Transparent,
            ) {
                HorizontalPager(
                    state = pagerStateOb,
                    count = 2,
                    userScrollEnabled = false
                ) { page ->
                    when (page) {
                        0 -> MemberList()
                        1 -> RowManage()
                    }
                }
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
private fun MemberList() {
    Text("会员列表", style = TextStyle(fontSize = 40.sp))
}

@Composable
private fun RowManage() {
    Text("角色权限", style = TextStyle(fontSize = 40.sp))
}

@Composable
private fun DrawContent() {
    Row(
        modifier = Modifier
            .wrapContentWidth()
            .fillMaxHeight()
            .background(Color.White)
            .nothing()
    ) {
        DrawColumnContent()

        DrawGradientLine(listOf(colorResource(id = com.res.R.color.res_cbcfd4), Color.White))
    }
}

@Composable
private fun DrawColumnContent() {
    val scope = rememberCoroutineScope()
    val drawerState= localDrawerState.current
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
                "赵云",
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
                contentDescription = "关闭按钮",
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
    }
}