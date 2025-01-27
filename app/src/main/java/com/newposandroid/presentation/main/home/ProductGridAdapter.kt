package com.newposandroid.presentation.main.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.newposandroid.R
import com.newposandroid.base.BaseAdapter
import com.newposandroid.databinding.ItemProductGridBinding
import com.newposandroid.domain.model.Product

class ProductGridAdapter : BaseAdapter<Product>() {
    override fun getViewType(position: Int): Int = 0

    override fun getViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ProductGridViewHolder(
            ItemProductGridBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ProductGridViewHolder) {
            val product = items[position]
            product.let { holder.binding.bind(product) }
        }
    }

    private class ProductGridViewHolder(val binding: ItemProductGridBinding) :
        RecyclerView.ViewHolder(binding.root)

    @SuppressLint("SetTextI18n")
    private fun ItemProductGridBinding.bind(product: Product?) {
        tvProductTitle.text = product?.name
        tvProductDesc.text = product?.desc
        tvProductPrice.text = "$${product?.price}"
        ivProduct.load(product?.image) {
            placeholder(R.drawable.ic_broken_image)

        }
    }
}