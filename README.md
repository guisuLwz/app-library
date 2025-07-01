# AppLibraryPlugin

`AppLibraryPlugin` is a Gradle plugin designed for Android projects. It helps developers quickly integrate commonly used dependencies and plugin configurations, automating setup and improving development efficiency.

## Features

- Automatically adds popular Android libraries (AppCompat, Material, Glide, OkHttp, Retrofit, etc.)
- Supports GreenDAO database and SafeArgs navigation plugin
- Generates a `README.txt` file with detailed configuration instructions
- Applies only to Android projects (requires the `com.android.base` plugin)

## Included Libraries

- androidx.appcompat:appcompat:1.4.1
- com.squareup.retrofit2:retrofit:2.9.0
- org.greenrobot:eventbus:3.3.1
- com.github.bumptech.glide:glide:4.11.0
- com.tencent:mmkv:1.2.13
- For the full list, check the generated README.txt file

## Getting Started

Add the plugin to your project:

```kotlin
plugins {
    id("io.github.guisuLwz.app-library") version "7.5.0"
}
```

## License

This project is licensed under the Apache License 2.0 - see the [LICENSE](LICENSE) file for details.
