package com.deliveryhero.workshop.dc2020.ui.rdp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.deliveryhero.workshop.dc2020.data.restaurant.RestaurantsRepo
import com.deliveryhero.workshop.dc2020.data.restaurant.domain.Restaurant
import javax.inject.Inject

class RdpViewModel @Inject constructor(private val restaurantsRepo: RestaurantsRepo) : ViewModel() {

    fun loadRestaurantDetails(id: Int) =
        liveData<Result<Restaurant>> {
            try {
                emit(Result.success(restaurantsRepo.getRestaurantDetails(id)))
            } catch (exception: Exception) {
                emit(Result.failure(exception))
            }
        }
}
