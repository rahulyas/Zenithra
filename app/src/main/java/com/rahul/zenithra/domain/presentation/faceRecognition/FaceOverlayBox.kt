package com.rahul.zenithra.domain.presentation.faceRecognition

import android.content.Context
import android.graphics.Bitmap
import androidx.camera.view.PreviewView
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@Composable
fun FaceOverlayBox(
    isFaceInside: Boolean,
    previewView: PreviewView,
) {
    val context = LocalContext.current
    val showSaveSuccess = remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        val color = if (isFaceInside) Color.Green else Color.Red
        Box(
            modifier = Modifier
                .size(400.dp)
                .border(4.dp, color, RoundedCornerShape(8.dp))
                .clickable(enabled = isFaceInside) {
                    val bitmap = previewView.bitmap
                    if (bitmap != null) {
                        val filename = "face_${System.currentTimeMillis()}.jpg"
                        val stream = context.openFileOutput(filename, Context.MODE_PRIVATE)
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
                        stream.close()
                        showSaveSuccess.value = true
                    }
                }
        )
        if (showSaveSuccess.value) {
            Text(
                "Image saved successfully!",
                color = Color.White,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 16.dp)
            )
        }
    }
}