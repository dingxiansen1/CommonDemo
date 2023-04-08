import com.dd.version.*

plugins {
    id("com.dd.library")
    id("com.dd.common")
    id("com.dd.compose")
    id("com.dd.test")
}


android {

    namespace = "com.dd.basiccompose"

    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Version.compose_version
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(project(":common:common"))
    implementation(project(":common:utils"))
    implementation(project(":common:net"))
}