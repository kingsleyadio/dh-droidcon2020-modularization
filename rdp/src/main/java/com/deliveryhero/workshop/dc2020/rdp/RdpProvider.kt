package com.deliveryhero.workshop.dc2020.rdp

import android.content.Context
import android.content.Intent
import com.deliveryhero.workshop.dc2020.rdp.di.DaggerRdpDiComponent
import com.deliveryhero.workshop.dc2020.rdp.di.RdpConfig
import com.deliveryhero.workshop.dc2020.rdp.di.RdpDiComponent
import com.deliveryhero.workshop.dc2020.rdp.ui.RdpActivity

object RdpProvider {

    private lateinit var rdpDiComponent: RdpDiComponent

    @JvmStatic
    fun initDependencies(config: RdpConfig) {
        rdpDiComponent = DaggerRdpDiComponent.builder().config(config).build()
    }

    fun getRdpStartIntent(context: Context, restaurantId: Int): Intent {
        return RdpActivity.newIntent(context, restaurantId)
    }

    internal fun inject(resource: Any) {
        rdpDiComponent.androidInjector.inject(resource)
    }
}
