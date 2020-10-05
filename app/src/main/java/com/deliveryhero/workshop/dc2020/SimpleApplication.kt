package com.deliveryhero.workshop.dc2020

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate

class SimpleApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
    }
}
