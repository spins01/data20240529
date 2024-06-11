package com.xiaojinzi.tally.lib.res.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.Interaction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.xiaojinzi.support.ktx.nothing
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

const val APP_ACTIVITY_FLAG_LOGIN = "login"
const val APP_ACTIVITY_FLAG_MAIN = "main"

// 跳过这次同步
const val APP_ACTIVITY_FLAG_SYNC_SKIP = "syncSkip"

const val APP_PADDING_SMALL = 8
const val APP_PADDING_NORMAL = 12
const val APP_PADDING_LARGE = 16

val AppBackgroundColor: Color
    @Composable
    get() = MaterialTheme.colorScheme.surfaceColorAtElevation(
        elevation = 0.5.dp,
    )

val AppButtonBrush: Brush
    @Composable
    get() = Brush.verticalGradient(
        colors = listOf(
            MaterialTheme.colorScheme.primary.copy(
                alpha = 0.8f,
            ),
            MaterialTheme.colorScheme.primary,
        ),
    )

@Composable
fun AppWidthSpace(width: Int = APP_PADDING_NORMAL) {
    Spacer(
        modifier = Modifier
            .width(width = width.dp)
            .nothing()
    )
}

@Composable
fun AppHeightSpace(height: Int = APP_PADDING_NORMAL) {
    Spacer(
        modifier = Modifier
            .height(height = height.dp)
            .nothing()
    )
}

@Composable
fun AppDivider(
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .height(height = 1.dp)
        .nothing(),
    color: Color = MaterialTheme.colorScheme.surfaceColorAtElevation(
        elevation = 2.dp,
    ),
) {
    Spacer(
        modifier = Modifier
            .then(other = modifier)
            .background(
                color = color,
            )
            .nothing()
    )
}

val AppShape1 = RoundedCornerShape(1.dp)
val AppShape2 = RoundedCornerShape(2.dp)
val AppShape3 = RoundedCornerShape(3.dp)
val AppShape4 = RoundedCornerShape(4.dp)
val AppShape5 = RoundedCornerShape(5.dp)
val AppShape6 = RoundedCornerShape(6.dp)
val AppShape7 = RoundedCornerShape(7.dp)
val AppShape8 = RoundedCornerShape(8.dp)
val AppShape9 = RoundedCornerShape(9.dp)
val AppShape10 = RoundedCornerShape(10.dp)
val AppShape11 = RoundedCornerShape(11.dp)
val AppShape12 = RoundedCornerShape(12.dp)
val AppShape13 = RoundedCornerShape(13.dp)
val AppShape14 = RoundedCornerShape(14.dp)
val AppShape15 = RoundedCornerShape(15.dp)
val AppShape16 = RoundedCornerShape(16.dp)
val AppShape17 = RoundedCornerShape(17.dp)
val AppShape18 = RoundedCornerShape(18.dp)
val AppShape19 = RoundedCornerShape(19.dp)
val AppShape20 = RoundedCornerShape(20.dp)
val AppShape21 = RoundedCornerShape(21.dp)
val AppShape22 = RoundedCornerShape(22.dp)
val AppShape23 = RoundedCornerShape(23.dp)
val AppShape24 = RoundedCornerShape(24.dp)
val AppShape25 = RoundedCornerShape(25.dp)
val AppShape26 = RoundedCornerShape(26.dp)
val AppShape27 = RoundedCornerShape(27.dp)
val AppShape28 = RoundedCornerShape(28.dp)
val AppShape29 = RoundedCornerShape(29.dp)
val AppShape30 = RoundedCornerShape(30.dp)

fun CornerBasedShape.copyNew(
    topStart: Dp? = null,
    topEnd: Dp? = null,
    bottomStart: Dp? = null,
    bottomEnd: Dp? = null,
) = this.copy(
    topStart = topStart?.run { CornerSize(this) } ?: this.topStart,
    topEnd = topEnd?.run { CornerSize(this) } ?: this.topEnd,
    bottomStart = bottomStart?.run { CornerSize(this) } ?: this.bottomStart,
    bottomEnd = bottomEnd?.run { CornerSize(this) } ?: this.bottomEnd,
)

fun CornerBasedShape.rectShape() = this.copy(
    topStart = CornerSize(0.dp),
    topEnd = CornerSize(0.dp),
    bottomStart = CornerSize(0.dp),
    bottomEnd = CornerSize(0.dp),
)

fun CornerBasedShape.topShape() = this.copy(
    bottomStart = CornerSize(0.dp),
    bottomEnd = CornerSize(0.dp),
)

fun CornerBasedShape.bottomShape() = this.copy(
    topStart = CornerSize(0.dp),
    topEnd = CornerSize(0.dp),
)

fun CornerBasedShape.startShape() = this.copy(
    topEnd = CornerSize(0.dp),
    bottomEnd = CornerSize(0.dp),
)

fun CornerBasedShape.endShape() = this.copy(
    topStart = CornerSize(0.dp),
    bottomStart = CornerSize(0.dp),
)

object NoRippleTheme : RippleTheme {

    @Composable
    override fun defaultColor() = Color.Unspecified

    @Composable
    override fun rippleAlpha(): RippleAlpha = RippleAlpha(0f, 0f, 0f, 0f)

}

object NoInteractionSource : MutableInteractionSource {

    override val interactions: Flow<Interaction> = emptyFlow()

    override suspend fun emit(interaction: Interaction) {}

    override fun tryEmit(interaction: Interaction) = true

}