plugins {
    kotlin("jvm")
    `maven-publish`
}

dependencies {
    api(project(":"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.2")
}

publishing {
    publications {
        create<MavenPublication>(project.name) {
            from(components["kotlin"])
        }
    }
}