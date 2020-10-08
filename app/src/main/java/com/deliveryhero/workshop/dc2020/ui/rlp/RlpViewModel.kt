package com.deliveryhero.workshop.dc2020.ui.rlp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.deliveryhero.workshop.dc2020.restaurant_provider.RestaurantsRepo
import com.deliveryhero.workshop.dc2020.restaurant_provider.domain.Restaurant
import kotlinx.coroutines.launch
import javax.inject.Inject

class RlpViewModel @Inject constructor(private val restaurantRepository: RestaurantsRepo): ViewModel() {

    private val restaurants = MutableLiveData<Result<List<Restaurant>>>()
    val restaurantsLiveData: LiveData<Result<List<Restaurant>>> get() = restaurants

    private val restaurantSelected = MutableLiveData<Restaurant>()
    val restaurantSelectedLiveData: LiveData<Restaurant> get() = restaurantSelected

    fun loadRestaurants() {
        viewModelScope.launch {
            val result = runCatching { restaurantRepository.getRestaurants() }
            restaurants.value = result
        }
    }

    fun onRestaurantSelected(restaurant: Restaurant) {
        restaurantSelected.value = restaurant
    }
}
