import com.dd.version.*
plugins {
    id("com.dd.library")
    id("com.dd.common")
}
android {
    namespace = "com.dd.net"
}
dependencies {
    implementation(com.dd.version.Library.coroutineLibrary)
    implementation(com.dd.version.Library.netLibrary)
    implementation(project(":Utils"))
}