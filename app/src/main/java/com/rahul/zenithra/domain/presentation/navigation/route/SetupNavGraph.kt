package com.rahul.zenithra.domain.presentation.navigation.route

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.rahul.zenithra.domain.presentation.home.HomeScreen
import com.rahul.zenithra.domain.presentation.login.LoginScreen
import com.rahul.zenithra.domain.presentation.navigation.Routes
import com.rahul.zenithra.domain.presentation.signUp.SignUpScreen

@Composable
fun SetupNavGraph(navController: NavHostController, startDestination: String) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Routes.Login.route) {
            LoginScreen(
                onSignInSuccess = {
                    navController.navigate(Routes.Home.route) {
                        popUpTo(0) { inclusive = true }
                        launchSingleTop = true
                    }
                },
                onSignUpClick = {
                    navController.navigate(Routes.SignUp.route)
                }
            )
        }

        composable(Routes.Home.route) {
            HomeScreen(
                /*onLogoutClick = {
                    navController.navigate(Routes.Login.route) {
                        popUpTo(Routes.Home.route) { inclusive = true }
                    }
                }*/
            )
        }


        composable(Routes.SignUp.route) {
            SignUpScreen(
                onSignUpSuccess = {
                    navController.navigate(Routes.Login.route) {
                        popUpTo(0) { inclusive = true }
                        launchSingleTop = true
                    }
                },
                onSignInClick = {
                    navController.navigate(Routes.Login.route)
                }
            )
        }
    }
}