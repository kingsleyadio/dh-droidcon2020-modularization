package com.deliveryhero.workshop.dc2020.rlp

import android.content.Context
import android.content.Intent
import com.deliveryhero.workshop.dc2020.rlp.di.DaggerRlpDiComponent
import com.deliveryhero.workshop.dc2020.rlp.di.RlpDiComponent
import com.deliveryhero.workshop.dc2020.rlp.di.RlpDiConfig

object RlpProvider {
    private lateinit var rlpDiComponent: RlpDiComponent

    @JvmStatic
    fun initDependencies(rlpDiConfig: RlpDiConfig) {
        rlpDiComponent = DaggerRlpDiComponent.builder().config(rlpDiConfig).build()
    }

    fun getRlpStartIntent(context: Context) = RlpActivity.newIntent(context)

    internal fun inject(resource: Any) {
        rlpDiComponent.androidInjector.inject(resource)
    }
}