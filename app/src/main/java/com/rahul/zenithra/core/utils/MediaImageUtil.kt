package com.rahul.zenithra.core.utils

import android.content.Context
import android.media.Image
import android.graphics.ImageFormat
import android.graphics.YuvImage
import android.graphics.Rect
import java.io.ByteArrayOutputStream
import android.graphics.BitmapFactory
import androidx.camera.lifecycle.ProcessCameraProvider
import com.google.mediapipe.framework.image.MPImage
import com.google.mediapipe.framework.image.BitmapImageBuilder

object MediaImageUtil {
    fun mediaImageToMpImage(mediaImage: Image): MPImage {
        // Convert YUV image to Bitmap
        val yuvBytes = yuv420ToNv21(mediaImage)
        val yuvImage = YuvImage(yuvBytes, ImageFormat.NV21, mediaImage.width, mediaImage.height, null)
        val out = ByteArrayOutputStream()
        yuvImage.compressToJpeg(Rect(0, 0, mediaImage.width, mediaImage.height), 100, out)
        val imageBytes = out.toByteArray()
        val bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)

        // Create MPImage from Bitmap
        return BitmapImageBuilder(bitmap).build()
    }

    private fun yuv420ToNv21(image: Image): ByteArray {
        val yBuffer = image.planes[0].buffer
        val uBuffer = image.planes[1].buffer
        val vBuffer = image.planes[2].buffer

        val ySize = yBuffer.remaining()
        val uSize = uBuffer.remaining()
        val vSize = vBuffer.remaining()

        val nv21 = ByteArray(ySize + uSize + vSize)

        yBuffer.get(nv21, 0, ySize)
        vBuffer.get(nv21, ySize, vSize)
        uBuffer.get(nv21, ySize + vSize, uSize)

        return nv21
    }
}

fun Context.getCameraProvider(): ProcessCameraProvider {
    return ProcessCameraProvider.getInstance(this).get()
}
