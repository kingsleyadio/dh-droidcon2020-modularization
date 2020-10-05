plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
}

android {
    setCompileSdkVersion(30)

    defaultConfig {
        applicationId = "com.deliveryhero.workshop.dc2020"
        setMinSdkVersion(21)
        setTargetSdkVersion(30)
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        viewBinding = true
    }

    buildTypes {
        named("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
}

dependencies {
    implementation(fileTree("libs") { include("*.jar") })
    implementation("org.jetbrains.kotlin:kotlin-stdlib")
    implementation("androidx.core:core-ktx:1.5.0-alpha04")
    implementation("androidx.appcompat:appcompat:1.3.0-alpha02")
    implementation("com.google.android.material:material:1.3.0-alpha03")
    implementation("androidx.constraintlayout:constraintlayout:2.0.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.9")
    val lifecycleVersion = "2.3.0-beta01"
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:$lifecycleVersion")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycleVersion")

    testImplementation("junit:junit:4.13")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")

    androidTestImplementation("androidx.test.ext:junit:1.1.2")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.3.0")
    androidTestImplementation("org.jetbrains.kotlin:kotlin-test-junit")

}
