package com.xiaojinzi.tally.lib.res.ui.theme.four

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable


private val SchenbrunnLightColors = lightColorScheme(
    primary = four_md_theme_light_primary,
    onPrimary = four_md_theme_light_onPrimary,
    primaryContainer = four_md_theme_light_primaryContainer,
    onPrimaryContainer = four_md_theme_light_onPrimaryContainer,
    secondary = four_md_theme_light_secondary,
    onSecondary = four_md_theme_light_onSecondary,
    secondaryContainer = four_md_theme_light_secondaryContainer,
    onSecondaryContainer = four_md_theme_light_onSecondaryContainer,
    tertiary = four_md_theme_light_tertiary,
    onTertiary = four_md_theme_light_onTertiary,
    tertiaryContainer = four_md_theme_light_tertiaryContainer,
    onTertiaryContainer = four_md_theme_light_onTertiaryContainer,
    error = four_md_theme_light_error,
    errorContainer = four_md_theme_light_errorContainer,
    onError = four_md_theme_light_onError,
    onErrorContainer = four_md_theme_light_onErrorContainer,
    background = four_md_theme_light_background,
    onBackground = four_md_theme_light_onBackground,
    surface = four_md_theme_light_surface,
    onSurface = four_md_theme_light_onSurface,
    surfaceVariant = four_md_theme_light_surfaceVariant,
    onSurfaceVariant = four_md_theme_light_onSurfaceVariant,
    outline = four_md_theme_light_outline,
    inverseOnSurface = four_md_theme_light_inverseOnSurface,
    inverseSurface = four_md_theme_light_inverseSurface,
    inversePrimary = four_md_theme_light_inversePrimary,
    surfaceTint = four_md_theme_light_surfaceTint,
    outlineVariant = four_md_theme_light_outlineVariant,
    scrim = four_md_theme_light_scrim,
)


private val SchenbrunnDarkColors = darkColorScheme(
    primary = four_md_theme_dark_primary,
    onPrimary = four_md_theme_dark_onPrimary,
    primaryContainer = four_md_theme_dark_primaryContainer,
    onPrimaryContainer = four_md_theme_dark_onPrimaryContainer,
    secondary = four_md_theme_dark_secondary,
    onSecondary = four_md_theme_dark_onSecondary,
    secondaryContainer = four_md_theme_dark_secondaryContainer,
    onSecondaryContainer = four_md_theme_dark_onSecondaryContainer,
    tertiary = four_md_theme_dark_tertiary,
    onTertiary = four_md_theme_dark_onTertiary,
    tertiaryContainer = four_md_theme_dark_tertiaryContainer,
    onTertiaryContainer = four_md_theme_dark_onTertiaryContainer,
    error = four_md_theme_dark_error,
    errorContainer = four_md_theme_dark_errorContainer,
    onError = four_md_theme_dark_onError,
    onErrorContainer = four_md_theme_dark_onErrorContainer,
    background = four_md_theme_dark_background,
    onBackground = four_md_theme_dark_onBackground,
    surface = four_md_theme_dark_surface,
    onSurface = four_md_theme_dark_onSurface,
    surfaceVariant = four_md_theme_dark_surfaceVariant,
    onSurfaceVariant = four_md_theme_dark_onSurfaceVariant,
    outline = four_md_theme_dark_outline,
    inverseOnSurface = four_md_theme_dark_inverseOnSurface,
    inverseSurface = four_md_theme_dark_inverseSurface,
    inversePrimary = four_md_theme_dark_inversePrimary,
    surfaceTint = four_md_theme_dark_surfaceTint,
    outlineVariant = four_md_theme_dark_outlineVariant,
    scrim = four_md_theme_dark_scrim,
)

@Composable
fun SchenbrunnYellowAppTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable() () -> Unit
) {
    val colors = if (!useDarkTheme) {
        SchenbrunnLightColors
    } else {
        SchenbrunnDarkColors
    }

    MaterialTheme(
        colorScheme = colors,
        content = content
    )
}