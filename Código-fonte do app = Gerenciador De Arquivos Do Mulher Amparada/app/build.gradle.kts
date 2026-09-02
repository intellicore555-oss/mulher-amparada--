plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.gerenciar"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.gerenciar"
        minSdk = 23
        targetSdk = 36
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    // AndroidX
    implementation("androidx.core:core-ktx:1.16.0")
    implementation("androidx.appcompat:appcompat:1.7.1")
    implementation("androidx.activity:activity-ktx:1.10.1")
    implementation("androidx.constraintlayout:constraintlayout:2.2.1")

    // Material Design
    implementation("com.google.android.material:material:1.12.0")

    // Lifecycle
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.9.1")

    // FileProvider
    implementation("androidx.core:core:1.16.0")
    
    implementation("androidx.biometric:biometric:1.1.0")

    // Testes

implementation("androidx.media3:media3-exoplayer:1.8.0")
    implementation("androidx.media3:media3-ui:1.8.0")
    implementation("androidx.media3:media3-common:1.8.0")

    implementation("net.lingala.zip4j:zip4j:2.11.5")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
   implementation("androidx.media3:media3-session:1.8.0")
 androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")
}