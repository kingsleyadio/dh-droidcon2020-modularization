package com.deliveryhero.workshop.dc2020.ui.launcher

import com.deliveryhero.workshop.dc2020.data.config.ConfigRepository
import com.deliveryhero.workshop.dc2020.data.config.domain.Config
import com.deliveryhero.workshop.dc2020.testcommon.testRules
import com.deliveryhero.workshop.dc2020.testcommon.withObserver
import com.nhaarman.mockitokotlin2.*
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test

class LauncherViewModelTest {

    @get:Rule
    val rules = testRules()

    private val configRepository = mock<ConfigRepository>()
    private val viewModel = LauncherViewModel(configRepository)

    @Test
    fun `emit success result when config download completes successfully`() = runBlockingTest {
        whenever(configRepository.getConfig()).thenReturn(Config("$"))

        viewModel.initializerLiveData.withObserver {
            verify(it).onChanged(true)
        }
    }

    @Test
    fun `emit failure result when config download fails`() = runBlockingTest {
        whenever(configRepository.getConfig()).thenAnswer { error("Failed") }

        viewModel.initializerLiveData.withObserver {
            verify(it).onChanged(false)
        }
    }

    @Test
    fun `trigger another emission when reload is called`() = runBlockingTest {
        whenever(configRepository.getConfig()).thenReturn(Config("$"))

        viewModel.initializerLiveData.withObserver {
            viewModel.reload()
            verify(it, times(2)).onChanged(any())
        }
    }
}
