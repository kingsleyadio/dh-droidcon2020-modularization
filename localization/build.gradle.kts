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
    implementation("org.jetbrains.kotlin:kotlin-stdlib")
    compileOnly(project(":translation-processor"))
    kapt(project(":translation-processor"))
}
