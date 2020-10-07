package com.deliveryhero.workshop.dc2020.data.restaurant.domain

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