package com.deliveryhero.workshop.dc2020.data.restaurant.remote

import com.deliveryhero.workshop.dc2020.data.restaurant.domain.Restaurant

fun RestaurantApiModel.mapToDomain() =
    Restaurant(id, name, imgUrl, avgRating, topCuisines, distance, priceTier, popularity)