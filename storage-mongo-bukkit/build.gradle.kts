plugins {
    id("alexisdev.publishing-conventions")
    id("alexisdev.spigot-conventions")
}

repositories {
    maven("https://jitpack.io")
}

dependencies {
    implementation("com.github.pixeldev-org.storage:storage-mongo-legacy-dist:v2.2.0")
}