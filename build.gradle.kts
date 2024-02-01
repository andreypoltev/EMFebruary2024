// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {

    alias(libs.plugins.android.application) apply false

    id("org.jetbrains.kotlin.android") version "1.9.22" apply false
    kotlin("plugin.serialization") version "1.9.22"
    id("com.google.devtools.ksp") version "1.9.22-1.0.17" apply false


//    id("org.jetbrains.kotlin.android") version "1.9.10" apply false
//    kotlin("plugin.serialization") version "1.9.10"
//    id("com.google.devtools.ksp") version "1.9.10-1.0.13" apply false
}