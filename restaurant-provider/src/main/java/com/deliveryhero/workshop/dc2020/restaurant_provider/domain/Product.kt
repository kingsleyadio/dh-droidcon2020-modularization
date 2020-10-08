package com.deliveryhero.workshop.dc2020.restaurant_provider.domain

data class Product(
    val id: Int,
    val name: String,
    val imgUrl: String,
    val ingredients: String,
    val price: Double
)