package com.rahul.zenithra.domain.presentation.navigation.route

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.navArgument
import com.google.gson.Gson
import com.rahul.zenithra.domain.model.Manga
import com.rahul.zenithra.domain.presentation.faceRecognition.FaceRecognitionScreen
import com.rahul.zenithra.domain.presentation.manga.MangaDescriptionScreen
import com.rahul.zenithra.domain.presentation.manga.MangaScreen

@Composable
fun HomeNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = BottomNavItem.Manga.route,
        modifier = modifier
    ) {
        composable(BottomNavItem.Manga.route) {
            MangaScreen(
                onMangaClick = { manga ->
                    val mangaJson = Uri.encode(Gson().toJson(manga))
                    navController.navigate("manga_description/$mangaJson")
                }
            )
        }

        composable(BottomNavItem.FaceRecognition.route) {
            FaceRecognitionScreen()
        }

        composable(
            "manga_description/{manga}",
            arguments = listOf(navArgument("manga") { type = NavType.StringType })
        ) { backStackEntry ->
            val json = backStackEntry.arguments?.getString("manga")
            val manga = Gson().fromJson(json, Manga::class.java)
            MangaDescriptionScreen(manga)
        }
    }
}
