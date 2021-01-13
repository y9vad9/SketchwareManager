import org.jetbrains.kotlin.konan.properties.loadProperties


plugins {
    kotlin("jvm") version "1.4.21"
    kotlin("plugin.serialization") version "1.4.10"
    `maven-publish`
}

group = "io.sketchware"
version = "alpha-2.2.2"

allprojects {
    repositories {
        mavenCentral()
        jcenter()
    }
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.0.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.2")
}

publishing {
    publications {
        create<MavenPublication>(name = project.name) {
            from(components["kotlin"])
        }
    }
}

val vcs = "https://github.com/y9neon/SketchwareManager"
val projectName = project.name

val localProperties = project.rootProject.file("local.properties")
    .takeIf(File::exists)
    ?.let(File::getAbsolutePath)
    ?.let(::loadProperties)

allprojects {
    apply(plugin = "maven-publish")

    publishing {
        publications.filterIsInstance<MavenPublication>().forEach { publication ->
            publication.pom {
                name.set(project.name)
                description.set(project.description)
                url.set(vcs)

                developers {
                    developer {
                        id.set("y9neon")
                        name.set("Vadim Kotlinov")
                    }
                }

                scm {
                    url.set(vcs)
                    tag.set("${project.version}")
                }
            }
        }

        repositories {
            maven {
                name = "bintray"
                url = uri("https://api.bintray.com/maven/kotlingang/maven/$projectName/;publish=1;override=1")

                val (bintrayUser, bintrayApiKey) = if(localProperties != null) {
                    localProperties.getProperty("bintrayUser") to localProperties.getProperty("bintrayApiKey")
                } else {
                    null to null
                }

                if(bintrayUser == null || bintrayApiKey == null) {
                    System.err.println("Missing bintray authorization (bintrayUser, bintrayApiKey in local.properties)")
                } else {
                    credentials {
                        username = bintrayUser
                        password = bintrayApiKey
                    }
                }
            }
        }
    }
}
