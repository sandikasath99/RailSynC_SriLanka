// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath ("com.google.android.libraries.mapsplatform.secrets-gradle-plugin:secrets-gradle-plugin:2.0.1")
        classpath ("com.android.tools.build:gradle:7.0.4")
        classpath ("com.google.gms:google-services:4.3.10")
    }
}

plugins {
    id("com.android.application") version "8.4.0" apply false
    id("com.google.gms.google-services") version "4.4.1" apply false
}