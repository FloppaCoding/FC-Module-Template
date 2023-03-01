import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import net.fabricmc.loom.task.RemapJarTask
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile


plugins {
    kotlin("jvm") version "1.8.10"
    id("com.github.johnrengelman.shadow") version "7.1.2"
    id("gg.essential.loom") version "0.10.0.+"
    idea
    java
}

// The output jar will be named jarBaseName-version, you can change that.
val jarBaseName = "FC_External"
group = "fcexternal"
version = "1.0"


repositories {
    mavenCentral()
    maven("https://repo.spongepowered.org/repository/maven-public/")
    maven("https://repo.sk1er.club/repository/maven-public")
    maven("https://jitpack.io" )
}

val packageLib by configurations.creating {
    configurations.implementation.get().extendsFrom(this)
}

dependencies {
    minecraft("com.mojang:minecraft:1.8.9")
    mappings("de.oceanlabs.mcp:mcp_stable:22-1.8.9")
    forge("net.minecraftforge:forge:1.8.9-11.15.1.2318-1.8.9")
    implementation("gg.essential:essential-1.8.9-forge:3662")

    // Will use the latest version of Floppaclient. Alternatively you can specify a version
    implementation("com.github.FloppaCoding:FloppaClient:-SNAPSHOT")
}

sourceSets {
    main {
        output.setResourcesDir(file("${buildDir}/classes/kotlin/main"))
    }
}

tasks{
    named<Jar>("jar") {
        // Manifest currently unused.
        manifest.attributes(
            "FloppaClientExtension" to true,
        )
        dependsOn(shadowJar)
        enabled = false
    }
    named<RemapJarTask>("remapJar") {
        archiveBaseName.set(jarBaseName)
        input.set(shadowJar.get().archiveFile)
    }
    named<ShadowJar>("shadowJar") {
        archiveBaseName.set(jarBaseName)
        archiveClassifier.set("dev")
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
        configurations = listOf(packageLib)
        mergeServiceFiles()
    }
    withType<JavaCompile> {
        options.encoding = "UTF-8"
    }
    withType<KotlinCompile> {
        kotlinOptions {
            jvmTarget = "1.8"
        }
    }
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(8))
}

kotlin {
    jvmToolchain {
        languageVersion.set(JavaLanguageVersion.of(8))
    }
}