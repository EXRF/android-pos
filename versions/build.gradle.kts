plugins {
    `kotlin-dsl`
}

repositories {
    google()
    mavenCentral()
}

gradlePlugin {
    plugins {
        create("versions") {
            id = "id.android_pos.versions"
            implementationClass = "id.android_pos.versions.DependenciesPlugin"
        }
    }
}