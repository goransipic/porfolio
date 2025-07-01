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
            implementation(libs.silk.icons.fa)
            // implementation(libs.kobwebx.markdown)
            
        }
    }
}
