package com.deliveryhero.workshop.dc2020.restaurant_provider.remote

import com.deliveryhero.workshop.dc2020.restaurant_provider.domain.Restaurant
import retrofit2.Retrofit
import javax.inject.Inject

internal interface RestaurantsRemoteDataSource {

    suspend fun getRestaurants(): List<Restaurant>

    suspend fun getRestaurantDetails(id: Int): Restaurant
}

internal class RestaurantsRemoteDataSourceImpl @Inject constructor(
    private val retrofit: Retrofit
) : RestaurantsRemoteDataSource {

    private val api by lazy {
        retrofit.create(RestaurantsApi::class.java)
    }

    override suspend fun getRestaurants(): List<Restaurant> = api.getRestaurants()
        .map(RestaurantApiModel::mapToDomain)

    override suspend fun getRestaurantDetails(id: Int): Restaurant =
        api.getRestaurantDetails(id).mapToDomain()
}
