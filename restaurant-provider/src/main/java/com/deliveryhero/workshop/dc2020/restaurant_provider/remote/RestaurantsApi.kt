package com.deliveryhero.workshop.dc2020.restaurant_provider.remote

import com.google.gson.annotations.SerializedName
import retrofit2.http.GET
import retrofit2.http.Path

internal interface RestaurantsApi {
    @GET("restaurants")
    suspend fun getRestaurants(): List<RestaurantApiModel>

    @GET("restaurant-details/{id}")
    suspend fun getRestaurantDetails(@Path("id") id: Int): RestaurantApiModel
}

internal class RestaurantApiModel(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("image") val imgUrl: String,
    @SerializedName("average_rating") val avgRating: Double,
    @SerializedName("top_cuisines") val topCuisines: List<String>,
    @SerializedName("distance_in_meters") val distance: Int,
    @SerializedName("price_tier") val priceTier: Int,
    @SerializedName("popularity_score") val popularity: Int,
    @SerializedName("menu") val menu: List<CategoryApiModel>?
)

internal class CategoryApiModel(
    @SerializedName("category_title") val title: String,
    @SerializedName("products") val products: List<ProductApiModel>
)

internal class ProductApiModel(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("image") val imgUrl: String,
    @SerializedName("ingredients") val ingredients: String,
    @SerializedName("price") val price: Double
)
