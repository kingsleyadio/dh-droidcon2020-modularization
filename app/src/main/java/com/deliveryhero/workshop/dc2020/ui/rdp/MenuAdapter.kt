package com.deliveryhero.workshop.dc2020.ui.rdp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.deliveryhero.workshop.dc2020.data.restaurant.domain.Menu
import com.deliveryhero.workshop.dc2020.data.restaurant.domain.Product
import com.deliveryhero.workshop.dc2020.databinding.MenuHeaderItemBinding
import com.deliveryhero.workshop.dc2020.databinding.MenuItemBinding
import com.deliveryhero.workshop.dc2020.ui.rdp.MenuAdapter.AdapterItem.HeaderItem
import com.deliveryhero.workshop.dc2020.ui.rdp.MenuAdapter.AdapterItem.ProductItem

class MenuAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val TYPE_ITEM = 0
        const val TYPE_HEADER = 1
    }

    sealed class AdapterItem(val viewType: Int) {
        class HeaderItem(val title: String) : AdapterItem(TYPE_HEADER)
        class ProductItem(val product: Product) : AdapterItem(TYPE_ITEM)
    }

    private var menuItems: List<AdapterItem> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    fun updateMenu(menu: Menu) {
        val list = mutableListOf<AdapterItem>()
        menu.categories.forEach { category ->
            list.add(HeaderItem(category.title))
            list.addAll(category.products.map { ProductItem(it) })
        }
        menuItems = list
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == TYPE_HEADER)
            MenuHeaderViewHolder(MenuHeaderViewHolder.createBinding(parent))
        else MenuItemViewHolder(MenuItemViewHolder.createBinding(parent))
    }

    override fun getItemCount(): Int = menuItems.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is MenuHeaderViewHolder) {
            holder.bind(menuItems[position] as HeaderItem)
        } else if (holder is MenuItemViewHolder) {
            holder.bind(menuItems[position] as ProductItem)
        }
    }

    override fun getItemViewType(position: Int) = menuItems[position].viewType
}

class MenuHeaderViewHolder(
    private val binding: MenuHeaderItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun createBinding(parent: ViewGroup): MenuHeaderItemBinding {
            return MenuHeaderItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        }
    }

    fun bind(item: HeaderItem) {
        binding.categoryName.text = item.title
    }
}

class MenuItemViewHolder(
    private val binding: MenuItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun createBinding(parent: ViewGroup): MenuItemBinding {
            return MenuItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        }
    }

    fun bind(item: ProductItem) {
        binding.run {
            productName.text = item.product.name
            Glide.with(productImage).load(item.product.imgUrl).into(productImage)
            productPrice.text = "${item.product.price} $"
            ingredients.text = item.product.ingredients
        }

    }
}
