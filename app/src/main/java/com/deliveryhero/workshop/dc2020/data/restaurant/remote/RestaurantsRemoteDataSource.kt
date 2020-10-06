package com.deliveryhero.workshop.dc2020.data.restaurant.remote

import com.deliveryhero.workshop.dc2020.data.restaurant.domain.Restaurant
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface RestaurantsRemoteDataSource {

    suspend fun getRestaurants(): List<Restaurant>

    suspend fun getRestaurantDetails(id: Int): Restaurant
}

class RestaurantsRemoteDataSourceImpl(
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