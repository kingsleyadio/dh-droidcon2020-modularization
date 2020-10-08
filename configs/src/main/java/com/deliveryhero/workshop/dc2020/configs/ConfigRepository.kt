package com.deliveryhero.workshop.dc2020.configs

import com.deliveryhero.workshop.dc2020.configs.domain.Config
import com.deliveryhero.workshop.dc2020.configs.memory.ConfigMemoryDataSource
import com.deliveryhero.workshop.dc2020.configs.remote.ConfigRemoteDataSource
import javax.inject.Inject

class ConfigRepository @Inject internal constructor(
    private val memory: ConfigMemoryDataSource,
    private val remote: ConfigRemoteDataSource
) {

    suspend fun getConfig(): Config {
        return memory.config ?: remote.getConfig().also { memory.config = it }
    }
}
