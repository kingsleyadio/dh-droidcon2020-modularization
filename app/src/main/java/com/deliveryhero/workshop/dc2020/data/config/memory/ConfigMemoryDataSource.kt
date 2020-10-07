package com.deliveryhero.workshop.dc2020.data.config.memory

import com.deliveryhero.workshop.dc2020.data.config.domain.Config
import dagger.Reusable
import javax.inject.Inject

interface ConfigMemoryDataSource {
    var config: Config?
}

@Reusable
class ConfigMemoryDataSourceImpl @Inject constructor(): ConfigMemoryDataSource {

    override var config: Config? = null
}
