import io.gitlab.arturbosch.detekt.Detekt

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.jetbrainsKotlinAndroid) apply false
    alias(libs.plugins.detekt)
    id("org.jlleitschuh.gradle.ktlint") version "12.1.0"
}

detekt {
//    toolVersion = "1.23.3"
    config.setFrom(file("config/detekt/detekt.yml"))
    buildUponDefaultConfig = true
}

// Kotlin DSL
tasks.withType<Detekt>().configureEach {
    reports {
        xml.required.set(true)
        html.required.set(true)
        txt.required.set(true)
        sarif.required.set(true)
        md.required.set(true)
    }
}

// dependencies {
//    detektPlugins("io.gitlab.arturbosch.detekt:detekt-formatting:1.23.3")
// }
