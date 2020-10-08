package com.deliveryhero.workshop.dc2020.rdp.di

import dagger.Component
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ActivityBuilder::class
    ],
    dependencies = [RdpConfig::class]
)
internal interface RdpComponent {

    @Component.Builder
    interface Builder {
        fun config(config: RdpConfig): Builder

        fun build(): RdpComponent
    }

    val androidInjector: DispatchingAndroidInjector<Any>

}
