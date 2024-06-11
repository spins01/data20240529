package com.xiaojinzi.tally.lib.res.ui.theme.five

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable


private val TitianRedLightColors = lightColorScheme(
    primary = five_md_theme_light_primary,
    onPrimary = five_md_theme_light_onPrimary,
    primaryContainer = five_md_theme_light_primaryContainer,
    onPrimaryContainer = five_md_theme_light_onPrimaryContainer,
    secondary = five_md_theme_light_secondary,
    onSecondary = five_md_theme_light_onSecondary,
    secondaryContainer = five_md_theme_light_secondaryContainer,
    onSecondaryContainer = five_md_theme_light_onSecondaryContainer,
    tertiary = five_md_theme_light_tertiary,
    onTertiary = five_md_theme_light_onTertiary,
    tertiaryContainer = five_md_theme_light_tertiaryContainer,
    onTertiaryContainer = five_md_theme_light_onTertiaryContainer,
    error = five_md_theme_light_error,
    errorContainer = five_md_theme_light_errorContainer,
    onError = five_md_theme_light_onError,
    onErrorContainer = five_md_theme_light_onErrorContainer,
    background = five_md_theme_light_background,
    onBackground = five_md_theme_light_onBackground,
    surface = five_md_theme_light_surface,
    onSurface = five_md_theme_light_onSurface,
    surfaceVariant = five_md_theme_light_surfaceVariant,
    onSurfaceVariant = five_md_theme_light_onSurfaceVariant,
    outline = five_md_theme_light_outline,
    inverseOnSurface = five_md_theme_light_inverseOnSurface,
    inverseSurface = five_md_theme_light_inverseSurface,
    inversePrimary = five_md_theme_light_inversePrimary,
    surfaceTint = five_md_theme_light_surfaceTint,
    outlineVariant = five_md_theme_light_outlineVariant,
    scrim = five_md_theme_light_scrim,
)


private val TitianRedDarkColors = darkColorScheme(
    primary = five_md_theme_dark_primary,
    onPrimary = five_md_theme_dark_onPrimary,
    primaryContainer = five_md_theme_dark_primaryContainer,
    onPrimaryContainer = five_md_theme_dark_onPrimaryContainer,
    secondary = five_md_theme_dark_secondary,
    onSecondary = five_md_theme_dark_onSecondary,
    secondaryContainer = five_md_theme_dark_secondaryContainer,
    onSecondaryContainer = five_md_theme_dark_onSecondaryContainer,
    tertiary = five_md_theme_dark_tertiary,
    onTertiary = five_md_theme_dark_onTertiary,
    tertiaryContainer = five_md_theme_dark_tertiaryContainer,
    onTertiaryContainer = five_md_theme_dark_onTertiaryContainer,
    error = five_md_theme_dark_error,
    errorContainer = five_md_theme_dark_errorContainer,
    onError = five_md_theme_dark_onError,
    onErrorContainer = five_md_theme_dark_onErrorContainer,
    background = five_md_theme_dark_background,
    onBackground = five_md_theme_dark_onBackground,
    surface = five_md_theme_dark_surface,
    onSurface = five_md_theme_dark_onSurface,
    surfaceVariant = five_md_theme_dark_surfaceVariant,
    onSurfaceVariant = five_md_theme_dark_onSurfaceVariant,
    outline = five_md_theme_dark_outline,
    inverseOnSurface = five_md_theme_dark_inverseOnSurface,
    inverseSurface = five_md_theme_dark_inverseSurface,
    inversePrimary = five_md_theme_dark_inversePrimary,
    surfaceTint = five_md_theme_dark_surfaceTint,
    outlineVariant = five_md_theme_dark_outlineVariant,
    scrim = five_md_theme_dark_scrim,
)

@Composable
fun TitianRedAppTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable() () -> Unit
) {
    val colors = if (!useDarkTheme) {
        TitianRedLightColors
    } else {
        TitianRedDarkColors
    }

    MaterialTheme(
        colorScheme = colors,
        content = content
    )
}