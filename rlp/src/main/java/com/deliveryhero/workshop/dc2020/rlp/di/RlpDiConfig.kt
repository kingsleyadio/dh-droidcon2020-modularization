package com.deliveryhero.workshop.dc2020.rlp.di

import com.deliveryhero.workshop.dc2020.localization.StringLocalizer
import com.deliveryhero.workshop.dc2020.restaurant_provider.RestaurantsRepo
import com.deliveryhero.workshop.dc2020.rlp.RdpLauncher

interface RlpDiConfig {
    val restaurantRepo: RestaurantsRepo
    val stringLocalizer: StringLocalizer
    val rdpLauncher: RdpLauncher
}