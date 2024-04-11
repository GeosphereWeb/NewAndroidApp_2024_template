import io.gitlab.arturbosch.detekt.Detekt

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.jetbrainsKotlinAndroid) apply false
    alias(libs.plugins.detekt)
    alias(libs.plugins.ktlint)
}

subprojects {
    apply(plugin = "org.jlleitschuh.gradle.ktlint") // Version should be inherited from parent
    apply(plugin = "io.gitlab.arturbosch.detekt")

    // Optionally configure plugin
    configure<org.jlleitschuh.gradle.ktlint.KtlintExtension> {
        debug.set(true)
    }
}

// Kotlin DSL
tasks.withType<Detekt>().configureEach {
    reports {
        xml.required.set(true)
        xml.outputLocation.set(file("$projectDir/build/reports/detekt.xml"))
        html.required.set(false)
        txt.required.set(false)
        sarif.required.set(false)
        md.required.set(false)
    }
}

dependencies {
    detektPlugins(libs.plugins.detekt.formatting)
}

// detekt {
//    toolVersion = libs.versions.detekt.get()
//    config.setFrom(file("$projectDir/config/detekt/detekt.yml"))
//    buildUponDefaultConfig = true
// }
detekt {

    // Version of detekt that will be used. When unspecified the latest detekt
    // version found will be used. Override to stay on the same version.
    toolVersion = libs.versions.detekt.get()

    config.setFrom(file("$projectDir/config/detekt/detekt.yml"))

    // The directories where detekt looks for source files.
    // Defaults to `files("src/main/java", "src/test/java", "src/main/kotlin", "src/test/kotlin")`.
    source.setFrom("src/main/java", "src/main/kotlin")

    // Builds the AST in parallel. Rules are always executed in parallel.
    // Can lead to speedups in larger projects. `false` by default.
    parallel = false

    // Define the detekt configuration(s) you want to use.
    // Defaults to the default detekt configuration.
//    config.setFrom("path/to/config.yml")

    // Applies the config files on top of detekt's default config file. `false` by default.
    buildUponDefaultConfig = true

    // Turns on all the rules. `false` by default.
//    allRules = false

    // Specifying a baseline file. All findings stored in this file in subsequent runs of detekt.
//    baseline = file("path/to/baseline.xml")

    // Disables all default detekt rulesets and will only run detekt with custom rules
    // defined in plugins passed in with `detektPlugins` configuration. `false` by default.
    disableDefaultRuleSets = false

    // Adds debug output during task execution. `false` by default.
    debug = false

    // If set to `true` the build does not fail when the
    // maxIssues count was reached. Defaults to `false`.
    ignoreFailures = false

    // Android: Don't create tasks for the specified build types (e.g. "release")
    ignoredBuildTypes = listOf("release")

    // Android: Don't create tasks for the specified build flavor (e.g. "production")
    ignoredFlavors = listOf("production")

    // Android: Don't create tasks for the specified build variants (e.g. "productionRelease")
    ignoredVariants = listOf("productionRelease")

    // Specify the base path for file paths in the formatted reports.
    // If not set, all file paths reported will be absolute file path.
    basePath = projectDir.absolutePath
}
