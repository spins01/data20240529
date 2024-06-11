plugins {
    id("appPlugin")
}

android {
    namespace = "com.spins.intech"
    signingConfigs {
    }
    flavorDimensions.clear()
    flavorDimensions.addAll(
        elements = listOf(
        )
    )
    productFlavors {
    }
    defaultConfig {
        applicationId = namespace
        versionName = "1.0.0.0"
        versionCode = versionName?.toVersionCode()
        buildTypes {
            maybeCreate("debug").apply {
            }
            maybeCreate("release").apply {
                isMinifyEnabled = true
                proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro",
                )
            }
        }
        ndk {
            abiFilters.clear()
            abiFilters.addAll(
                elements = listOf(
                    "armeabi-v7a",
                    "arm64-v8a",
                )
            )
        }
    }
    buildFeatures {
        buildConfig = true
    }
}