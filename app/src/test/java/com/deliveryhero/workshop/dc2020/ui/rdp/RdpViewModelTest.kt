package com.deliveryhero.workshop.dc2020.ui.rdp

import com.deliveryhero.workshop.dc2020.data.restaurant.RestaurantsRepo
import com.deliveryhero.workshop.dc2020.testcommon.testRules
import com.deliveryhero.workshop.dc2020.testcommon.withObserver
import com.nhaarman.mockitokotlin2.*
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test

class RdpViewModelTest {

    @get:Rule
    val rules = testRules()

    private val restaurantRepository = mock<RestaurantsRepo>()
    private val viewModel = RdpViewModel(restaurantRepository)

    @Test
    fun `emit success result when restaurant list downloads successfully`() = runBlockingTest {
        whenever(restaurantRepository.getRestaurantDetails(any())).thenReturn(mock())

        viewModel.loadRestaurantDetails(1).withObserver { observer ->
            verify(observer).onChanged(argWhere { it.isSuccess })
            verify(restaurantRepository).getRestaurantDetails(eq(1))
        }
    }

    @Test
    fun `emit failure result when restaurant list download fails`() = runBlockingTest {
        whenever(restaurantRepository.getRestaurantDetails(any())).thenAnswer { error("Failed") }

        viewModel.loadRestaurantDetails(1).withObserver { observer ->
            verify(observer).onChanged(argWhere { it.isFailure })
            verify(restaurantRepository).getRestaurantDetails(eq(1))
        }
    }
}
