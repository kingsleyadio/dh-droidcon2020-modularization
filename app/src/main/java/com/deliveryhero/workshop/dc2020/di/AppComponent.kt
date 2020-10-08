package com.deliveryhero.workshop.dc2020.di

import android.app.Application
import com.deliveryhero.workshop.dc2020.SimpleApplication
import com.deliveryhero.workshop.dc2020.configs.ConfigsModule
import com.deliveryhero.workshop.dc2020.restaurant_provider.di.RestaurantProviderModule
import com.deliveryhero.workshop.dc2020.rlp.di.RlpDiConfig
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Component(
    modules = [
        ConfigsModule::class,
        AppProviderModule::class,
        AndroidSupportInjectionModule::class,
        ActivityBuilder::class,
        RestaurantProviderModule::class
    ]
)
@Singleton
interface AppComponent : AndroidInjector<SimpleApplication>, RlpDiConfig {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance app: Application): AppComponent
    }
}
