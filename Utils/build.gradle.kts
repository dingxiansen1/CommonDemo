import com.dd.version.*

plugins {
    id("com.dd.library")
    id("com.dd.test")
}
android {
    namespace = "com.dd.utils"
}
dependencies {
    implementation(AndroidX.core)
    implementation(AndroidX.appcompat)
    implementation(AndroidX.androidPaletteKtx)
    implementation(Jetpack.startup)
}