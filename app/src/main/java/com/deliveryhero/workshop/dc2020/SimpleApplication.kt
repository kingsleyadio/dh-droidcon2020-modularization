package com.deliveryhero.workshop.dc2020

import androidx.appcompat.app.AppCompatDelegate
import com.deliveryhero.workshop.dc2020.di.DaggerAppComponent
import com.deliveryhero.workshop.dc2020.localization.StringLocalizer
import com.deliveryhero.workshop.dc2020.rdp.RdpProvider
import com.deliveryhero.workshop.dc2020.rlp.RlpProvider
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import javax.inject.Inject

class SimpleApplication : DaggerApplication() {

    @Inject
    lateinit var stringLocalizer: StringLocalizer

    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        StringLocalizer.setDefault(stringLocalizer)
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        val appComponent = DaggerAppComponent.factory().create(this)
        RdpProvider.initDependencies(appComponent)
        RlpProvider.initDependencies(appComponent)

        return appComponent
    }
}
