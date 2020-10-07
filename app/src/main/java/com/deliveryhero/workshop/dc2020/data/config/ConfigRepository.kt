package com.deliveryhero.workshop.dc2020.data.config

import com.deliveryhero.workshop.dc2020.data.config.domain.Config
import com.deliveryhero.workshop.dc2020.data.config.memory.ConfigMemoryDataSource
import com.deliveryhero.workshop.dc2020.data.config.remote.ConfigRemoteDataSource
import javax.inject.Inject

class ConfigRepository @Inject constructor(
    private val memory: ConfigMemoryDataSource,
    private val remote: ConfigRemoteDataSource
) {

    suspend fun getConfig(): Config {
        return memory.config ?: remote.getConfig()
    }
}
