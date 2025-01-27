package com.newposandroid.presentation.main.report

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.newposandroid.base.BaseAdapter
import com.newposandroid.databinding.ItemHorizontailChartBinding
import com.newposandroid.domain.model.SellingProduct

class SellingProductAdapter : BaseAdapter<SellingProduct>() {
    override fun getViewType(position: Int): Int = 0

    override fun getViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return SellingProductViewHolder(
            ItemHorizontailChartBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is SellingProductViewHolder) {
            val selling = items[position]
            selling?.let { holder.binding.bind(selling) }
        }
    }

    private class SellingProductViewHolder(val binding: ItemHorizontailChartBinding) :
        RecyclerView.ViewHolder(binding.root)

    @SuppressLint("SetTextI18n")
    private fun ItemHorizontailChartBinding.bind(sellingProduct: SellingProduct?) {
        tvProductName.text = sellingProduct?.name
        tvSalesInfo.text = sellingProduct?.price
        progressBar.progress = sellingProduct?.percentage ?: 0
        tvPercentage.text = "${sellingProduct?.percentage}%"
        tvSales.text = "50 sales"
    }
}