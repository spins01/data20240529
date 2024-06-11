package com.app.module.base.view.compose

import android.graphics.PointF
import androidx.annotation.FloatRange
import androidx.annotation.Keep
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.graphics.AndroidPath
import androidx.compose.ui.graphics.ClipOp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.xiaojinzi.support.bean.StringItemDto
import com.xiaojinzi.support.compose.util.contentWithComposable
import com.xiaojinzi.support.ktx.format2f
import com.xiaojinzi.support.ktx.nothing
import com.xiaojinzi.support.ktx.toStringItemDto

private val TestListVo by lazy {
    PieChartVo(
        content = "总支出：100.00".toStringItemDto(),
        items = listOf(
            PieChartItemVo(
                content = "夜宵 44.00".toStringItemDto(),
                percent = 0.2f,
            ),
            PieChartItemVo(
                content = "公交 33.00".toStringItemDto(),
                percent = 0.3f,
            ),
            PieChartItemVo(
                content = "礼金红包 1000.00".toStringItemDto(),
                percent = 0.4f,
            ),
            PieChartItemVo(
                content = "测试4".toStringItemDto(),
                percent = 0.1f,
            ),
            PieChartItemVo(
                content = "夜宵 44.00".toStringItemDto(),
                percent = 0.2f,
            ),
            PieChartItemVo(
                content = "公交 33.00".toStringItemDto(),
                percent = 0.3f,
            ),
            PieChartItemVo(
                content = "礼金红包 1000.00".toStringItemDto(),
                percent = 0.4f,
            ),
            PieChartItemVo(
                content = "测试4".toStringItemDto(),
                percent = 0.1f,
            ),
            PieChartItemVo(
                content = "夜宵 44.00".toStringItemDto(),
                percent = 0.2f,
            ),
            PieChartItemVo(
                content = "公交 33.00".toStringItemDto(),
                percent = 0.3f,
            ),
            PieChartItemVo(
                content = "礼金红包 1000.00".toStringItemDto(),
                percent = 0.4f,
            ),
            PieChartItemVo(
                content = "测试4".toStringItemDto(),
                percent = 0.1f,
            ),
        )
    )
}

// 10 个比较经典的颜色, 用于饼状图的各个部分的绘制
private val ColorList = listOf(
    Color(0xFFE57373),
    Color(0xFF81C784),
    Color(0xFF64B5F6),
    Color(0xFFFFD54F),
    Color(0xFF9575CD),
    Color(0xFF4DB6AC),
    Color(0xFFAED581),
    Color(0xFF4FC3F7),
    Color(0xFFFF8A65),
    Color(0xFF7986CB),
)

/*private val ColorList = listOf(
    Color(0xFFc23531),
    Color(0xFF2f4554),
    Color(0xFF61a0a8),
    Color(0xFFd48265),
    Color(0xFF91c7ae),
    Color(0xFF749f83),
    Color(0xFFca8622),
    Color(0xFFbda29a),
    Color(0xFF6e7074),
    Color(0xFF546570),
    Color(0xFFc4ccd3),
)*/

/*private val ColorList = listOf(
    Color(0xFFF5001C),
    Color(0xFFF56E25),
    Color(0xFFF5A82F),
    Color(0xFFF5E33D),
    Color(0xFF35D03C),
    Color(0xFF00A339),
    Color(0xFF00C573),
    Color(0xFF00D0A7),
    Color(0xFF68D4D2),
    Color(0xFF4DB0C9),
    Color(0xFF89D0F3),
    Color(0xFF0079C9),
    Color(0xFF7A93DF),
    Color(0xFFE91481),
)*/

@Keep
data class PieChartVo(
    // 中间的空白的比例
    val centerCircleRatio: Float = 5f / 7f,
    val content: StringItemDto,
    val items: List<PieChartItemVo>,
)

@Keep
data class PieChartItemVo(
    val content: StringItemDto?,
    @FloatRange(from = 0.0, to = 1.0)
    val percent: Float,
)

@ExperimentalAnimationApi
@Composable
private fun PieChartCoreView(
    modifier: Modifier = Modifier,
    vo: PieChartVo,
) {
    AnimatedVisibility(
        visible = true,
        enter = scaleIn()
    ) {
        Canvas(
            modifier = modifier,
        ) {
            val targetSizeValue = this.size.minDimension
            val centerCircleRadius = targetSizeValue * vo.centerCircleRatio / 2f
            val targetSize = this.size.copy(
                width = targetSizeValue,
                height = targetSizeValue,
            )
            val centerPoint = PointF(
                this.size.width / 2f,
                this.size.height / 2f,
            )
            this.clipPath(
                path = AndroidPath().apply {
                    this.addRoundRect(
                        roundRect = RoundRect(
                            left = centerPoint.x - centerCircleRadius,
                            top = centerPoint.y - centerCircleRadius,
                            right = centerPoint.x + centerCircleRadius,
                            bottom = centerPoint.y + centerCircleRadius,
                            radiusX = centerCircleRadius,
                            radiusY = centerCircleRadius,
                        )
                    )
                },
                clipOp = ClipOp.Difference,
            ) {
                // 总共的角度
                val totalAngle = 360f
                var startAngle = -90f
                vo.items.forEachIndexed { index, itemVo ->
                    val sweepAngle = totalAngle * itemVo.percent
                    drawArc(
                        topLeft = Offset(
                            x = (this.size.width - targetSizeValue) / 2f,
                            y = (this.size.height - targetSizeValue) / 2f,
                        ),
                        size = targetSize,
                        color = ColorList.get(index = index % ColorList.size),
                        startAngle = startAngle,
                        sweepAngle = sweepAngle,
                        useCenter = true,
                        style = Fill,
                    )
                    startAngle += sweepAngle
                }
            }
            /*drawCircle(
                color = Color.White,
                radius = centerCircleRadius,
                center = Offset(x = targetSizeValue / 2f, y = targetSizeValue / 2f),
            )*/
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@ExperimentalAnimationApi
@Composable
fun PieChartView(
    modifier: Modifier = Modifier,
    vo: PieChartVo,
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {
        if (vo.items.isEmpty()) {
            AppCommonEmptyDataView()
        } else {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .nothing(),
                verticalAlignment = Alignment.Top
            ) {
                PieChartCoreView(
                    modifier = Modifier
                        .widthIn(
                            min = 0.dp, max = 240.dp
                        )
                        .fillMaxWidth(fraction = 0.5f)
                        .aspectRatio(ratio = 1f)
                        .nothing(),
                    vo = vo,
                )
                Spacer(modifier = Modifier.width(width = 16.dp))
                Column(
                    modifier = Modifier
                        .fillMaxWidth(fraction = 1f)
                        .wrapContentHeight()
                        .nothing(),
                    horizontalAlignment = Alignment.End,
                    verticalArrangement = Arrangement.Center,
                ) {
                    FlowRow(
                        modifier = Modifier
                            .wrapContentSize()
                            .nothing(),
                        horizontalArrangement = Arrangement.End,
                    ) {
                        vo.items.forEachIndexed { index, itemVo ->
                            Row(
                                modifier = Modifier
                                    .padding(horizontal = 6.dp, vertical = 4.dp)
                                    .nothing(),
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(size = 6.dp)
                                        .clip(shape = CircleShape)
                                        .background(
                                            color = ColorList.get(index = index % ColorList.size),
                                        )
                                        .nothing(),
                                )
                                Spacer(modifier = Modifier.width(width = 2.dp))
                                Text(
                                    text = "${
                                        itemVo.content?.contentWithComposable().orEmpty()
                                    }(${itemVo.percent.times(100).format2f()}%)",
                                    style = MaterialTheme.typography.labelSmall.copy(
                                        fontSize = 10.sp,
                                        color = MaterialTheme.colorScheme.onSurface,
                                    ),
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@ExperimentalAnimationApi
@Preview
@Composable
private fun PieChartViewPreview() {
    PieChartView(vo = TestListVo)
}

@ExperimentalAnimationApi
@Preview
@Composable
private fun PieChartCoreViewPreview() {
    PieChartCoreView(
        modifier = Modifier
            .width(400.dp)
            .height(600.dp)
            .nothing(),
        vo = TestListVo
    )
}