package com.deliveryhero.workshop.dc2020.configs.remote

import com.google.gson.annotations.SerializedName
import retrofit2.http.GET

internal interface ConfigApi {

    @GET("config")
    suspend fun getConfig(): ConfigApiModel
}

internal class ConfigApiModel(
    @SerializedName("currency_symbol") val currencySymbol: String,
    @SerializedName("max_price_tiers") val maxPriceTiers: Int,
    @SerializedName("add_to_favorites_enabled") val addToFavoritesEnabled: Boolean
)
