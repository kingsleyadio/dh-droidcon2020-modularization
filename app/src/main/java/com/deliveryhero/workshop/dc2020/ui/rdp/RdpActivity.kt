package com.deliveryhero.workshop.dc2020.ui.rdp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.deliveryhero.workshop.dc2020.data.restaurant.domain.Restaurant
import com.deliveryhero.workshop.dc2020.databinding.ActivityRdpBinding
import com.deliveryhero.workshop.dc2020.ui.common.ViewModelFactory
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class RdpActivity : DaggerAppCompatActivity() {

    companion object {
        private const val EXTRA_ID = "EXTRA_ID"

        fun newIntent(context: Context, id: Int) = Intent(context, RdpActivity::class.java).also {
            it.putExtra(EXTRA_ID, id)
        }
    }

    private lateinit var binding: ActivityRdpBinding

    @Inject
    lateinit var viewModeFactory: ViewModelFactory<RdpViewModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRdpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launchWhenCreated { loadDetails() }
    }

    private fun loadDetails() {
        val viewModel = ViewModelProvider(this, viewModeFactory).get<RdpViewModel>()
        viewModel.loadRestaurantDetails(intent.getIntExtra(EXTRA_ID, 0))
            .observe(this, { displayRestaurantDetails(it.getOrThrow()) })
    }

    private fun displayRestaurantDetails(restaurant: Restaurant) {
        Glide.with(binding.restaurantImage).load(restaurant.imgUrl).into(binding.restaurantImage)
        binding.restaurantName.text = restaurant.name
    }
}
