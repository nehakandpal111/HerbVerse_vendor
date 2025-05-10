package com.example.herbverse_vendor.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF81C784),
    secondary = Color(0xFFA5D6A7),
    tertiary = Color(0xFFDCE775),
    background = Color(0xFF1E3B1E),
    surface = Color(0xFF1E3B1E),
)

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF4CAF50),
    secondary = Color(0xFF8BC34A),
    tertiary = Color(0xFFCDDC39),
    background = Color(0xFFEDF7ED),
    surface = Color(0xFFEDF7ED),
    onPrimary = Color(0xFFEDF7ED),
    onSecondary = Color(0xFFD4E8D4),
    onTertiary = Color(0xFFD4E8D4),
    onBackground = Color(0xFF1E3B1E),
    onSurface = Color(0xFF1E3B1E)
)

@Composable
fun HerbverseVendorTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}