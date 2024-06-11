package com.app.module.user.module.privacy_agreement.view

import android.annotation.SuppressLint
import android.app.Activity
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.app.module.base.support.AppServices
import com.xiaojinzi.reactive.template.view.BusinessContentView
import com.xiaojinzi.support.ktx.getActivity
import com.xiaojinzi.support.ktx.nothing
import com.xiaojinzi.support.ktx.tryFinishActivity
import com.xiaojinzi.tally.lib.res.ui.APP_PADDING_LARGE
import com.xiaojinzi.tally.lib.res.ui.AppWidthSpace
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
@ExperimentalMaterial3Api
@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Composable
private fun PrivacyAgreementView(
    needInit: Boolean? = false,
) {
    val context = LocalContext.current
    BusinessContentView<PrivacyAgreementViewModel>(
        needInit = needInit,
    ) { vm ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = MaterialTheme.colorScheme.surface,
                )
                .statusBarsPadding()
                .padding(horizontal = APP_PADDING_LARGE.dp, vertical = 0.dp)
                .nothing(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            Spacer(
                modifier = Modifier
                    .height(height = 30.dp)
                    .nothing()
            )

            Row(
                modifier = Modifier
                    .align(
                        alignment = Alignment.Start,
                    )
                    .wrapContentSize()
                    .nothing(),
                verticalAlignment = Alignment.CenterVertically,
            ) {

                Image(
                    modifier = Modifier
                        .size(size = 80.dp)
                        .nothing(),
                    painter = rememberAsyncImagePainter(
                        model = AppServices.appInfoSpi.appIconRsd
                    ),
                    contentDescription = null,
                )

                AppWidthSpace()

                Column(
                    modifier = Modifier
                        .wrapContentSize()
                        .nothing(),
                ) {
                    Text(
                        text = "欢迎使用",
                        style = MaterialTheme.typography.bodyLarge.copy(
                            color = MaterialTheme.colorScheme.onSurface.copy(
                                alpha = 0.8f,
                            ),
                        ),
                        textAlign = TextAlign.Start,
                    )
                    Spacer(
                        modifier = Modifier
                            .height(height = 4.dp)
                            .nothing()
                    )
                    Text(
                        text = "一刻记账",
                        fontFamily = FontFamily.Serif,
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface,
                        ),
                        textAlign = TextAlign.Start,
                    )
                }

            }

            Spacer(
                modifier = Modifier
                    .height(height = 24.dp)
                    .nothing()
            )

            val annotatedString = buildAnnotatedString {
                this.append(
                    text = "欢迎使用 一刻记账 App. 我们非常重视您的个人信息和隐私保护. \n为了给您提供更优质的服务, 一刻记账将会使用您的个人信息. ",
                )
                this.withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                        color = Color(
                            color = MaterialTheme.colorScheme.onSurface.toArgb(),
                        ),
                    ),
                ) {
                    append("请在使用前认真阅读")
                }
                this.pushStringAnnotation(tag = "userProtocol1", annotation = "userProtocol1")
                this.withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                        color = Color(
                            color = MaterialTheme.colorScheme.primary.toArgb(),
                        ),
                    ),
                ) {
                    append("《一刻记账用户协议》")
                }
                this.pop()
                this.withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                        color = Color(
                            color = MaterialTheme.colorScheme.onSurface.toArgb(),
                        ),
                    ),
                ) {
                    append(" 于 ")
                }
                this.pushStringAnnotation(tag = "userProtocol2", annotation = "userProtocol2")
                this.withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                        color = Color(
                            color = MaterialTheme.colorScheme.primary.toArgb(),
                        ),
                    ),
                ) {
                    append("《一刻记账隐私协议》")
                }
                this.pop()
                this.withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                        color = Color(
                            color = MaterialTheme.colorScheme.onSurface.toArgb(),
                        ),
                    ),
                ) {
                    append(" , 点击 同意并继续 表示您已知情并同意以上协议和以下约定\n\n")
                }
                this.append(
                    text = "1. 上传记账图片需要使用您的图片存储、相机权限.\n",
                )
                this.append(
                    text = "2. 您可以在相关页面访问、修改、删除您的个人信息以及管理您的授权.\n",
                )
            }

            ClickableText(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(weight = 1f, fill = true)
                    .nothing(),
                text = annotatedString,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Normal,
                    color = MaterialTheme.colorScheme.onSurface,
                    lineHeight = MaterialTheme.typography.bodyMedium.lineHeight.times(1.2f),
                ),
                onClick = { offset ->
                    annotatedString
                        .getStringAnnotations(
                            tag = "userProtocol1",
                            start = offset,
                            end = offset,
                        )
                        .firstOrNull()
                        ?.let {
                            // 去 web 界面
                        }
                    annotatedString
                        .getStringAnnotations(
                            tag = "userProtocol2",
                            start = offset,
                            end = offset,
                        )
                        .firstOrNull()
                        ?.let {
                            // 去 web 界面
                        }
                },
            )

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .nothing(),
                onClick = {
                    context.getActivity()?.run {
                        this.setResult(
                            Activity.RESULT_OK,
                        )
                        this.finish()
                    }
                },
            ) {
                Text(
                    text = "同意并继续",
                    textAlign = TextAlign.Start,
                )
            }

            TextButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .nothing(),
                onClick = {
                    context.tryFinishActivity()
                },
            ) {
                Text(
                    text = "不同意",
                    textAlign = TextAlign.Start,
                )
            }

            Spacer(
                modifier = Modifier
                    .navigationBarsPadding()
                    .height(height = 12.dp)
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
fun PrivacyAgreementViewWrap() {
    PrivacyAgreementView()
}

@InternalCoroutinesApi
@ExperimentalMaterial3Api
@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Preview
@Composable
private fun PrivacyAgreementViewPreview() {
    PrivacyAgreementView(
        needInit = false,
    )
}