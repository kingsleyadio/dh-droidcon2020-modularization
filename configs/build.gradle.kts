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

    val retrofitVersion: String by rootProject.extra
    implementation("com.squareup.retrofit2:retrofit:$retrofitVersion")
    implementation("com.squareup.retrofit2:converter-gson:$retrofitVersion")
    val daggerVersion: String by rootProject.extra
    implementation("com.google.dagger:dagger:$daggerVersion")
    kapt("com.google.dagger:dagger-compiler:$daggerVersion")

    testImplementation(project(":test-common"))
}
