package com.matzip.presentation

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class HuggApplication: Application() {
    override fun onCreate() {
        super.onCreate()
    }
}