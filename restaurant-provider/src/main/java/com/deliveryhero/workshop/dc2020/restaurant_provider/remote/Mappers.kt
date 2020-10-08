package com.deliveryhero.workshop.dc2020.restaurant_provider.remote

import com.deliveryhero.workshop.dc2020.restaurant_provider.domain.Category
import com.deliveryhero.workshop.dc2020.restaurant_provider.domain.Menu
import com.deliveryhero.workshop.dc2020.restaurant_provider.domain.Product
import com.deliveryhero.workshop.dc2020.restaurant_provider.domain.Restaurant

internal fun RestaurantApiModel.mapToDomain() =
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

internal fun CategoryApiModel.mapToDomain() =
    Category(title, products.map(ProductApiModel::mapToDomain))

internal fun ProductApiModel.mapToDomain() =
    Product(id, name, imgUrl, ingredients, price)
