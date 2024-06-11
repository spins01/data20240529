package com.app.module.base.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.xiaojinzi.support.ktx.nothing
import com.xiaojinzi.tally.lib.res.ui.theme.five.TitianRedAppTheme
import com.xiaojinzi.tally.lib.res.ui.theme.four.SchenbrunnYellowAppTheme
import com.xiaojinzi.tally.lib.res.ui.theme.one.ChinaRedAppTheme
import com.xiaojinzi.tally.lib.res.ui.theme.one.one_md_theme_dark_background
import com.xiaojinzi.tally.lib.res.ui.theme.one.one_md_theme_light_background
import com.xiaojinzi.tally.lib.res.ui.theme.three.OliveGreenAppTheme
import com.xiaojinzi.tally.lib.res.ui.theme.three.OliveGreenDynamicAppTheme
import com.xiaojinzi.tally.lib.res.ui.theme.two.KleinBlueAppTheme
import com.app.module.base.support.AppServices

@Composable
fun AppTheme(
    useDarkThemeForSystem: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val themeIndex by AppServices.appInfoSpi.themeIndexStateOb.collectAsState(initial = null)
    val useDarkThemeAdapter = themeIndex?.run {
        when (this) {
            1 -> false
            2 -> true
            else -> useDarkThemeForSystem
        }
    }
    val themeColorIndex by AppServices.appInfoSpi.themeColorIndexStateOb.collectAsState(initial = null)
    val isShowContent = themeIndex != null && themeColorIndex != null
    Box(
        modifier = Modifier
            .fillMaxSize()
            .run {
                if (isShowContent) {
                    this
                } else {
                    this.background(
                        color = if (useDarkThemeForSystem) {
                            one_md_theme_dark_background
                        } else {
                            one_md_theme_light_background
                        },
                    )
                }
            }
            .nothing(),
        contentAlignment = Alignment.TopCenter,
    ) {
        useDarkThemeAdapter?.let {
            themeColorIndex?.let {
                when (themeColorIndex) {
                    Int.MAX_VALUE -> OliveGreenDynamicAppTheme(
                        useDarkTheme = useDarkThemeAdapter,
                        content = content,
                    )

                    1 -> ChinaRedAppTheme(
                        useDarkTheme = useDarkThemeAdapter,
                        content = content,
                    )

                    2 -> KleinBlueAppTheme(
                        useDarkTheme = useDarkThemeAdapter,
                        content = content,
                    )

                    3 -> OliveGreenAppTheme(
                        useDarkTheme = useDarkThemeAdapter,
                        content = content,
                    )

                    4 -> SchenbrunnYellowAppTheme(
                        useDarkTheme = useDarkThemeAdapter,
                        content = content,
                    )

                    5 -> TitianRedAppTheme(
                        useDarkTheme = useDarkThemeAdapter,
                        content = content,
                    )

                    else -> OliveGreenAppTheme(
                        useDarkTheme = useDarkThemeAdapter,
                        content = content,
                    )
                }
            }
        }
    }
}