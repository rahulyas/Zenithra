package com.rahul.zenithra.core.common

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rahul.zenithra.domain.presentation.navigation.route.BottomNavItem

@Composable
fun CustomBottomBar(
    selectedRoute: String,
    onItemSelected: (String) -> Unit
) {
    val items = listOf(BottomNavItem.Manga, BottomNavItem.FaceRecognition)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Black)
            .padding(12.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        items.forEach { item ->
            val isSelected = selectedRoute == item.route
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(24.dp))
                    .background(if (isSelected) Color.White else Color(0xFF2C2C2E)) // dark gray for unselected
                    .clickable { onItemSelected(item.route) }
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    if (!isSelected) {
                        Icon(
                            painter = painterResource(id = item.iconResId),
                            contentDescription = item.title,
                            tint = Color.White,
                            modifier = Modifier
                                .size(20.dp)
                                .align(Alignment.CenterHorizontally) // optional if Column already centers
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                    }

                    Text(
                        text = item.title,
                        color = if (isSelected) Color.Black else Color.White,
                        fontSize = if (isSelected) 16.sp else 12.sp,
                        fontWeight = if (isSelected) FontWeight.Medium else FontWeight.Normal
                    )
                }

            }
        }
    }
}
