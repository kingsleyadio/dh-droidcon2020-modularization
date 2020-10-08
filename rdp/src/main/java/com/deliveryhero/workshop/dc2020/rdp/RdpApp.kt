package com.deliveryhero.workshop.dc2020.rdp

import com.deliveryhero.workshop.dc2020.rdp.di.DaggerRdpComponent
import com.deliveryhero.workshop.dc2020.rdp.di.RdpComponent
import com.deliveryhero.workshop.dc2020.rdp.di.RdpConfig

object RdpApp {

    private lateinit var rdpComponent: RdpComponent

    @JvmStatic
    fun initRdp(config: RdpConfig) {
        rdpComponent = DaggerRdpComponent.builder().config(config).build()
    }

    internal fun inject(resource: Any) {
        rdpComponent.androidInjector.inject(resource)
    }
}
