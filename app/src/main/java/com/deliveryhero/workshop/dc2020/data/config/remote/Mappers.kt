package com.deliveryhero.workshop.dc2020.data.config.remote

import com.deliveryhero.workshop.dc2020.data.config.domain.Config

fun ConfigApiModel.mapToDomain() = Config(currencySymbol, maxPriceTiers, addToFavoritesEnabled)
