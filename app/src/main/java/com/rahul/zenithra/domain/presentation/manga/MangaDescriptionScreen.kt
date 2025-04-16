package com.rahul.zenithra.domain.presentation.manga

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.rahul.zenithra.domain.model.Manga

@Composable
fun MangaDescriptionScreen(manga: Manga) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Top
        ) {
            Image(
                painter = rememberAsyncImagePainter(manga.thumb),
                contentDescription = null,
                modifier = Modifier
                    .width(120.dp)
                    .height(170.dp)
                    .clip(RoundedCornerShape(8.dp))
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = manga.title ?: "No Title",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )

                Text(
                    text = manga.subTitle ?: "",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }

            IconButton(onClick = { /* TODO: Add to favorites */ }) {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = "Favorite",
                    tint = Color.White
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = manga.summary ?: "No Summary",
            fontSize = 14.sp,
            color = Color.White
        )
    }
}
