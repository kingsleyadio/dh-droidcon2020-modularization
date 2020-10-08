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

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(project(":localization"))
    implementation(project(":mvvm-common"))
    implementation(project(":restaurant-provider"))

    implementation("org.jetbrains.kotlin:kotlin-stdlib")

    val coreVersion: String by rootProject.extra
    implementation("androidx.core:core-ktx:$coreVersion")
    val appCompatVersion: String by rootProject.extra
    implementation("androidx.appcompat:appcompat:$appCompatVersion")
    val mdcVersion: String by rootProject.extra
    implementation("com.google.android.material:material:$mdcVersion")
    val constraintLayoutVersion: String by rootProject.extra
    implementation("androidx.constraintlayout:constraintlayout:$constraintLayoutVersion")
    val lifecycleVersion: String by rootProject.extra
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:$lifecycleVersion")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycleVersion")
    val glideVersion: String by rootProject.extra
    implementation("com.github.bumptech.glide:glide:$glideVersion")
    val retrofitVersion: String by rootProject.extra
    implementation("com.squareup.retrofit2:retrofit:$retrofitVersion")
    implementation("com.squareup.retrofit2:converter-gson:$retrofitVersion")
    val daggerVersion: String by rootProject.extra
    implementation("com.google.dagger:dagger:$daggerVersion")
    implementation("com.google.dagger:dagger-android-support:$daggerVersion")
    kapt("com.google.dagger:dagger-compiler:$daggerVersion")
    kapt("com.google.dagger:dagger-android-processor:$daggerVersion")

    testImplementation(project(":test-common"))
}
