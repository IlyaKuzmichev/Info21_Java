import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    java
    id("org.springframework.boot")
    id("io.spring.dependency-management")
}

repositories {
    mavenCentral()
    google()
    gradlePluginPortal()
}

dependencies {
    implementation(project(":")) // Подключаем код основного приложения

    // Для отправки REST-запросов
    implementation("org.springframework.boot:spring-boot-starter-web")

    // Для работы с JPA (если нужно напрямую работать с базой)
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    // Тестовые зависимости
    testImplementation("org.springframework.boot:spring-boot-starter-test")

    // Для работы с REST-запросами в тестах
    testImplementation("io.rest-assured:rest-assured:5.4.0")

    implementation("org.postgresql:postgresql:42.7.2")
}

tasks.withType<Test> {
    useJUnitPlatform() // Включает JUnit 5
}

tasks.named<BootJar>("bootJar") {
    enabled = false
}

tasks.withType<BootJar> {
    enabled = false
}