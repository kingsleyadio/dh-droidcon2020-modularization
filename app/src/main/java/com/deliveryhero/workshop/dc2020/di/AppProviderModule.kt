package com.deliveryhero.workshop.dc2020.di

import android.app.Application
import com.deliveryhero.workshop.dc2020.data.restaurant.remote.RestaurantsRemoteDataSource
import com.deliveryhero.workshop.dc2020.data.restaurant.remote.RestaurantsRemoteDataSourceImpl
import com.deliveryhero.workshop.dc2020.localization.ResourceStringLocalizer
import com.deliveryhero.workshop.dc2020.localization.StringLocalizer
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
object AppProviderModule {

    @Provides
    @Singleton
    fun provideStrinngLocalizer(app: Application): StringLocalizer {
        return ResourceStringLocalizer(app)
    }

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://my-json-server.typicode.com/kingsleyadio/dh-droidcon2020-modularization/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()

    @Provides
    @Singleton
    fun providesRemoteDataSource(impl: RestaurantsRemoteDataSourceImpl): RestaurantsRemoteDataSource {
        return impl
    }
}
