package com.deliveryhero.workshop.dc2020.data.restaurant.domain

data class Product(
    val id: Int,
    val name: String,
    val imgUrl: String,
    val ingredients: String,
    val price: Double
)