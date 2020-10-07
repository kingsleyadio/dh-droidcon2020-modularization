package com.deliveryhero.workshop.dc2020.data.config.remote

import com.deliveryhero.workshop.dc2020.data.config.domain.Config
import dagger.Reusable
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Inject

interface ConfigRemoteDataSource {

    suspend fun getConfig(): Config
}

@Reusable
class ConfigRemoteDataSourceImpl @Inject constructor(retrofit: Retrofit) : ConfigRemoteDataSource {

    private val api by lazy<ConfigApi>(LazyThreadSafetyMode.NONE) { retrofit.create() }

    override suspend fun getConfig(): Config {
        return api.getConfig().mapToDomain()
    }
}
