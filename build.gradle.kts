plugins {
    kotlin("jvm") version "1.8.10"
    kotlin("plugin.allopen") version "1.8.10"
    id("com.google.devtools.ksp")
    id("io.quarkus")
}

repositories {
    mavenCentral()
    mavenLocal()
}

val quarkusPlatformGroupId: String by project
val quarkusPlatformArtifactId: String by project
val quarkusPlatformVersion: String by project

dependencies {
    platform("org.komapper:komapper-platform:1.8.0").let {
        implementation(it)
        ksp(it)
    }
    implementation(enforcedPlatform("io.quarkus.platform:quarkus-bom:2.16.3.Final"))
    implementation("io.quarkus:quarkus-kotlin")
    implementation("io.quarkus:quarkus-resteasy-reactive-jackson")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    implementation("io.quarkus:quarkus-arc")
    implementation("io.quarkus:quarkus-resteasy-reactive")

    implementation("org.komapper:komapper-starter-r2dbc")
    implementation("org.komapper:komapper-dialect-h2-r2dbc")
    ksp("org.komapper:komapper-processor")

    testImplementation("io.quarkus:quarkus-junit5")
    testImplementation("io.rest-assured:rest-assured")
}

group = "org.acme"
version = "1.0.0-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

tasks.withType<Test> {
    systemProperty("java.util.logging.manager", "org.jboss.logmanager.LogManager")
}
allOpen {
    annotation("javax.ws.rs.Path")
    annotation("javax.enterprise.context.ApplicationScoped")
    annotation("io.quarkus.test.junit.QuarkusTest")
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions.jvmTarget = JavaVersion.VERSION_17.toString()
    kotlinOptions.javaParameters = true
}

// https://github.com/quarkusio/quarkus/issues/29698#issuecomment-1368073417
project.afterEvaluate {
    getTasksByName("quarkusGenerateCode", true).forEach { task ->
        task.setDependsOn(task.dependsOn.map { it as Provider<Task> }.filter { it.get().name != "processResources" })
    }
    getTasksByName("quarkusGenerateCodeDev", true).forEach { task ->
        task.setDependsOn(task.dependsOn.map { it as Provider<Task> }.filter { it.get().name != "processResources" })
    }
}
