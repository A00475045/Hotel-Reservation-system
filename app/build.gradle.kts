plugins {
    alias(libs.plugins.androidApplication)
}

android {
    namespace = "com.example.hotel_reservation_system"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.hotel_reservation_system"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)

    implementation("androidx.recyclerview:recyclerview:1.2.1")
    implementation("com.squareup.retrofit:retrofit:1.9.0")
    implementation(libs.play.services.base)

    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}