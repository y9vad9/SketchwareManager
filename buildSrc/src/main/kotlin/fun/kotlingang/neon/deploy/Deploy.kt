package `fun`.kotlingang.neon.deploy

import Library
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.publish.PublishingExtension
import org.gradle.api.publish.maven.MavenPublication
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.create
import org.gradle.kotlin.dsl.get
import org.gradle.kotlin.dsl.the

internal fun deployError(message: String) {
    throw DeployException(message)
}

internal fun fieldMissingError(fieldName: String) = deployError("Field with name '$fieldName' is missing.")

class Deploy : Plugin<Project> {
    override fun apply(target: Project) {
        val config = target.extensions.create<DeployConfiguration>(name = "deploy")

        target.afterEvaluate {

            if (config.user == null)
                fieldMissingError(config::user.name)
            if (config.host == null)
                fieldMissingError(config::host.name)
            if (config.password == null)
                fieldMissingError(config::password.name)
            if (config.destinationFolderPath == null)
                fieldMissingError(config::destinationFolderPath.name)

            project.the<PublishingExtension>().apply {
                apply(plugin = "maven-publish")

                publications {
                    create<MavenPublication>("deploy") {
                        group = Library.PACKAGE
                        artifactId = Library.NAME
                        version = Library.VERSION

                        pom {
                            name.set(Library.NAME)
                            description.set("Coroutine-based Sketchware Manager JVM Library.")
                        }

                        from(components["kotlin"])
                    }
                }

                repositories {
                    maven {
                        name = Library.NAME
                        version = Library.VERSION

                        url = uri("sftp://${config.host}:22/${config.destinationFolderPath}")

                        credentials {
                            username = config.user
                            password = config.password
                        }
                    }
                }
            }
        }
    }

}