package com.deliveryhero.workshop.dc2020.data.config.remote

import com.google.gson.annotations.SerializedName
import retrofit2.http.GET

interface ConfigApi {

    @GET("config")
    suspend fun getConfig(): ConfigApiModel
}

class ConfigApiModel(
    @SerializedName("currency_symbol") val currencySymbol: String,
    @SerializedName("max_price_tiers") val maxPriceTiers: Int,
    @SerializedName("add_to_favorites_enabled") val addToFavoritesEnabled: Boolean
)
