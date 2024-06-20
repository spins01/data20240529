pluginManagement {
    includeBuild("./build-logic")
    repositories {
        maven {
            url = uri("https://jitpack.io")
        }
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            from(files("./libs.versions.toml"))
        }
    }
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        maven {
            url = uri("https://jitpack.io")
        }
        // 添加阿里的镜像 Maven
        maven {
            url = uri("https://maven.aliyun.com/nexus/content/groups/public/")
        }
        google()
        mavenCentral()
    }
}

rootProject.name = "NewProject"
include(":app")
include(":module-base")
include(":lib-res")
include(":module-net")
