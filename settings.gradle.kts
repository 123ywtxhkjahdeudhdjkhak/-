pluginManagement {
    repositories {
        maven(url = "https://maven.aliyun.com/repository/google")
        maven( url ="https://maven.aliyun.com/repository/gradle-plugin")
        maven( url="https://maven.aliyun.com/repository/public")
        maven( url ="https://maven.aliyun.com/repository/jcenter")
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven(url = "https://jitpack.io")
    }
}

rootProject.name = "Yyong"
include(":app")
include(":myapplication")
include(":developfoundation")
