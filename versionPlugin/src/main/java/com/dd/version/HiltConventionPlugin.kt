package com.dd.version

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class HiltConventionPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        with(project) {
            dependencies {
                add("implementation", Jetpack.hilt)
                add("implementation", Jetpack.hilt_navigation)
                add("kapt", Jetpack.hilt_kapt)
            }
        }
    }

}