package com.rahul.zenithra.ui.theme

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

private val LightColorScheme = lightColorScheme(
    primary = Green70,              // Green70 = Color(0xFFA0D76A)
    onPrimary = Black,        // Text color on primary (buttons, etc.)
    primaryContainer = Gray60,      // Gray60 = Color(0xFFBFC7D2)
    onPrimaryContainer = Black90,   // Black90 = Color(0xFF101820)

    secondary = Gray75,             // Gray75 = Color(0xFF3A414A)
    onSecondary = White,      // Text color on secondary buttons
    secondaryContainer = Gray80,    // Gray80 = Color(0xFF54565A)

    tertiary = Gray65,              // Gray65 = Color(0xFF57606B)
    onTertiary = Black90,           // Text color on tertiary (cards)

    background = Color.Transparent,   // For background color
    onBackground = Gray95,          // Gray95 = Color(0xFFF0F0F5)

    surface = Gray90,               // Gray90 = Color(0xFFE2E2E7)
    onSurface = Gray70,            // Gray70 = Color(0xFF3F4851)

    surfaceVariant = Silver80,      // Silver80 = Color(0xFFDCDCDC)
    onSurfaceVariant = Navy90,     // Text color on card border

    outline = Gray50,               //  Gray50 = Color(0xFF89919C)
    inverseOnSurface = Gray55,      //  Gray55 = Color(0xFF707882)
    inverseSurface = Navy85,        //  Navy85 = Color(0xFF212C37)
    inversePrimary = Gray85,        //  Gray85 = Color(0xFF5C5E62)

    error = Color(0xFFB00020),      // Standard Material Design error color
    onError = White,          // Text color on error
    errorContainer = Color(0xFFFFDAD4), // Light error container color
    onErrorContainer = Color(0xFF410E0B) // Dark error container color
)


private val DarkColorScheme = darkColorScheme(
    primary = Color(0xffA0D76A),
    background = Color(0xFF2A2B32),
    secondaryContainer = Color(0xFF101820),
    onSecondaryContainer = Color(0xff212C37),
    tertiaryContainer = Color(0xff44474f),
    primaryContainer = Gray60.copy(
        alpha = 0.5f
    ),
    outline = Color(0xff505050),

    )

@Composable
fun ZenithraTheme(
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