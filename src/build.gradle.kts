plugins {
    kotlin("jvm") version "2023.3.5"
    application
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
}

application {
    mainClass.set("com.example.MainKt")
}
