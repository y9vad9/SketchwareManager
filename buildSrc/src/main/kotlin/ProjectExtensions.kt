import `fun`.kotlingang.neon.deploy.Deploy
import `fun`.kotlingang.neon.deploy.DeployConfiguration
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.gradle.util.GUtil.loadProperties
import java.io.File

/**
 * Enabling maven publish task for library
 * if `deploy.properties` exists in project folder.
 * deploy.properties should have next fields: host(server remote address), user (sftp user on remote server),
 * password (user's password), destination (destination folder path on remote server).
 */
fun Project.applyDeploy() {
    val deployPropertiesFile: File = project.file("deploy.properties")

    if (deployPropertiesFile.exists()) {
        val properties = loadProperties(deployPropertiesFile)

        project.apply<Deploy>()
        project.configure<DeployConfiguration> {
            user = properties.getProperty("user")
            host = properties.getProperty("host")
            password = properties.getProperty("password")
            destinationFolderPath = properties.getProperty("destination")
        }
    }
}