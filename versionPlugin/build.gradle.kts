
plugins {
    `kotlin-dsl`
}


repositories {
    google()
    mavenCentral()
    gradlePluginPortal()
}


java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

dependencies {
    //添加Gradle相关的API，否则无法自定义Plugin和Task
    implementation(gradleApi())
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.10")
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            // 在 app 模块需要通过 id 引用这个插件
            id = "com.dd.application"
            // 实现这个插件的类的路径
            implementationClass = "com.dd.version.AndroidApplicationConventionPlugin"
        }
        register("room") {
            // 在 app 模块需要通过 id 引用这个插件
            id = "com.dd.room"
            // 实现这个插件的类的路径
            implementationClass = "com.dd.version.RoomConventionPlugin"
        }
        register("hilt") {
            // 在 app 模块需要通过 id 引用这个插件
            id = "com.dd.hilt"
            // 实现这个插件的类的路径
            implementationClass = "com.dd.version.HiltConventionPlugin"
        }
    }
}