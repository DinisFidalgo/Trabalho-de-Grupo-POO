plugins {
    kotlin("jvm") version "2023.3.5"
    application
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("org.apache.pdfbox:pdfbox:3.0.2")
}

application {
    mainClass.set("com.example.MainKt")
}
