package com.deliveryhero.workshop.dc2020.data.config.memory

import com.deliveryhero.workshop.dc2020.data.config.domain.Config
import dagger.Reusable
import javax.inject.Inject

interface ConfigMemoryDataSource {
    val config: Config?
}

@Reusable
class ConfigMemoryDataSourceImpl @Inject constructor(): ConfigMemoryDataSource {

    override val config: Config? = null
}
