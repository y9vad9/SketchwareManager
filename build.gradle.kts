import `fun`.kotlingang.neon.deploy.Deploy
import `fun`.kotlingang.neon.deploy.DeployConfiguration
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jetbrains.kotlin.konan.properties.loadProperties

plugins {
    kotlin(Plugins.JVM) version Versions.KOTLIN
    kotlin(Plugins.SERIALIZATION) version Versions.KOTLIN
    `maven-publish`
}

group = Library.PACKAGE
version = Library.VERSION

repositories {
    mavenCentral()
}

dependencies {
    implementation(Dependencies.KOTLIN_COROUTINES)
    implementation(Dependencies.KOTLIN_SERIALIZATION_JSON)
    implementation(Dependencies.JUNIT)
}

val deployPropertiesFile: File = project.file("deploy.properties")

if(deployPropertiesFile.exists()) {
    val properties = loadProperties(deployPropertiesFile.absolutePath)

    project.apply<Deploy>()
    project.configure<DeployConfiguration> {
        user = properties.getProperty("user")
        host = properties.getProperty("host")
        password = properties.getProperty("password")
        destinationFolderPath = properties.getProperty("destination")
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        useIR = true
        freeCompilerArgs += "-Xopt-in=kotlin.RequiresOptIn"
    }
}