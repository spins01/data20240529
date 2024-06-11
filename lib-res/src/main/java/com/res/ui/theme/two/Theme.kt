package com.xiaojinzi.tally.lib.res.ui.theme.two

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable


private val KleinBlueLightColors = lightColorScheme(
    primary = two_md_theme_light_primary,
    onPrimary = two_md_theme_light_onPrimary,
    primaryContainer = two_md_theme_light_primaryContainer,
    onPrimaryContainer = two_md_theme_light_onPrimaryContainer,
    secondary = two_md_theme_light_secondary,
    onSecondary = two_md_theme_light_onSecondary,
    secondaryContainer = two_md_theme_light_secondaryContainer,
    onSecondaryContainer = two_md_theme_light_onSecondaryContainer,
    tertiary = two_md_theme_light_tertiary,
    onTertiary = two_md_theme_light_onTertiary,
    tertiaryContainer = two_md_theme_light_tertiaryContainer,
    onTertiaryContainer = two_md_theme_light_onTertiaryContainer,
    error = two_md_theme_light_error,
    errorContainer = two_md_theme_light_errorContainer,
    onError = two_md_theme_light_onError,
    onErrorContainer = two_md_theme_light_onErrorContainer,
    background = two_md_theme_light_background,
    onBackground = two_md_theme_light_onBackground,
    surface = two_md_theme_light_surface,
    onSurface = two_md_theme_light_onSurface,
    surfaceVariant = two_md_theme_light_surfaceVariant,
    onSurfaceVariant = two_md_theme_light_onSurfaceVariant,
    outline = two_md_theme_light_outline,
    inverseOnSurface = two_md_theme_light_inverseOnSurface,
    inverseSurface = two_md_theme_light_inverseSurface,
    inversePrimary = two_md_theme_light_inversePrimary,
    surfaceTint = two_md_theme_light_surfaceTint,
    outlineVariant = two_md_theme_light_outlineVariant,
    scrim = two_md_theme_light_scrim,
)


private val KleinBlueDarkColors = darkColorScheme(
    primary = two_md_theme_dark_primary,
    onPrimary = two_md_theme_dark_onPrimary,
    primaryContainer = two_md_theme_dark_primaryContainer,
    onPrimaryContainer = two_md_theme_dark_onPrimaryContainer,
    secondary = two_md_theme_dark_secondary,
    onSecondary = two_md_theme_dark_onSecondary,
    secondaryContainer = two_md_theme_dark_secondaryContainer,
    onSecondaryContainer = two_md_theme_dark_onSecondaryContainer,
    tertiary = two_md_theme_dark_tertiary,
    onTertiary = two_md_theme_dark_onTertiary,
    tertiaryContainer = two_md_theme_dark_tertiaryContainer,
    onTertiaryContainer = two_md_theme_dark_onTertiaryContainer,
    error = two_md_theme_dark_error,
    errorContainer = two_md_theme_dark_errorContainer,
    onError = two_md_theme_dark_onError,
    onErrorContainer = two_md_theme_dark_onErrorContainer,
    background = two_md_theme_dark_background,
    onBackground = two_md_theme_dark_onBackground,
    surface = two_md_theme_dark_surface,
    onSurface = two_md_theme_dark_onSurface,
    surfaceVariant = two_md_theme_dark_surfaceVariant,
    onSurfaceVariant = two_md_theme_dark_onSurfaceVariant,
    outline = two_md_theme_dark_outline,
    inverseOnSurface = two_md_theme_dark_inverseOnSurface,
    inverseSurface = two_md_theme_dark_inverseSurface,
    inversePrimary = two_md_theme_dark_inversePrimary,
    surfaceTint = two_md_theme_dark_surfaceTint,
    outlineVariant = two_md_theme_dark_outlineVariant,
    scrim = two_md_theme_dark_scrim,
)

@Composable
fun KleinBlueAppTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable() () -> Unit
) {
    val colors = if (!useDarkTheme) {
        KleinBlueLightColors
    } else {
        KleinBlueDarkColors
    }

    MaterialTheme(
        colorScheme = colors,
        content = content
    )
}