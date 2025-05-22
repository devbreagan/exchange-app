package com.gbreagan.challenge.exchange.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

private val DarkColorScheme = darkColorScheme(
    primary = BankOrange,
    secondary = BlueGrey,
    tertiary = SkyBlue,
    onPrimary = BankOrange

)

private val LightColorScheme = lightColorScheme(
    primary = BankBlue,
    secondary = Color(0xFF707A92),
    tertiary = Color(0xFFAD1457),
    onPrimary = BankBlue

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
fun ExchangeTheme(
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

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}

object BankStyles {
    val colorPositive: Color = Color(0xFF00B407)
    val colorNegative: Color = Color(0xFFFF0000)
    val colorNeutral: Color = Color(0xFF404F66)

    @Composable
    fun textDefault() = TextStyle(
        fontSize = 20.sp,
        lineHeight = 22.sp,
        fontWeight = FontWeight.W600
    )

    @Composable
    fun textBodySmall() = MaterialTheme.typography.bodySmall

    @Composable
    fun textBodyMedium() = MaterialTheme.typography.bodyMedium

    @Composable
    fun textBodyLarge() = MaterialTheme.typography.bodyLarge

    @Composable
    fun textTitleSmall() = MaterialTheme.typography.titleSmall

    @Composable
    fun textTitleMedium() = MaterialTheme.typography.titleMedium

    @Composable
    fun textTitleLarge() = MaterialTheme.typography.titleLarge

    @Composable
    fun textHeadSmall() = MaterialTheme.typography.headlineSmall

    @Composable
    fun textHeadMedium() = MaterialTheme.typography.headlineMedium

    @Composable
    fun textHeadLarge() = MaterialTheme.typography.headlineLarge
}