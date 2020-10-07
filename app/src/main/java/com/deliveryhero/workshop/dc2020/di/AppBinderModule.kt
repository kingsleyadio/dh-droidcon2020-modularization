package com.deliveryhero.workshop.dc2020.di

import com.deliveryhero.workshop.dc2020.data.config.memory.ConfigMemoryDataSource
import com.deliveryhero.workshop.dc2020.data.config.memory.ConfigMemoryDataSourceImpl
import com.deliveryhero.workshop.dc2020.data.config.remote.ConfigRemoteDataSource
import com.deliveryhero.workshop.dc2020.data.config.remote.ConfigRemoteDataSourceImpl
import com.deliveryhero.workshop.dc2020.data.restaurant.remote.RestaurantsRemoteDataSource
import com.deliveryhero.workshop.dc2020.data.restaurant.remote.RestaurantsRemoteDataSourceImpl
import dagger.Binds
import dagger.Module

@Module
interface AppBinderModule {

    @Binds
    fun bindsConfigRemoteDataSource(impl: ConfigRemoteDataSourceImpl): ConfigRemoteDataSource

    @Binds
    fun bindsConfigMemoryDataSource(impl: ConfigMemoryDataSourceImpl): ConfigMemoryDataSource

    @Binds
    fun bindsRestaurantRemoteDataSource(impl: RestaurantsRemoteDataSourceImpl): RestaurantsRemoteDataSource
}
