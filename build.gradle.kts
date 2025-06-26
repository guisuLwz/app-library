plugins {
    kotlin("jvm") version "2.1.20"
    id("java-gradle-plugin")
    id("maven-publish")
    id("com.gradle.plugin-publish") version "1.2.1"
}

group = "io.github.guisuLwz"
version = "7.5.0"

gradlePlugin {
    website = "https://github.com/guisuLwz/app-library"
    vcsUrl = "https://github.com/guisuLwz/app-library.git"
    plugins {
        create("appLibraryPlugin") {
            id = "io.github.guisuLwz.app-library"
            displayName = "appLibrary"
            description = "This plugin automatically integrates multiple Android dependencies without the need for manual copying and adding."
            tags = listOf("plugins", "android", "library", "gui_su", "guisu")
            implementationClass = "com.gui_su.plugin.AppLibraryPlugin"
        }
    }
}

repositories {
    maven { url = uri("https://maven.aliyun.com/nexus/content/repositories/google") }
    maven { url = uri("https://maven.aliyun.com/nexus/content/groups/public") }
    maven { url = uri("https://maven.aliyun.com/nexus/content/repositories/jcenter")}
    maven { url = uri("https://plugins.gradle.org/m2/") }
    google()
    mavenCentral()
    gradlePluginPortal()
    maven { url = uri("https://jitpack.io") }
    jcenter()
}

dependencies {
    implementation(gradleApi())
    implementation(localGroovy())
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(8)
}