package com.deliveryhero.workshop.dc2020.rlp.di

import dagger.Component
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ActivityBuilder::class
    ], dependencies = [RlpDiConfig::class]
)
internal interface RlpDiComponent {

    @Component.Builder
    interface Builder {
        fun config(config: RlpDiConfig): Builder
        fun build(): RlpDiComponent
    }

    val androidInjector: DispatchingAndroidInjector<Any>
}