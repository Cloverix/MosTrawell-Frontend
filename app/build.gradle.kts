plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)

    kotlin("plugin.serialization") version "2.3.0"
}

android {
    namespace = "com.example.mostrawell"
    compileSdk = 36
    /*
    compileSdk {
        version = release(36)
    }
    */

    defaultConfig {
        applicationId = "com.example.mostrawell"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        debug {
            buildConfigField("String", "BASE_URL", "\"https://localhost:8080/\"")
        }
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    /*
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }

    tasks.withType<JavaCompile>().configureEach {
        options.release.set(17)
    }

     */

    buildFeatures {
        compose = true
        buildConfig = true
    }
}

kotlin {
    jvmToolchain(17)
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)

    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation("androidx.navigation:navigation-compose:2.8.9")
    implementation("io.coil-kt:coil-compose:2.4.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.9.0")
    implementation("io.ktor:ktor-client-core:3.3.0")
    implementation("io.ktor:ktor-client-android:3.3.0")
    implementation("io.ktor:ktor-client-auth:3.3.0")
    implementation("io.ktor:ktor-client-content-negotiation:3.3.0")
    implementation("io.ktor:ktor-serialization-kotlinx-json:3.3.0")
    implementation("io.insert-koin:koin-android:4.2.1")
    implementation("io.insert-koin:koin-androidx-compose:4.2.1")
    implementation("io.insert-koin:koin-core:4.2.1")
    implementation("androidx.datastore:datastore-preferences:1.1.1")
}