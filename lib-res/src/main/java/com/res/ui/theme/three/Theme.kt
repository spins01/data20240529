package com.xiaojinzi.tally.lib.res.ui.theme.three

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext


private val OliveGreenLightColors = lightColorScheme(
    primary = three_md_theme_light_primary,
    onPrimary = three_md_theme_light_onPrimary,
    primaryContainer = three_md_theme_light_primaryContainer,
    onPrimaryContainer = three_md_theme_light_onPrimaryContainer,
    secondary = three_md_theme_light_secondary,
    onSecondary = three_md_theme_light_onSecondary,
    secondaryContainer = three_md_theme_light_secondaryContainer,
    onSecondaryContainer = three_md_theme_light_onSecondaryContainer,
    tertiary = three_md_theme_light_tertiary,
    onTertiary = three_md_theme_light_onTertiary,
    tertiaryContainer = three_md_theme_light_tertiaryContainer,
    onTertiaryContainer = three_md_theme_light_onTertiaryContainer,
    error = three_md_theme_light_error,
    errorContainer = three_md_theme_light_errorContainer,
    onError = three_md_theme_light_onError,
    onErrorContainer = three_md_theme_light_onErrorContainer,
    background = three_md_theme_light_background,
    onBackground = three_md_theme_light_onBackground,
    surface = three_md_theme_light_surface,
    onSurface = three_md_theme_light_onSurface,
    surfaceVariant = three_md_theme_light_surfaceVariant,
    onSurfaceVariant = three_md_theme_light_onSurfaceVariant,
    outline = three_md_theme_light_outline,
    inverseOnSurface = three_md_theme_light_inverseOnSurface,
    inverseSurface = three_md_theme_light_inverseSurface,
    inversePrimary = three_md_theme_light_inversePrimary,
    surfaceTint = three_md_theme_light_surfaceTint,
    outlineVariant = three_md_theme_light_outlineVariant,
    scrim = three_md_theme_light_scrim,
)


private val OliveGreenDarkColors = darkColorScheme(
    primary = three_md_theme_dark_primary,
    onPrimary = three_md_theme_dark_onPrimary,
    primaryContainer = three_md_theme_dark_primaryContainer,
    onPrimaryContainer = three_md_theme_dark_onPrimaryContainer,
    secondary = three_md_theme_dark_secondary,
    onSecondary = three_md_theme_dark_onSecondary,
    secondaryContainer = three_md_theme_dark_secondaryContainer,
    onSecondaryContainer = three_md_theme_dark_onSecondaryContainer,
    tertiary = three_md_theme_dark_tertiary,
    onTertiary = three_md_theme_dark_onTertiary,
    tertiaryContainer = three_md_theme_dark_tertiaryContainer,
    onTertiaryContainer = three_md_theme_dark_onTertiaryContainer,
    error = three_md_theme_dark_error,
    errorContainer = three_md_theme_dark_errorContainer,
    onError = three_md_theme_dark_onError,
    onErrorContainer = three_md_theme_dark_onErrorContainer,
    background = three_md_theme_dark_background,
    onBackground = three_md_theme_dark_onBackground,
    surface = three_md_theme_dark_surface,
    onSurface = three_md_theme_dark_onSurface,
    surfaceVariant = three_md_theme_dark_surfaceVariant,
    onSurfaceVariant = three_md_theme_dark_onSurfaceVariant,
    outline = three_md_theme_dark_outline,
    inverseOnSurface = three_md_theme_dark_inverseOnSurface,
    inverseSurface = three_md_theme_dark_inverseSurface,
    inversePrimary = three_md_theme_dark_inversePrimary,
    surfaceTint = three_md_theme_dark_surfaceTint,
    outlineVariant = three_md_theme_dark_outlineVariant,
    scrim = three_md_theme_dark_scrim,
)

/**
 * 橄榄绿的主题颜色
 */
@Composable
fun OliveGreenAppTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable() () -> Unit
) {
    val colors = if (!useDarkTheme) {
        OliveGreenLightColors
    } else {
        OliveGreenDarkColors
    }

    MaterialTheme(
        colorScheme = colors,
        content = content
    )
}

@Composable
fun OliveGreenDynamicAppTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val context = LocalContext.current
    val colors = if (useDarkTheme) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            dynamicDarkColorScheme(context = context)
        } else {
            OliveGreenDarkColors
        }
    } else {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            dynamicLightColorScheme(context = context)
        } else {
            OliveGreenLightColors
        }
    }

    MaterialTheme(
        colorScheme = colors,
        content = content
    )
}