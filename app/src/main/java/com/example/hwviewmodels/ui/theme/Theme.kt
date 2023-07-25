package com.example.hwviewmodels.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

data class ExtendedCustomColors(
    val primary: Color,
    val secondary: Color,
    val tertiary: Color,
    val onPrimary: Color,
    val onSecondary: Color,
    val onTertiary: Color,
    val background: Color
)

val LocalExtendedCustomColors = staticCompositionLocalOf {
    ExtendedCustomColors(
        primary = Color.Unspecified,
        secondary = Color.Unspecified,
        tertiary = Color.Unspecified,
        onPrimary = Color.Unspecified,
        onSecondary = Color.Unspecified,
        onTertiary = Color.Unspecified,
        background = Color.Unspecified,
    )
}

@Composable
fun CustomTheme(
    content: @Composable () -> Unit
) {
    val extendedColors = ExtendedCustomColors(
        primary = DarkBlue,
        secondary = DarkBrown,
        tertiary = DirtGreen,
        onPrimary = LigthBlue,
        onSecondary = LigthPink,
        onTertiary = LigthGreen,
        background = White
    )
    CompositionLocalProvider(LocalExtendedCustomColors provides extendedColors) {
        MaterialTheme(
            content = content,
            typography = Typography
        )
    }
}

object CustomTheme{
    val colors: ExtendedCustomColors
    @Composable
    get() = LocalExtendedCustomColors.current
}



private val backroundWhite = darkColorScheme(
    primary = DarkBlue,
    secondary = DarkBrown,
    tertiary = DirtGreen,
    onPrimary = LigthBlue,
    onSecondary = LigthPink,
    onTertiary = LigthGreen,
    background = White,
)

private val backgroundDark = lightColorScheme(
    onPrimary = DarkBlue,
    onSecondary = DarkBrown,
    onTertiary = DirtGreen,
    primary = LigthBlue,
    secondary = LigthPink,
    tertiary = LigthGreen,
    background = DarkGray,
)




private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)
private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun HwViewModelsTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}

