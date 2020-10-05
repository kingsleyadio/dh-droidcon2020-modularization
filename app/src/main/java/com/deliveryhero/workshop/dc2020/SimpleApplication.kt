package com.deliveryhero.workshop.dc2020

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.deliveryhero.workshop.dc2020.localization.ResourceStringLocalizer
import com.deliveryhero.workshop.dc2020.localization.StringLocalizer

class SimpleApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        StringLocalizer.setDefault(ResourceStringLocalizer(this))
    }
}
