package com.rahul.zenithra.domain.presentation.faceRecognition

import androidx.annotation.OptIn
import androidx.camera.core.ExperimentalGetImage
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.compose.LocalLifecycleOwner

@OptIn(ExperimentalGetImage::class)
@Composable
fun FaceRecognitionScreen() {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    var isFaceInside by remember { mutableStateOf(false) }

    val cameraPermissionGranted = rememberCameraPermission()

    if (cameraPermissionGranted) {
        val previewView = rememberPreviewView()
        AndroidView(factory = { previewView }, modifier = Modifier.fillMaxSize())

        LaunchedEffect(Unit) {
            setupCamera(
                context = context,
                previewView = previewView,
                lifecycleOwner = lifecycleOwner,
                onFaceInside = { isInside -> isFaceInside = isInside }
            )
        }

        FaceOverlayBox(
            isFaceInside = isFaceInside,
            previewView = previewView,
        )
    } else {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Camera permission required for face recognition.")
        }
    }
}




