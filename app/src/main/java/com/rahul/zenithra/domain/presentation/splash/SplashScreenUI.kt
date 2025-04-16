package com.rahul.zenithra.domain.presentation.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.rahul.zenithra.ui.theme.CardBackground
import com.rahul.zenithra.ui.theme.Icon

@Composable
fun SplashScreenUI() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(CardBackground), // Or your theme background
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id = Icon.appIcon), // Your logo
                contentDescription = "App Logo",
                modifier = Modifier.size(120.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text("Zenithra", style = MaterialTheme.typography.headlineMedium, color = Color.White)
        }
    }
}
