package com.deliveryhero.workshop.dc2020.ui.rlp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.deliveryhero.translation.generated.TranslationKeys
import com.deliveryhero.workshop.dc2020.R
import com.deliveryhero.workshop.dc2020.data.restaurant.domain.Restaurant
import com.deliveryhero.workshop.dc2020.databinding.ActivityRlpBinding
import com.deliveryhero.workshop.dc2020.localization.StringLocalizer
import com.deliveryhero.workshop.dc2020.mvvm_common.ViewModelFactory
import com.deliveryhero.workshop.dc2020.ui.rdp.RdpActivity
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class RlpActivity : DaggerAppCompatActivity() {

    companion object {

        fun newIntent(context: Context): Intent {
            return Intent(context, RlpActivity::class.java)
        }
    }

    @Inject
    lateinit var stringLocalizer: StringLocalizer

    @Inject
    lateinit var viewModelFactory: ViewModelFactory<RlpViewModel>
    private val viewModel by viewModels<RlpViewModel> { viewModelFactory }

    private lateinit var binding: ActivityRlpBinding
    private lateinit var restaurantsAdapter: RestaurantsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRlpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
        viewModel.restaurantsLiveData.observe(this, ::handleRestaurants)
        viewModel.restaurantSelectedLiveData.observe(this, ::handleRestaurantSelection)
        viewModel.loadRestaurants()
    }

    private fun initViews() {
        with(binding.recyclerView) {
            layoutManager = LinearLayoutManager(context)
            restaurantsAdapter = RestaurantsAdapter(viewModel::onRestaurantSelected)
            adapter = restaurantsAdapter
        }
        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.loadRestaurants()
        }
        setSupportActionBar(binding.toolbar)
        title = getString(R.string.app_name)
    }

    private fun handleRestaurants(result: Result<List<Restaurant>>) {
        val restaurants = result.onFailure {
            Toast.makeText(
                this,
                stringLocalizer.getText(TranslationKeys.RESTAURANT_LIST_FAILED),
                Toast.LENGTH_SHORT
            ).show()
        }.getOrDefault(emptyList())

        binding.swipeRefreshLayout.isRefreshing = false
        binding.textViewEmpty.isVisible = restaurants.isEmpty()
        restaurantsAdapter.refresh(restaurants)
    }

    private fun handleRestaurantSelection(restaurant: Restaurant) {
        startActivity(RdpActivity.newIntent(this, restaurant.id))
    }
}
