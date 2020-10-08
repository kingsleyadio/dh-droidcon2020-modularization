package com.deliveryhero.workshop.dc2020.rdp.di

import com.deliveryhero.workshop.dc2020.localization.StringLocalizer
import com.deliveryhero.workshop.dc2020.restaurant_provider.RestaurantsRepo

interface RdpConfig {
    val restaurantRepository: RestaurantsRepo
    val stringLocalizer: StringLocalizer
}
