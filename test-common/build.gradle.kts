plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
}

android {
    val targetSdkVersion: Int by rootProject.extra
    val minSdkVersion: Int by rootProject.extra
    setCompileSdkVersion(targetSdkVersion)
    defaultConfig {
        setMinSdkVersion(minSdkVersion)
        setTargetSdkVersion(targetSdkVersion)
    }
}

dependencies {
    val lifecycleVersion: String by rootProject.extra
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycleVersion")

    api("junit:junit:4.13")
    api("org.jetbrains.kotlin:kotlin-test-junit")
    val mockitoVersion = "3.3.3"
    api("org.mockito:mockito-core:$mockitoVersion")
    api("org.mockito:mockito-inline:$mockitoVersion")
    api("com.nhaarman.mockitokotlin2:mockito-kotlin:2.2.0")
    api("androidx.arch.core:core-testing:2.1.0")
    val coroutinesVersion: String by rootProject.extra
    api("org.jetbrains.kotlinx:kotlinx-coroutines-test:$coroutinesVersion")
}
