package com.app.module.user.module.loading.view

import android.annotation.SuppressLint
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.xiaojinzi.reactive.template.view.BusinessContentView
import com.xiaojinzi.support.ktx.nothing
import com.xiaojinzi.tally.lib.res.ui.AppHeightSpace
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
@ExperimentalMaterial3Api
@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Composable
private fun LoadingView(
    previewDefault: LoadingPreviewDefault? = null,
) {
    val context = LocalContext.current
    BusinessContentView<LoadingViewModel>(
        needInit = false,
    ) { vm ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .nothing(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Icon(
                modifier = Modifier
                    .size(size = 60.dp)
                    .nothing(),
                painter = painterResource(
                    id = com.res.R.drawable.res_book1,
                ),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurface,
            )
            AppHeightSpace()
            Text(
                modifier = Modifier
                    .wrapContentSize()
                    .nothing(),
                text = "一刻记账",
                fontFamily = FontFamily(
                    Font(
                        resId = com.res.R.font.res_font_xdks
                    )
                ),
                style = MaterialTheme.typography.titleLarge.copy(
                    color = MaterialTheme.colorScheme.onSurface,
                ),
                textAlign = TextAlign.Start,
            )
            Spacer(
                modifier = Modifier
                    .height(height = 160.dp)
                    .nothing()
            )
        }
    }
}

@InternalCoroutinesApi
@ExperimentalMaterial3Api
@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Composable
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
fun LoadingViewWrap() {
    LoadingView()
}

private data class LoadingPreviewDefault(
    val str: String,
)

@InternalCoroutinesApi
@ExperimentalMaterial3Api
@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Preview(
    showBackground = true,
)
@Composable
private fun LoadingViewPreview() {
    LoadingView(
        previewDefault = LoadingPreviewDefault(
            str = "测试数据",
        )
    )
}