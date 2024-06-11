package com.app.module.base.support

import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import androidx.appcompat.content.res.AppCompatResources
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import com.xiaojinzi.support.ktx.app

object AppImageDefault {

    private val DefaultImage: Drawable = AppCompatResources.getDrawable(
        app, com.res.R.drawable.res_placeholder1
    ) ?: ColorDrawable(Color(0XFFF2F2F2).toArgb())

    val placeholderImage = DefaultImage
    val fallbackImage = DefaultImage
    val errorImage = DefaultImage

}