package com.deliveryhero.workshop.dc2020.data.restaurant.domain

import com.deliveryhero.translation.generated.TranslationKeys

data class Restaurant(
    val id: Int,
    val name: String,
    val imgUrl: String,
    val avgRating: Double,
    val topCuisines: List<String>,
    val distance: Int,
    val priceTier: Int,
    val popularity: Int,
    val menu: Menu
)

val Restaurant.popularityKey: String
    get() = when (popularity) {
        in 0..99 -> TranslationKeys.NOT_POPULAR
        in 100..399 -> TranslationKeys.POPULAR
        in 400..699 -> TranslationKeys.VERY_POPULAR
        else -> TranslationKeys.UNKNOWN
    }
