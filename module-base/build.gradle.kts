plugins {
    id("modulePlugin")
}

android {
    namespace = "com.spins.module.base"
}

dependencies {

    api(project(":lib-res"))
    api(libs.xiaojinzi.android.support.init)
    api(libs.xiaojinzi.android.support.activitystack)
    api(libs.xiaojinzi.android.support.ktx.retrofit)
    api(libs.xiaojinzi.android.module.storage)
    api(libs.xiaojinzi.android.module.ffmpeg)
    api(libs.kcomponent.core)
    api(libs.xiaojinzi.android.reactive.core)
    api(libs.xiaojinzi.android.reactive.template)
    api(libs.xiaojinzi.android.reactive.template.compose)

    api(libs.compose.runtime)
    api(libs.compose.runtime.android)
    api(libs.compose.ui.android)
    api(libs.compose.foundation.android)
    api(libs.compose.foundation.layout.android)
    api(libs.compose.material.android)
    api(libs.compose.material3)

    api(libs.glance.appwidget)
    api(libs.glance.material3)

    api ("androidx.constraintlayout:constraintlayout-compose:1.0.1")

    api ("com.tencent:mmkv:1.3.5")

    api("com.google.accompanist:accompanist-insets:0.24.3-alpha")


}