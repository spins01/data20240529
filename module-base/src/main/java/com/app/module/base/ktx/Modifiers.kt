package com.app.module.base.ktx

import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layout
import com.xiaojinzi.support.ktx.notSupportError

enum class LayoutCustomWidthMode {
    BaseSelf,
    AtLastHeight,
}

enum class LayoutCustomHeightMode {
    BaseSelf,
    AtLastWidth,
}

fun Modifier.layoutCustom(
    widthMode: LayoutCustomWidthMode = LayoutCustomWidthMode.BaseSelf,
    heightMode: LayoutCustomHeightMode = LayoutCustomHeightMode.BaseSelf,
): Modifier {
    return this.layout { measurable, constraints ->
        if (widthMode == LayoutCustomWidthMode.AtLastHeight && heightMode == LayoutCustomHeightMode.AtLastWidth) {
            notSupportError()
        }
        val placeable = measurable.measure(constraints)
        val targetWidth = when (widthMode) {
            LayoutCustomWidthMode.BaseSelf -> placeable.width
            LayoutCustomWidthMode.AtLastHeight -> maxOf(a = placeable.width, b = placeable.height)
        }
        val targetHeight = when (heightMode) {
            LayoutCustomHeightMode.BaseSelf -> placeable.height
            LayoutCustomHeightMode.AtLastWidth -> maxOf(a = placeable.width, b = placeable.height)
        }
        val dx = (targetWidth - placeable.width) / 2
        val dy = (targetHeight - placeable.height) / 2
        layout(targetWidth, targetHeight) {
            placeable.placeRelative(dx, dy)
        }
    }
}