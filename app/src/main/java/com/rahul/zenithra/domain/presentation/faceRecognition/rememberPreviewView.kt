package com.rahul.zenithra.domain.presentation.faceRecognition

import android.content.Context
import androidx.annotation.OptIn
import androidx.camera.core.CameraSelector
import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.Preview
import androidx.camera.view.PreviewView
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import com.google.mediapipe.tasks.components.containers.Detection
import com.google.mediapipe.tasks.core.BaseOptions
import com.google.mediapipe.tasks.vision.facedetector.FaceDetector
import com.rahul.zenithra.core.utils.MediaImageUtil
import com.rahul.zenithra.core.utils.getCameraProvider

@Composable
fun rememberPreviewView(): PreviewView {
    val context = LocalContext.current
    return remember {
        PreviewView(context).apply {
            scaleType = PreviewView.ScaleType.FILL_CENTER
        }
    }
}

@OptIn(ExperimentalGetImage::class)
suspend fun setupCamera(
    context: Context,
    previewView: PreviewView,
    lifecycleOwner: LifecycleOwner,
    onFaceInside: (Boolean) -> Unit
) {
    val options = FaceDetector.FaceDetectorOptions.builder()
        .setMinDetectionConfidence(0.5f)
        .setBaseOptions(
            BaseOptions.builder()
                .setModelAssetPath("models/blaze_face_short_range.tflite")
                .build()
        )
        .build()

    val faceDetector = FaceDetector.createFromOptions(context, options)
    val cameraProvider = context.getCameraProvider()

    val preview = Preview.Builder().build().also {
        it.setSurfaceProvider(previewView.surfaceProvider)
    }

    val imageAnalyzer = ImageAnalysis.Builder()
        .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
        .build()

    imageAnalyzer.setAnalyzer(ContextCompat.getMainExecutor(context)) { imageProxy ->
        val mediaImage = imageProxy.image
        if (mediaImage != null) {
            val mpImage = MediaImageUtil.mediaImageToMpImage(mediaImage)
            val result = faceDetector.detect(mpImage)
            val faces: List<Detection> = result.detections()

            val imageWidth = mediaImage.width.toFloat()
            val imageHeight = mediaImage.height.toFloat()

            val faceDetectedInside = faces.any { detection ->
                val box = detection.boundingBox()
                val left = box.left / imageWidth
                val right = box.right / imageWidth
                val top = box.top / imageHeight
                val bottom = box.bottom / imageHeight
                left > 0.2f && right < 0.8f && top > 0.2f && bottom < 0.8f
            }

            onFaceInside(faceDetectedInside)
        }
        imageProxy.close()
    }

    val cameraSelector = CameraSelector.Builder()
        .requireLensFacing(CameraSelector.LENS_FACING_FRONT)
        .build()

    cameraProvider.unbindAll()
    cameraProvider.bindToLifecycle(
        lifecycleOwner,
        cameraSelector,
        preview,
        imageAnalyzer
    )
}