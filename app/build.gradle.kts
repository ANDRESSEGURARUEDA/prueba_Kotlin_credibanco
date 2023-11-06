plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id ("kotlin-kapt")
    id ("com.google.dagger.hilt.android")
}

android {
    namespace = "com.example.pruebakotlincredibanco"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.pruebakotlincredibanco"
        minSdk = 24
        targetSdk = 33
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    val retrofitVersion = "2.9.0"

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    //implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")
    //implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.5")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.5")
    implementation("com.squareup.okhttp3:logging-interceptor:4.8.1")
    //LiveData
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.3.0")
    //ViewModel
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.3.0")
    //Activity
    implementation("androidx.activity:activity-ktx:1.8.0")
    //Fragment
    implementation("androidx.fragment:fragment-ktx:1.6.2")
    //Retrofit
    implementation("com.squareup.retrofit2:retrofit:$retrofitVersion")
    implementation("com.squareup.retrofit2:converter-gson:$retrofitVersion")
    //Corutinas
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")
    //Dagger
    implementation("com.google.dagger:hilt-android:2.44")
    kapt("com.google.dagger:hilt-android-compiler:2.44")
    //Room
    implementation ("androidx.room:room-ktx:2.3.0")
    kapt ("androidx.room:room-compiler:2.3.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}