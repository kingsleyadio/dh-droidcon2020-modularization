package com.deliveryhero.workshop.dc2020.configs

import com.deliveryhero.workshop.dc2020.configs.domain.Config
import com.deliveryhero.workshop.dc2020.configs.memory.ConfigMemoryDataSource
import com.deliveryhero.workshop.dc2020.configs.remote.ConfigRemoteDataSource
import com.deliveryhero.workshop.dc2020.testcommon.testRules
import com.nhaarman.mockitokotlin2.*
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class ConfigRepositoryTest {

    @get:Rule
    val rules = testRules()

    private val memoryDataSource = mock<ConfigMemoryDataSource>()
    private val remoteDataSource = mock<ConfigRemoteDataSource>()
    private val configRepository = ConfigRepository(memoryDataSource, remoteDataSource)

    @Test
    fun `gets config from memory if already exists`() = runBlockingTest {
        val config = Config("$")
        whenever(memoryDataSource.config).thenReturn(config)

        val result = configRepository.getConfig()
        assertEquals(config, result)
        verify(memoryDataSource).config
        verify(remoteDataSource, never()).getConfig()
    }

    @Test
    fun `gets config from remote and write to memory if not exist`() = runBlockingTest {
        assertNull(memoryDataSource.config)

        val config = Config("$")
        whenever(remoteDataSource.getConfig()).thenReturn(config)

        val result = configRepository.getConfig()
        assertEquals(config, result)
        verify(remoteDataSource).getConfig()
        verify(memoryDataSource).config = eq(config)
    }
}
