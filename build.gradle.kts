import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import io.gitlab.arturbosch.detekt.*

plugins {
    kotlin("jvm") version "1.6.10"
    id("io.gitlab.arturbosch.detekt").version("1.22.0")
}

repositories {
   mavenCentral()
}

dependencies {
    implementation("com.github.shiguruikai:combinatoricskt:1.6.0")
    implementation("com.sksamuel.scrimage:scrimage-core:4.0.32")
    implementation("com.sksamuel.scrimage:scrimage-webp:4.0.32")
    implementation("com.sksamuel.scrimage:scrimage-formats-extra:4.0.32")
    testApi("org.junit.jupiter:junit-jupiter-engine:5.7.0")
    testImplementation("org.assertj:assertj-core:3.18.1")
    detektPlugins("io.gitlab.arturbosch.detekt:detekt-formatting:1.22.0")
}

detekt {
    buildUponDefaultConfig = true // preconfigure defaults
    allRules = false // activate all available (even unstable) rules.
    config = files("$projectDir/config/detekt.yml") // point to your custom config defining rules to run, overwriting default behavior
    autoCorrect = false
}

tasks {
    withType<KotlinCompile> {
        kotlinOptions {
            jvmTarget = "1.8"
            freeCompilerArgs = listOf("-Xjsr305=strict")
        }
    }
    wrapper {
        gradleVersion = "7.6" //version required
    }
    test {
        useJUnitPlatform()
        testLogging {
            showStandardStreams = true
        }
    }
    withType<Detekt>().configureEach {
        jvmTarget = "1.8"
    }
    withType<DetektCreateBaselineTask>().configureEach {
        jvmTarget = "1.8"
    }
}
