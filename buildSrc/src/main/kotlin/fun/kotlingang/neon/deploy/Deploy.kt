package `fun`.kotlingang.neon.deploy

import Library
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.publish.PublishingExtension
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.create
import org.gradle.kotlin.dsl.the

internal fun deployError(message: String) {
    throw DeployException(message)
}

internal fun fieldMissingError(fieldName: String) = deployError("Field with name '$fieldName' is missing.")

class Deploy : Plugin<Project> {
    override fun apply(target: Project) {
        target.apply(plugin = "org.gradle.maven-publish")
        val config = target.extensions.create<DeployConfiguration>(name = "Deploy")

        target.afterEvaluate {

            if(config.user == null)
                fieldMissingError(config::user.name)
            if(config.host == null)
                fieldMissingError(config::host.name)
            if(config.password == null)
                fieldMissingError(config::password.name)
            if(config.destinationFolderPath == null)
                fieldMissingError(config::destinationFolderPath.name)

            project.the<PublishingExtension>().repositories.maven {
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