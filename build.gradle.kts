plugins {
    kotlin("jvm") version "2.1.20"
    id("java-gradle-plugin")
    id("maven-publish")
    id("signing")
}

group = "io.github.guisulwz"
version = "1.0.2-750"

publishing {
    publications {
        create<MavenPublication>("plugin") {
            from(components["kotlin"])

            afterEvaluate {
                artifact(tasks["sourcesJar"])
                artifact(tasks["javadocJar"])
            }

            group = "io.github.guisulwz"
            artifactId = "app-library"
            version = "7.5.2"

            pom {
                name.set("appLibraryPlugin")
                description.set("This plugin automatically integrates multiple Android dependencies without the need for manual copying and adding.")
                url.set("https://github.com/guisulwz/app-library")

                licenses {
                    license {
                        name.set("The Apache License, Version 2.0")
                        url.set("https://www.apache.org/licenses/LICENSE-2.0.txt")
                    }
                }

                developers {
                    developer {
                        id.set("guisulwz")
                        name.set("Li WeiZhong")
                        email.set("2100588538@qq.com")
                    }
                }

                scm {
                    connection.set("scm:git:git://github.com/guisulwz/app-library.git")
                    developerConnection.set("scm:git:ssh://github.com:guisulwz/app-library.git")
                    url.set("https://github.com/guisulwz/app-library")
                }
            }
        }
    }

    repositories {
        maven {
            // 发布文件会在build/myPublish目录生成
            url = layout.buildDirectory.dir("myPublish").get().asFile.toURI()
            mavenLocal()
        }
    }
}

signing {
    useGpgCmd()
    sign(publishing.publications["plugin"])
}

tasks.register<Jar>("sourcesJar") {
    archiveClassifier.set("sources")
    from(sourceSets.main.get().allSource)
}

tasks.register<Jar>("javadocJar") {
    archiveClassifier.set("javadoc")
    from(tasks.named("javadoc"))
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