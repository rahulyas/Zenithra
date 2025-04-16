package com.rahul.zenithra.domain.presentation.home

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.rahul.zenithra.core.common.CustomBottomBar
import com.rahul.zenithra.domain.presentation.navigation.route.BottomNavItem
import com.rahul.zenithra.domain.presentation.navigation.route.HomeNavGraph

@Composable
fun HomeScreen() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = Color.Black,
            ) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route ?: BottomNavItem.Manga.route

                CustomBottomBar(
                    selectedRoute = currentRoute,
                    onItemSelected = { route ->
                        if (currentRoute != route) {
                            navController.navigate(route) {
                                popUpTo(navController.graph.startDestinationId) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    }
                )
            }

        }

    ) { padding ->
        HomeNavGraph(navController = navController, modifier = Modifier.padding(padding))
    }
}

