package com.deliveryhero.workshop.dc2020.data.restaurant

import com.deliveryhero.workshop.dc2020.data.restaurant.remote.RestaurantsRemoteDataSource
import javax.inject.Inject

class RestaurantsRepo @Inject constructor(
    private val remoteDataSource: RestaurantsRemoteDataSource
) {

    suspend fun getRestaurants() = remoteDataSource.getRestaurants()

    suspend fun getRestaurantDetails(id: Int) = remoteDataSource.getRestaurantDetails(id)
}