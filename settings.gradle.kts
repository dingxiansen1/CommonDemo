pluginManagement {
    includeBuild("../CommonDemo/versionPlugin")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "CommonDemo"
include(":app")
include(":BasicCompose")
include(":Utils")
include(":Net")
