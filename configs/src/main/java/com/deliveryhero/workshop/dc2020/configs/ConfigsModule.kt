package com.deliveryhero.workshop.dc2020.configs

import com.deliveryhero.workshop.dc2020.configs.memory.ConfigMemoryDataSource
import com.deliveryhero.workshop.dc2020.configs.memory.ConfigMemoryDataSourceImpl
import com.deliveryhero.workshop.dc2020.configs.remote.ConfigRemoteDataSource
import com.deliveryhero.workshop.dc2020.configs.remote.ConfigRemoteDataSourceImpl
import dagger.Binds
import dagger.Module

@Module
abstract class ConfigsModule {

    @Binds
    internal abstract fun bindsConfigRemoteDataSource(impl: ConfigRemoteDataSourceImpl): ConfigRemoteDataSource

    @Binds
    internal abstract fun bindsConfigMemoryDataSource(impl: ConfigMemoryDataSourceImpl): ConfigMemoryDataSource
}
