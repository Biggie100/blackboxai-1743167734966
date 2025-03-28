package com.example.sanskriti

import android.app.Application
import com.google.firebase.FirebaseApp

class SanskritiApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // Initialize Firebase
        FirebaseApp.initializeApp(this)
    }
}