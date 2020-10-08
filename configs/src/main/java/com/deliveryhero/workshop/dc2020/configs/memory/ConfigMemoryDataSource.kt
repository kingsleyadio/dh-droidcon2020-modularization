package com.deliveryhero.workshop.dc2020.configs.memory

import com.deliveryhero.workshop.dc2020.configs.domain.Config
import dagger.Reusable
import javax.inject.Inject

internal interface ConfigMemoryDataSource {
    var config: Config?
}

@Reusable
internal class ConfigMemoryDataSourceImpl @Inject constructor(): ConfigMemoryDataSource {

    override var config: Config? = null
}
