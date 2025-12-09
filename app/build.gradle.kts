plugins {
    id("java")
    id("checkstyle")
    id("jacoco")
    id("org.sonarqube") version "7.1.0.6387"
}

group = "hexlet.code"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    useJUnitPlatform()
    finalizedBy(tasks.jacocoTestReport)
}

checkstyle {
    toolVersion = "10.12.5"
}

jacoco {
    toolVersion = "0.8.12"
}

tasks.jacocoTestReport {
    dependsOn(tasks.test)
    reports {
        xml.required = true
        html.required = true
    }
}

sonar {
    properties {
        property("sonar.projectKey", "Dron92-dev_java-project-78")
        property("sonar.organization", "dron92-dev")
    }
}