
plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.11.0-M2")
    implementation("org.seleniumhq.selenium:selenium-java:4.22.0")
    implementation("org.picocontainer:picocontainer:2.15")
    implementation("io.github.bonigarcia:webdrivermanager:5.9.1")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.11.0-M2")
}

tasks.test {
    useJUnitPlatform()
    maxParallelForks = 4
}