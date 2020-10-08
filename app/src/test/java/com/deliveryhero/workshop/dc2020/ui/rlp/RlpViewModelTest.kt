package com.deliveryhero.workshop.dc2020.ui.rlp

import com.deliveryhero.workshop.dc2020.data.restaurant.RestaurantsRepo
import com.deliveryhero.workshop.dc2020.data.restaurant.domain.Restaurant
import com.deliveryhero.workshop.dc2020.testcommon.testRules
import com.deliveryhero.workshop.dc2020.testcommon.withObserver
import com.nhaarman.mockitokotlin2.argWhere
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test

class RlpViewModelTest {

    @get:Rule
    val rules = testRules()

    private val restaurantRepository = mock<RestaurantsRepo>()
    private val viewModel = RlpViewModel(restaurantRepository)

    @Test
    fun `emit success result when restaurant list downloads successfully`() = runBlockingTest {
        whenever(restaurantRepository.getRestaurants()).thenReturn(emptyList())

        viewModel.restaurantsLiveData.withObserver { observer ->
            viewModel.loadRestaurants()
            verify(observer).onChanged(argWhere { it.isSuccess })
        }
    }

    @Test
    fun `emit failure result when restaurant list download fails`() = runBlockingTest {
        whenever(restaurantRepository.getRestaurants()).thenAnswer { error("Failed") }

        viewModel.restaurantsLiveData.withObserver { observer ->
            viewModel.loadRestaurants()
            verify(observer).onChanged(argWhere { it.isFailure })
        }
    }

    @Test
    fun `emit selected restaurant on selection`() {
        viewModel.restaurantSelectedLiveData.withObserver {
            val restaurant = mock<Restaurant>()
            viewModel.onRestaurantSelected(restaurant)

            verify(it).onChanged(restaurant)
        }
    }
}
