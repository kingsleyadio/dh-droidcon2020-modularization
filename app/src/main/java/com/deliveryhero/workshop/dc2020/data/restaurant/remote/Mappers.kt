package com.deliveryhero.workshop.dc2020.data.restaurant.remote

import com.deliveryhero.workshop.dc2020.data.restaurant.domain.Category
import com.deliveryhero.workshop.dc2020.data.restaurant.domain.Menu
import com.deliveryhero.workshop.dc2020.data.restaurant.domain.Product
import com.deliveryhero.workshop.dc2020.data.restaurant.domain.Restaurant

fun RestaurantApiModel.mapToDomain() =
    Restaurant(
        id,
        name,
        imgUrl,
        avgRating,
        topCuisines,
        distance,
        priceTier,
        popularity,
        Menu(menu.orEmpty().map(CategoryApiModel::mapToDomain))
    )

fun CategoryApiModel.mapToDomain() =
    Category(title, products.map(ProductApiModel::mapToDomain))

fun ProductApiModel.mapToDomain() =
    Product(id, name, imgUrl, ingredients, price)
