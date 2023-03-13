package com.dd.version

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class RoomConventionPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        with(project) {
            dependencies {
                add("implementation", Jetpack.room_runtime)
                add("implementation", Jetpack.room)
                add("kapt", Jetpack.room_kapt)
            }
        }
    }

}