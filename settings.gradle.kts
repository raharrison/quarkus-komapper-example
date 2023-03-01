pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
        mavenLocal()
    }
    plugins {
        id("io.quarkus") version "2.16.3.Final"
        id("com.google.devtools.ksp") version "1.8.10-1.0.9"
    }
}
rootProject.name="quarkus-komapper-example"
