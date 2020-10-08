package com.deliveryhero.workshop.dc2020.configs.remote

import com.deliveryhero.workshop.dc2020.configs.domain.Config

internal fun ConfigApiModel.mapToDomain() = Config(currencySymbol, maxPriceTiers, addToFavoritesEnabled)
