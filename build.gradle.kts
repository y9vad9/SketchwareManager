import org.jetbrains.kotlin.konan.properties.loadProperties

buildscript {
    repositories { mavenCentral() }

    dependencies {
        val kotlinVersion = "1.4.30"
        classpath(kotlin("gradle-plugin", version = kotlinVersion))
        classpath(kotlin("serialization", version = kotlinVersion))
    }
}

plugins {
    kotlin("jvm") version "1.4.31"
    kotlin("plugin.serialization") version "1.4.31"
    `maven-publish`
}

group = "io.sketchware.manager"
version = libraryVersion

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(kotlinSerializationJson)
    implementation(kotlinCoroutines)
    implementation("org.junit.jupiter:junit-jupiter:5.7.0")
}

val localProperties = project.rootProject.file("local.properties")
    .takeIf(File::exists)
    ?.let(File::getAbsolutePath)
    ?.let(::loadProperties)

tasks.withType<Test> {
    useJUnitPlatform()
}

allprojects {
    group = "io.sketchware"
    version = libraryVersion

    apply(plugin = "maven-publish")

    publishing {
        apply(plugin = "maven-publish")
        publications {
            create<MavenPublication>("Deploy") {
                groupId = group as String
                artifactId = "SketchwareManager"
                version = libraryVersion
            }
        }

        repositories {
            maven {
                name = "sketchware-api"
                url = uri(localProperties!!["serverURI"]!!)
                credentials {
                    username = (localProperties["username"] as String?)!!
                    password = (localProperties["password"] as String?)!!
                }
            }
        }
    }
}


tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        useIR = true
        freeCompilerArgs += "-Xopt-in=kotlin.RequiresOptIn"
    }
}
