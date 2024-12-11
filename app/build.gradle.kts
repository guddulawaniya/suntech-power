plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id ("kotlin-kapt")
    id ("com.google.dagger.hilt.android")
}

android {
    namespace = "com.power.suntechpower"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.power.suntechpower"
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
    kotlinOptions {
        jvmTarget = "1.8"
    }
    kapt {
        correctErrorTypes = true // Helps with annotation processor error resolution
    }

    buildFeatures{
        viewBinding = true
        dataBinding = true
    }
}

dependencies {

    implementation ("dev.bandb.graphview:graphview:0.8.1")
    implementation ("de.hdodenhof:circleimageview:3.1.0")
    implementation ("io.github.chaosleung:pinview:1.4.4")
    implementation ("com.tbuonomo:dotsindicator:4.2")
    implementation ("com.google.android.material:material:1.9.0")
    implementation ("com.google.firebase:firebase-messaging:23.1.0")
    implementation (platform("com.google.firebase:firebase-bom:29.3.1"))
    implementation ("com.google.firebase:firebase-analytics-ktx")

    implementation ("org.jetbrains.kotlin:kotlin-stdlib:1.8.0")

    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.0")
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.6.0")

    // DataBinding (Optional but recommended)
    implementation ("androidx.databinding:databinding-runtime:7.4.0")

    // Coroutine support for ViewModel (if needed)
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")
    implementation ("com.intuit.sdp:sdp-android:1.0.6")
    // Coroutine support for ViewModel
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")
    // If using Retrofit or other network libraries
    implementation("com.squareup.okhttp3:okhttp:4.9.3")

    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation ("com.squareup.okhttp3:logging-interceptor:4.9.0")

    implementation ("com.google.dagger:hilt-android:2.46")  // Latest stable version
    kapt ("com.google.dagger:hilt-android-compiler:2.46")

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}