package com.deliveryhero.workshop.dc2020.di

import android.app.Application
import com.deliveryhero.workshop.dc2020.localization.ResourceStringLocalizer
import com.deliveryhero.workshop.dc2020.localization.StringLocalizer
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object AppProviderModule {

    @Provides
    @Singleton
    fun provideStrinngLocalizer(app: Application): StringLocalizer {
        return ResourceStringLocalizer(app)
    }
}
