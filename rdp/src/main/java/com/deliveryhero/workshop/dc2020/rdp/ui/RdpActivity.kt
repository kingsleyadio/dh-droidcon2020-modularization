package com.deliveryhero.workshop.dc2020.rdp.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.deliveryhero.translation.generated.TranslationKeys
import com.deliveryhero.workshop.dc2020.localization.StringLocalizer
import com.deliveryhero.workshop.dc2020.mvvm_common.ViewModelFactory
import com.deliveryhero.workshop.dc2020.rdp.RdpProvider
import com.deliveryhero.workshop.dc2020.rdp.databinding.ActivityRdpBinding
import com.deliveryhero.workshop.dc2020.restaurant_provider.domain.Restaurant
import javax.inject.Inject

internal class RdpActivity : AppCompatActivity() {

    companion object {
        private const val EXTRA_ID = "EXTRA_ID"

        fun newIntent(context: Context, id: Int) = Intent(context, RdpActivity::class.java).also {
            it.putExtra(EXTRA_ID, id)
        }
    }

    private lateinit var binding: ActivityRdpBinding

    @Inject
    lateinit var stringLocalizer: StringLocalizer
    @Inject
    lateinit var viewModeFactory: ViewModelFactory<RdpViewModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        RdpProvider.inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityRdpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initToolbar()
        lifecycleScope.launchWhenCreated { loadDetails() }
    }

    private fun initToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun loadDetails() {
        val viewModel = ViewModelProvider(this, viewModeFactory).get<RdpViewModel>()
        viewModel.loadRestaurantDetails(intent.getIntExtra(EXTRA_ID, 0))
            .observe(this, {
                if (it.isSuccess) {
                    displayRestaurantDetails(it.getOrThrow())
                } else {
                    AlertDialog.Builder(this)
                        .setCancelable(false)
                        .setMessage(stringLocalizer.getText(TranslationKeys.RESTAURANT_DETAILS_FAILED))
                        .setNegativeButton(stringLocalizer.getText(TranslationKeys.QUIT)) { _, _ -> finish() }
                        .setPositiveButton(stringLocalizer.getText(TranslationKeys.RETRY)) { _, _ -> loadDetails() }
                        .show()
                }
            })
    }

    private fun displayRestaurantDetails(restaurant: Restaurant) = with(binding) {
        Glide.with(restaurantImage).load(restaurant.imgUrl).into(restaurantImage)
        collapsingToolbar.title = restaurant.name

        val menuAdapter = MenuAdapter()
        menuRecyclerView.layoutManager = LinearLayoutManager(this@RdpActivity)
        menuRecyclerView.adapter = menuAdapter
        menuAdapter.updateMenu(restaurant.menu)
    }
}
