package com.example.sprint3.ui.base

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class BaseProjectApplication @Inject constructor() : Application() {

    override fun onCreate() {
        super.onCreate()
    }
}