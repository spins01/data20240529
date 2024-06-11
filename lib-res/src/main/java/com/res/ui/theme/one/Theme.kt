package com.xiaojinzi.tally.lib.res.ui.theme.one

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable


private val ChinaRedLightColors = lightColorScheme(
    primary = one_md_theme_light_primary,
    onPrimary = one_md_theme_light_onPrimary,
    primaryContainer = one_md_theme_light_primaryContainer,
    onPrimaryContainer = one_md_theme_light_onPrimaryContainer,
    secondary = one_md_theme_light_secondary,
    onSecondary = one_md_theme_light_onSecondary,
    secondaryContainer = one_md_theme_light_secondaryContainer,
    onSecondaryContainer = one_md_theme_light_onSecondaryContainer,
    tertiary = one_md_theme_light_tertiary,
    onTertiary = one_md_theme_light_onTertiary,
    tertiaryContainer = one_md_theme_light_tertiaryContainer,
    onTertiaryContainer = one_md_theme_light_onTertiaryContainer,
    error = one_md_theme_light_error,
    errorContainer = one_md_theme_light_errorContainer,
    onError = one_md_theme_light_onError,
    onErrorContainer = one_md_theme_light_onErrorContainer,
    background = one_md_theme_light_background,
    onBackground = one_md_theme_light_onBackground,
    surface = one_md_theme_light_surface,
    onSurface = one_md_theme_light_onSurface,
    surfaceVariant = one_md_theme_light_surfaceVariant,
    onSurfaceVariant = one_md_theme_light_onSurfaceVariant,
    outline = one_md_theme_light_outline,
    inverseOnSurface = one_md_theme_light_inverseOnSurface,
    inverseSurface = one_md_theme_light_inverseSurface,
    inversePrimary = one_md_theme_light_inversePrimary,
    surfaceTint = one_md_theme_light_surfaceTint,
    outlineVariant = one_md_theme_light_outlineVariant,
    scrim = one_md_theme_light_scrim,
)


private val ChinaRedDarkColors = darkColorScheme(
    primary = one_md_theme_dark_primary,
    onPrimary = one_md_theme_dark_onPrimary,
    primaryContainer = one_md_theme_dark_primaryContainer,
    onPrimaryContainer = one_md_theme_dark_onPrimaryContainer,
    secondary = one_md_theme_dark_secondary,
    onSecondary = one_md_theme_dark_onSecondary,
    secondaryContainer = one_md_theme_dark_secondaryContainer,
    onSecondaryContainer = one_md_theme_dark_onSecondaryContainer,
    tertiary = one_md_theme_dark_tertiary,
    onTertiary = one_md_theme_dark_onTertiary,
    tertiaryContainer = one_md_theme_dark_tertiaryContainer,
    onTertiaryContainer = one_md_theme_dark_onTertiaryContainer,
    error = one_md_theme_dark_error,
    errorContainer = one_md_theme_dark_errorContainer,
    onError = one_md_theme_dark_onError,
    onErrorContainer = one_md_theme_dark_onErrorContainer,
    background = one_md_theme_dark_background,
    onBackground = one_md_theme_dark_onBackground,
    surface = one_md_theme_dark_surface,
    onSurface = one_md_theme_dark_onSurface,
    surfaceVariant = one_md_theme_dark_surfaceVariant,
    onSurfaceVariant = one_md_theme_dark_onSurfaceVariant,
    outline = one_md_theme_dark_outline,
    inverseOnSurface = one_md_theme_dark_inverseOnSurface,
    inverseSurface = one_md_theme_dark_inverseSurface,
    inversePrimary = one_md_theme_dark_inversePrimary,
    surfaceTint = one_md_theme_dark_surfaceTint,
    outlineVariant = one_md_theme_dark_outlineVariant,
    scrim = one_md_theme_dark_scrim,
)

@Composable
fun ChinaRedAppTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable() () -> Unit
) {
    val colors = if (!useDarkTheme) {
        ChinaRedLightColors
    } else {
        ChinaRedDarkColors
    }

    MaterialTheme(
        colorScheme = colors,
        content = content
    )
}