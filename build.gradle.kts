// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    val kotlinVersion by extra("1.4.10")
    val minSdkVersion by extra(21)
    val targetSdkVersion by extra(30)
    val coroutinesVersion by extra("1.3.9")
    val lifecycleVersion by extra("2.3.0-beta01")
    val retrofitVersion by extra("2.9.0")
    val daggerVersion by extra("2.29.1")
    val constraintLayoutVersion by extra("2.0.2")
    val mdcVersion by extra("1.3.0-alpha03")
    val appCompatVersion by extra("1.3.0-alpha02")
    val coreVersion by extra("1.5.0-alpha04")
    val glideVersion by extra("4.11.0")

    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:4.0.1")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

allprojects {
    repositories {
        google()
        jcenter()
    }
}

subprojects {
    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions.jvmTarget = "1.8"
        kotlinOptions.freeCompilerArgs += listOf(
            "-progressive",
            "-Xopt-in=kotlin.ExperimentalStdlibApi",
            "-Xopt-in=kotlin.experimental.ExperimentalTypeInference",
            "-Xopt-in=kotlinx.coroutines.ExperimentalCoroutinesApi"
        )
    }
}

tasks.register<Delete>("clean") {
    delete(rootProject.buildDir)
}
