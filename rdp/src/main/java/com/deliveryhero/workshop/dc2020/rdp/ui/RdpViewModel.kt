package com.deliveryhero.workshop.dc2020.rdp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.deliveryhero.workshop.dc2020.restaurant_provider.RestaurantsRepo
import javax.inject.Inject

internal class RdpViewModel @Inject constructor(
    private val restaurantsRepo: RestaurantsRepo
) : ViewModel() {

    fun loadRestaurantDetails(id: Int) = liveData {
        val result = runCatching { restaurantsRepo.getRestaurantDetails(id) }
        emit(result)
    }
}
