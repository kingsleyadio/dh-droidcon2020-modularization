package com.deliveryhero.workshop.dc2020.configs.remote

import com.deliveryhero.workshop.dc2020.configs.domain.Config
import dagger.Reusable
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Inject

internal interface ConfigRemoteDataSource {

    suspend fun getConfig(): Config
}

@Reusable
internal class ConfigRemoteDataSourceImpl @Inject constructor(retrofit: Retrofit) : ConfigRemoteDataSource {

    private val api by lazy<ConfigApi>(LazyThreadSafetyMode.NONE) { retrofit.create() }

    override suspend fun getConfig(): Config {
        return api.getConfig().mapToDomain()
    }
}
