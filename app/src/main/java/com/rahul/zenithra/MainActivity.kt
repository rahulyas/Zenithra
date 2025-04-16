package com.rahul.zenithra

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.rahul.zenithra.domain.presentation.navigation.Routes
import com.rahul.zenithra.domain.presentation.navigation.route.SetupNavGraph
import com.rahul.zenithra.domain.presentation.splash.SplashScreenUI
import com.rahul.zenithra.domain.presentation.splash.SplashViewModel
import com.rahul.zenithra.ui.theme.ZenithraTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val splashViewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ZenithraTheme {
                val navController = rememberNavController()
                val isLoggedIn by splashViewModel.isLoggedIn.collectAsState()

                when (isLoggedIn) {
                    true -> SetupNavGraph(navController, startDestination = Routes.Home.route)
                    false -> SetupNavGraph(navController, startDestination = Routes.Login.route)
                    null -> {
                        // Splash or loading screen
                        SplashScreenUI()
//                        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
//                            CircularProgressIndicator()
//                        }
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ZenithraTheme {
    }
}