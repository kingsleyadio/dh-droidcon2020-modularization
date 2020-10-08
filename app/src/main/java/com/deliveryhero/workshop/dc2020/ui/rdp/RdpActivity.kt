package com.deliveryhero.workshop.dc2020.ui.rdp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.deliveryhero.translation.generated.TranslationKeys
import com.deliveryhero.workshop.dc2020.restaurant_provider.domain.Restaurant
import com.deliveryhero.workshop.dc2020.databinding.ActivityRdpBinding
import com.deliveryhero.workshop.dc2020.localization.StringLocalizer
import com.deliveryhero.workshop.dc2020.mvvm_common.ViewModelFactory
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_rdp.*
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
    lateinit var stringLocalizer: StringLocalizer
    @Inject
    lateinit var viewModeFactory: ViewModelFactory<RdpViewModel>

    override fun onCreate(savedInstanceState: Bundle?) {
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

    private fun displayRestaurantDetails(restaurant: Restaurant) {
        Glide.with(binding.restaurantImage).load(restaurant.imgUrl).into(binding.restaurantImage)
        binding.collapsingToolbar.title = restaurant.name

        val menuAdapter = MenuAdapter()
        menuRecyclerView.layoutManager = LinearLayoutManager(this)
        menuRecyclerView.adapter = menuAdapter
        menuAdapter.updateMenu(restaurant.menu)
    }
}
