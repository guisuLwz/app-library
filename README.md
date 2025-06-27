# AppLibraryPlugin

`AppLibraryPlugin` is a Gradle plugin designed for Android projects. It helps developers quickly integrate commonly used dependencies and plugin configurations, automating setup and improving development efficiency.

## Features

- Automatically adds popular Android libraries (AppCompat, Material, Glide, OkHttp, Retrofit, etc.)
- Supports GreenDAO database and SafeArgs navigation plugin
- Generates a `README.txt` file with detailed configuration instructions
- Applies only to Android projects (requires the `com.android.base` plugin)

## Getting Started

Add the plugin to your project:

```kotlin
plugins {
    id("io.github.guisuLwz.app-library") version "1.0.0"
}
