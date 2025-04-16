package com.rahul.zenithra.domain.presentation.navigation.route

import com.rahul.zenithra.ui.theme.Icon

sealed class BottomNavItem(
    val route: String,
    val iconResId: Int,
    val title: String
) {
    data object Manga : BottomNavItem("manga", Icon.openBook, "Manga")
    data object FaceRecognition : BottomNavItem("face", Icon.face, "Face")
}

