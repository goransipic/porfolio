import com.varabyte.kobweb.gradle.application.util.configAsKobwebApplication
import kotlinx.html.link
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.kobweb.application)
    // alias(libs.plugins.kobwebx.markdown)
}

group = "org.example.porfolio"
version = "1.0-SNAPSHOT"

kobweb {
    app {
        val croatiaZone = ZoneId.of("Europe/Zagreb")
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")

        globals.put(
            "version",
            ZonedDateTime
                .now(croatiaZone)
                .format(formatter)
        )
        index {
            description.set("Powered by Kobweb")

            head.add {
                link(rel = "stylesheet", href = basePath.prependTo("/fonts/faces.css"))
            }
        }
    }
}

kotlin {
    configAsKobwebApplication("porfolio")

    sourceSets {
        jsMain.dependencies {
            implementation(libs.compose.runtime)
            implementation(libs.compose.html.core)
            implementation(libs.kobweb.core)
            implementation(libs.kobweb.silk)
            implementation("dev.gitlive:firebase-app:2.1.0")
            implementation("dev.gitlive:firebase-auth:2.1.0")
            implementation("dev.gitlive:firebase-firestore:2.1.0")
            implementation(npm("validator", "13.15.15")) // Or latest version)
            implementation(npm("yup","1.6.1"))
            //implementation(libs.silk.icons.fa)
            // implementation(libs.kobwebx.markdown)
            
        }
    }
}
