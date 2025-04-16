package com.rahul.zenithra.domain.presentation.faceRecognition

import android.Manifest
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.LaunchedEffect

@Composable
fun rememberCameraPermission(): Boolean {
    val context = LocalContext.current
    val permissionGranted = remember {
        mutableStateOf(
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED
        )
    }

    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { granted -> permissionGranted.value = granted }

    LaunchedEffect(Unit) {
        if (!permissionGranted.value) {
            permissionLauncher.launch(Manifest.permission.CAMERA)
        }
    }

    return permissionGranted.value
}
