package com.matzip.presentation

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MatzipApplication: Application() {
    override fun onCreate() {
        super.onCreate()
    }
}

const val BASE_URL = "https://api.matzip.com/"