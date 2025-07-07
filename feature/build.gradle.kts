plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.matzip.feature"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {
    implementation(project(":domain"))
    implementation(AndroidX.CORE)
    implementation(AndroidX.LIFECYCLE_RUNTIME_KTX)
    implementation(AndroidX.ACTIVITY_COMPOSE)
    implementation(platform(AndroidX.COMPOSE_BOM))
    implementation(AndroidX.COMPOSE_UI)
    implementation(AndroidX.COMPOSE_GRAPHICS)
    implementation(AndroidX.COMPOSE_PREVIEW)
    implementation(AndroidX.COMPOSE_MATERIAL)
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    //힐트
    implementation(Google.HILT_ANDROID)
    implementation(Google.HILT_CORE)
    implementation(Google.HILT_COMPOSE)
    kapt(Google.HILT_COMPILER)
}