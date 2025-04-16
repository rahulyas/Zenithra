package com.rahul.zenithra.domain.presentation.navigation

sealed class Routes(val route: String) {
    data object Login : Routes("login")
    data object Home : Routes("home")
    data object SignUp : Routes("signup")
    data object MangaDescription : Routes("mangaDescription")
    // Add more routes as needed
}