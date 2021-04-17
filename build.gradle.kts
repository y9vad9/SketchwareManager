import org.gradle.util.GUtil.loadProperties

buildscript {
    repositories { mavenCentral() }

    dependencies {

        classpath(kotlin("gradle-plugin", version = kotlinVersion))
        classpath(kotlin("serialization", version = kotlinVersion))
    }
}

plugins {
    kotlin("jvm") version kotlinVersion
    kotlin("plugin.serialization") version kotlinVersion
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
    ?.let(::loadProperties)

tasks.withType<Test> {
    useJUnitPlatform()
}

publishing {
    publications {
        create<MavenPublication>("Deploy") {
            groupId = "io.sketchware"
            artifactId = "SketchwareManager"
            version = libraryVersion
            pom {
                name.set("Sketchware Manager Library")
                description.set("Coroutine-based JVM Library")
            }
        }
    }

    repositories {
        maven {
            name = "SketchwareManager"
            url = uri("sftp://${localProperties!!["serverIp"]}:22/${localProperties["deployPath"]}")
            credentials {
                username = (localProperties["username"] as String?)!!
                password = (localProperties["password"] as String?)!!
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