package com.gui_su.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project
import java.io.File


class AppLibraryPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        with(project) {
            afterEvaluate {
                if (it.plugins.hasPlugin("com.android.base")) {
                    logger.lifecycle("Plugin implements dependence start....")

                    applyOtherPlugins(it)

                    addDependencies(it)

                    writeReadMe(it)

                    writeJava()

                    gradle.buildFinished {
                        logger.lifecycle("Old plugins dependencies, please read README.txt file in root dir.... ")
                        logger.lifecycle("Plugin implements dependencies done....")
                    }
                } else logger.lifecycle("It is not android project!")
            }
        }
    }

    private fun applyOtherPlugins(project: Project) {
        val plugins = arrayListOf(
            "kotlin-parcelize"
        )
        with(project.pluginManager) {
            for (plugin in plugins) {
                apply(plugin)
                project.logger.lifecycle("Applied plugin $plugin.")
            }
        }
    }

    private fun addDependencies(project: Project) {
        val dependencies = arrayListOf(
            "category" to "android",
            "implementation" to "androidx.appcompat:appcompat:1.4.1",
            "implementation" to "com.google.android.material:material:1.5.0",
            "implementation" to "androidx.constraintlayout:constraintlayout:2.1.3",
            "implementation" to "androidx.core:core-ktx:1.7.0",
            "testImplementation" to "junit:junit:4.13.2",
            "androidTestImplementation" to "androidx.test.ext:junit:1.1.3",
            "androidTestImplementation" to "androidx.test.espresso:espresso-core:3.4.0",
            "category" to "greendao",
            "implementation" to "org.greenrobot:greendao:3.3.0",
            "implementation" to "org.greenrobot:greendao-generator:3.3.0",
            "implementation" to "io.github.yuweiguocn:GreenDaoUpgradeHelper:v2.2.1",
            "implementation" to "org.greenrobot:greendao-gradle-plugin:3.3.0",
            "category" to "glide",
            "implementation" to "com.github.bumptech.glide:glide:4.11.0",
            "annotationProcessor" to "com.github.bumptech.glide:compiler:4.11.0",
            "category" to "okhttp3",
            "implementation" to "com.squareup.okhttp3:okhttp:4.10.0",
            "implementation" to "com.squareup.okhttp3:logging-interceptor:4.10.0",
            "category" to "livedata + viewmodel + coroutines",
            "implementation" to "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4",
            "implementation" to "androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2",
            "implementation" to "androidx.work:work-runtime-ktx:2.7.1",
            "implementation" to "androidx.lifecycle:lifecycle-runtime-ktx:2.6.2",
            "implementation" to "androidx.lifecycle:lifecycle-livedata-ktx:2.6.2",
            "category" to "navigation",
            "implementation" to "androidx.navigation:navigation-fragment:2.5.3",
            "implementation" to "androidx.navigation:navigation-ui:2.5.3",
            "category" to "retrofit2",
            "implementation" to "com.squareup.retrofit2:retrofit:2.9.0",
            "implementation" to "com.squareup.retrofit2:adapter-rxjava2:2.9.0",
            "implementation" to "com.squareup.retrofit2:converter-gson:2.9.0",
            "implementation" to "com.squareup.retrofit2:converter-scalars:2.9.0",
            "category" to "EventBus",
            "implementation" to "org.greenrobot:eventbus:3.3.1",
            "category" to "FastJson",
            "implementation" to "com.alibaba:fastjson:1.2.76",
            "category" to "MMKV",
            "implementation" to "com.tencent:mmkv:1.2.13",
            "category" to "gson",
            "implementation" to "com.google.code.gson:gson:2.10.1"
        )
        project.dependencies.apply {
            for (dependence in dependencies) {
                if (dependence.first == "category") project.logger.lifecycle("<------ ${dependence.second} ------> ")
                else {
                    add(dependence.first, dependence.second)
                    project.logger.lifecycle("${dependence.first}: ${dependence.second}")
                }
            }
        }
    }

    private fun writeReadMe(project: Project) {
        val readmeFile = File(project.rootDir, "README.txt")

        if (!readmeFile.exists()) {
            readmeFile.writeText(
                """
                            欢迎使用 io.github.guisuLwz.app-library 插件！
                            本插件自动集成多个安卓依赖，包括GreenDAO、navigation、agp等等。                    
                            1.若如需使用上述提到的插件，还请在 project 的 build.gradle 中添加：
        
                            buildscript {
                                ext.kotlin_version = "1.6.0"
                                dependencies {
                                    classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.21")
                                    classpath("com.android.tools.build:gradle:4.2.2")
                                    classpath("org.greenrobot:greendao-gradle-plugin:3.3.0")
                                    classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.5.3")
                                }
                            }
                            
                            以及需更换插件版本
                            
                            plugins {
                                id 'com.android.application' version '7.4.1' apply false
                                id 'com.android.library' version '7.4.1' apply false
                                id 'org.jetbrains.kotlin.android' version '1.8.0' apply false
                            }
                            
                            2.若需使用 greendao 自动更新版本插件或者 navigation，需在 app 的 build.gradle 中添加：
                            
                            plugins {
                                id 'org.greenrobot.greendao'
                                id 'androidx.navigation.safeargs'
                            }
                            
                            greendao {
                                schemaVersion 1 // 指定数据库schema版本号，迁移等操作会用到
                                daoPackage 'com.example.xxx.greendao.dao' // dao的包名，包名默认是entity所在的包
                                targetGenDir 'src/main/java' // 生成数据库文件的目录
                            }
                            
                            3.需要在根目录的 settings.gradle 中替换：
                            
                            dependencyResolutionManagement {
                                repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
                                repositories {
                                    google()
                                    mavenCentral()
                                    maven { url 'https://jitpack.io' } // 其实只需要添加这个
                                }
                            }
                            
                            4.需要在根目录的 gradle.properties 中添加：
                            
                            android.enableJetifier=true
                            
                            感谢使用！
                         """.trimIndent()
            )
        }
    }

    private fun writeJava() {

    }
}