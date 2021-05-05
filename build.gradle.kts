import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

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
    implementation(Dependencies.COROUTINES)
    implementation(Dependencies.JSON)
    testImplementation(Dependencies.JUNIT)
    implementation(kotlin("reflect"))
}

/**
 * Enabling maven publish task for library
 * if `deploy.properties` exists in project folder.
 * deploy.properties should have next fields: host(server remote address), user (sftp user on remote server),
 * password (user's password), destination (destination folder path on remote server)
 */
applyDeploy()

tasks.apply {
    withType<KotlinCompile> {
        kotlinOptions {
            languageVersion = "1.5"
            freeCompilerArgs = freeCompilerArgs + "-Xopt-in=kotlin.RequiresOptIn"
        }
    }
    withType<Test> {
        useJUnitPlatform()
    }
}