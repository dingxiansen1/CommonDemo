import com.dd.version.*
plugins {
    id("com.dd.library")
    id("com.dd.common")
}
android {
    namespace = "com.dd.common"
}

dependencies {

    testImplementation(AndroidTest.junit)
    androidTestImplementation(AndroidTest.ext)
    androidTestImplementation(AndroidTest.espresso)

    implementation(project(":common:utils"))
    implementation(project(":common:net"))

    implementation(Jetpack.work)

    //leakcanary
    debugImplementation(Other.leakcanary)

}