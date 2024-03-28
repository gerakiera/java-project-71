plugins {
    application
    id ("checkstyle")
}

group = "hexlet.code"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.assertj:assertj-core:3.24.2")
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation ("info.picocli:picocli:4.7.5")
    implementation ("com.fasterxml.jackson.core:jackson-databind:2.16.1")
    implementation ("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:2.16.1")
}

application {
    mainClass = "hexlet.code.App"
}

tasks.test {
    useJUnitPlatform()
}