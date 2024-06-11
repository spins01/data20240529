package com.app.module.base.view.compose

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.xiaojinzi.support.bean.StringItemDto
import com.xiaojinzi.support.compose.util.contentWithComposable
import com.xiaojinzi.support.ktx.nothing
import com.xiaojinzi.support.ktx.toStringItemDto
import com.xiaojinzi.support.ktx.tryFinishActivity

@Composable
fun AppbarNormalM3(
    backIcon: ImageVector? = Icons.Filled.ArrowBack,
    backClickListener: (() -> Unit)? = null,
    containerColor: Color = MaterialTheme.colorScheme.background,
    contentColor: Color = MaterialTheme.colorScheme.onBackground,
    title: StringItemDto? = null,
    titleStyle: TextStyle = MaterialTheme.typography.titleMedium.copy(
        color = contentColor,
    ),
    titleTextAlign: TextAlign = TextAlign.Center,
    titleContent: @Composable (BoxScope.() -> Unit)? = null,
    @DrawableRes
    menu1IconRsd: Int? = null,
    menu1IconTint: Color = contentColor,
    menu1TextValue: StringItemDto? = null,
    menu1TextStyle: TextStyle = MaterialTheme.typography.titleSmall,
    menu1ClickListener: (() -> Unit)? = null,
    @DrawableRes
    menu2IconRsd: Int? = null,
    menu2IconTint: Color = contentColor,
    menu2TextValue: StringItemDto? = null,
    menu2TextStyle: TextStyle = MaterialTheme.typography.titleSmall,
    menu2ClickListener: (() -> Unit)? = null,
) {
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = containerColor)
            .statusBarsPadding()
            .nothing(),
    ) {

        IconButton(
            modifier = Modifier
                .align(alignment = Alignment.CenterStart)
                .nothing(),
            enabled = backIcon != null,
            onClick = {
                if (backClickListener == null) {
                    context.tryFinishActivity()
                } else {
                    backClickListener.invoke()
                }
            },
        ) {
            backIcon?.let {
                Icon(
                    painter = rememberVectorPainter(image = backIcon),
                    contentDescription = null,
                    tint = contentColor,
                )
            }
        }
        Row(
            modifier = Modifier
                .align(alignment = Alignment.CenterEnd)
                .padding(end = 8.dp)
                .nothing(),
        ) {
            menu2TextValue?.let {
                TextButton(onClick = {
                    menu2ClickListener?.invoke()
                }) {
                    Text(
                        text = menu2TextValue.contentWithComposable(),
                        style = menu2TextStyle,
                    )
                }
            }
            if (menu2IconRsd != null) {
                IconButton(onClick = {
                    menu2ClickListener?.invoke()
                }) {
                    Icon(
                        modifier = Modifier
                            .size(20.dp)
                            .nothing(),
                        painter = painterResource(id = menu2IconRsd),
                        contentDescription = null,
                        tint = menu2IconTint,
                    )
                }
            }
            menu1TextValue?.let {
                TextButton(onClick = {
                    menu1ClickListener?.invoke()
                }) {
                    Text(
                        text = menu1TextValue.contentWithComposable(),
                        style = menu1TextStyle,
                    )
                }
            }
            if (menu1IconRsd != null) {
                IconButton(onClick = {
                    menu1ClickListener?.invoke()
                }) {
                    Icon(
                        modifier = Modifier
                            .size(20.dp)
                            .nothing(),
                        painter = painterResource(id = menu1IconRsd),
                        contentDescription = null,
                        tint = menu1IconTint,
                    )
                }
            }
        }

        titleContent?.let {
            it()
        }?: run {
            val titleStr = title?.contentWithComposable()
            titleStr?.run {
                Text(
                    modifier = Modifier
                        .align(alignment = Alignment.Center)
                        .wrapContentSize()
                        .nothing(),
                    text = this,
                    style = titleStyle,
                    textAlign = titleTextAlign,
                )
            }
        }
    }
}

@Preview
@Composable
private fun AppbarNormalPreviewM3() {
    AppbarNormalM3(
        title = "测试".toStringItemDto(),
        menu1TextValue = "点我".toStringItemDto(),
    )
}