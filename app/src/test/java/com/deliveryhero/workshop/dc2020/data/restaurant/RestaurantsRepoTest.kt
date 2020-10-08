package com.deliveryhero.workshop.dc2020.data.restaurant

import com.deliveryhero.workshop.dc2020.common.testRules
import com.deliveryhero.workshop.dc2020.data.restaurant.domain.Restaurant
import com.deliveryhero.workshop.dc2020.data.restaurant.remote.RestaurantsRemoteDataSource
import com.nhaarman.mockitokotlin2.*
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals

class RestaurantsRepoTest {

    @get:Rule
    val rules = testRules()

    private val dataSource = mock<RestaurantsRemoteDataSource>()
    private val repository = RestaurantsRepo(dataSource)

    @Test
    fun `gets restaurant list data from data source`() = runBlockingTest {
        val restaurantList = listOf(mock<Restaurant>())
        whenever(dataSource.getRestaurants()).thenReturn(restaurantList)

        val result = repository.getRestaurants()
        assertEquals(restaurantList, result)
        verify(dataSource).getRestaurants()
    }

    @Test
    fun `gets restaurant details from data source`() = runBlockingTest {
        val restaurant = mock<Restaurant>()
        whenever(dataSource.getRestaurantDetails(any())).thenReturn(restaurant)

        val result = repository.getRestaurantDetails(5)
        assertEquals(restaurant, result)
        verify(dataSource).getRestaurantDetails(eq(5))
    }
}
