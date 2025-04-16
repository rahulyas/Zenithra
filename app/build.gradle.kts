plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    kotlin("plugin.serialization") version "1.9.24"
    alias(libs.plugins.devToolsKspGoogle)
    alias(libs.plugins.daggerHiltAndroid)
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "com.rahul.zenithra"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.rahul.zenithra"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.rules)
    implementation(libs.core)
    implementation(libs.androidx.ui.android)
    implementation(libs.androidx.ui.graphics.android)
    implementation(libs.androidx.foundation.android)
    implementation(libs.androidx.foundation.layout.android)
    implementation(libs.vision.common)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    implementation(libs.bundles.testing)
    implementation(libs.bundles.compose)
    //kotlinx serialization
    implementation(libs.kotlinx.serialization.json)
    implementation( libs.androidx.lifecycle.runtime.compose)
    //dagger hilt
    ksp(libs.com.google.dagger.hilt.compiler)
    implementation(libs.bundles.daggerHilt)

    //room
    ksp(libs.androidx.room.compiler)
    implementation(libs.bundles.room)

    // Retrofit and Gson
    implementation(libs.retrofit)
    implementation(libs.converter.gson)

    //
    implementation(libs.coil.compose)
    //
    implementation (libs.androidx.camera.camera2)
    implementation( libs.androidx.camera.lifecycle)
    implementation( libs.androidx.camera.view)
//    implementation (libs.androidx.camera.extensions)
//    implementation (libs.androidx.camera.core)

    implementation(libs.tasks.vision)
//// MediaPipe Face Detection
//    implementation (libs.solution.core)
//    implementation (libs.vision)
}