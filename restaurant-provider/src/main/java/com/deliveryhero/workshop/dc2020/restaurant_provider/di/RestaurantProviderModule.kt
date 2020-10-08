package com.deliveryhero.workshop.dc2020.restaurant_provider.di

import com.deliveryhero.workshop.dc2020.restaurant_provider.remote.RestaurantsRemoteDataSource
import com.deliveryhero.workshop.dc2020.restaurant_provider.remote.RestaurantsRemoteDataSourceImpl
import dagger.Binds
import dagger.Module

@Module
abstract class RestaurantProviderModule {

    @Binds
    internal abstract fun bindsRestaurantRemoteDataSource(impl: RestaurantsRemoteDataSourceImpl): RestaurantsRemoteDataSource
}