package com.deliveryhero.workshop.dc2020.ui.rlp

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.deliveryhero.workshop.dc2020.restaurant_provider.domain.Restaurant
import com.deliveryhero.workshop.dc2020.restaurant_provider.domain.popularityKey
import com.deliveryhero.workshop.dc2020.databinding.ItemRestaurantsBinding
import com.deliveryhero.workshop.dc2020.localization.StringLocalizer

class RestaurantsAdapter(
    private val onClick: (Restaurant) -> Unit
) : RecyclerView.Adapter<RestaurantsAdapter.ViewHolder>() {

    private val restaurants = arrayListOf<Restaurant>()

    fun refresh(data: List<Restaurant>) {
        restaurants.clear()
        restaurants.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemRestaurantsBinding.inflate(inflater, parent, false)
        return ViewHolder(binding).apply {
            binding.root.setOnClickListener {
                restaurants.getOrNull(adapterPosition)?.let(onClick)
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(restaurants[position])
    }

    override fun getItemCount(): Int {
        return restaurants.size
    }

    class ViewHolder(private val binding: ItemRestaurantsBinding) : RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(restaurant: Restaurant) = with(binding) {
            restaurantCharacteristicsTextView.text = run {
                val priceTier = "$".repeat(restaurant.priceTier)
                val characteristics = restaurant.topCuisines.joinToString(", ")
                "$priceTier \u2022 $characteristics"
            }

            Glide.with(restaurantImageView).load(restaurant.imgUrl).into(restaurantImageView)
            restaurantNameTextView.text = restaurant.name
            restaurantPopularityTextView.text = StringLocalizer.getText(restaurant.popularityKey)
            restaurantPrimaryTag.text = "${restaurant.avgRating} \u2605"
        }
    }
}
