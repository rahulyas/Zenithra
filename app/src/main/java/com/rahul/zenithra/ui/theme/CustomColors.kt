package com.rahul.zenithra.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

data class CustomColors(
    val primary: Color,
    val primaryBackground: Color,
    val secondaryBackground: Color,
    val whiteContentBackground: Color,
    val lightBackground: Color,
    val active: Color,
    val primaryText: Color,
    val secondaryText: Color,
    val placeholderText: Color,
    val pureWhite: Color,
    val danger: Color,
    val primaryIcon: Color,
    val darkIcon: Color,
    val iconOnDarkBackground: Color,
    val iconOnLightBackground: Color,
    val iconOnTabBar: Color,
    val inputBorder: Color,
    val primaryBorder: Color,
    val popupBackground: Color,
    val popupContentBackground: Color,
    val cardBackground: Color
)

val LocalCustomColors = staticCompositionLocalOf {
    CustomColors(
        primary = Color.Unspecified,
        primaryBackground = Color.Unspecified,
        secondaryBackground = Color.Unspecified,
        whiteContentBackground = Color.Unspecified,
        lightBackground = Color.Unspecified,
        active = Color.Unspecified,
        primaryText = Color.Unspecified,
        secondaryText = Color.Unspecified,
        placeholderText = Color.Unspecified,
        pureWhite = Color.Unspecified,
        danger = Color.Unspecified,
        primaryIcon = Color.Unspecified,
        darkIcon = Color.Unspecified,
        iconOnDarkBackground = Color.Unspecified,
        iconOnLightBackground = Color.Unspecified,
        iconOnTabBar = Color.Unspecified,
        inputBorder = Color.Unspecified,
        primaryBorder = Color.Unspecified,
        popupBackground = Color.Unspecified,
        popupContentBackground = Color.Unspecified,
        cardBackground = Color.Unspecified
    )
}

val lightTheme = CustomColors(
    primary = Green70,
    primaryBackground = White,
    secondaryBackground = OuterSpace,
    whiteContentBackground = White,
    lightBackground = Gray95,
    active = SnowDrift,
    primaryText = Gray75,
    secondaryText = Gray55,
    placeholderText = Silver,
    pureWhite = White,
    danger = Red60,
    primaryIcon = Aluminium,
    darkIcon = Gray80,
    iconOnDarkBackground = Gray60,
    iconOnLightBackground = Manatee,
    iconOnTabBar = Gray75,
    inputBorder = Celeste,
    primaryBorder = Gray90,
    popupBackground = AthensGray,
    popupContentBackground = Gray70,
    cardBackground = CardBackground
)

val darkTheme = CustomColors(
    primary = Green70,
    primaryBackground = CharlestonGreen,
    secondaryBackground = EerieBlack,
    whiteContentBackground = Mako,
    lightBackground = Mako,
    active = Mako,
    primaryText = White,
    secondaryText = White.copy(0.76f),
    placeholderText = White.copy(0.6f),
    pureWhite = White,
    danger = Red60,
    primaryIcon = White,
    darkIcon = White,
    iconOnDarkBackground = White,
    iconOnLightBackground = White,
    iconOnTabBar = White,
    inputBorder = shuttleGray,
    primaryBorder = Emperor,
    popupBackground = EerieBlack,
    popupContentBackground = Mako,
    cardBackground = CardBackground
)

object CustomTheme {
    val colors: CustomColors
        @Composable
        get() = LocalCustomColors.current
}

