package com.newposandroid.presentation.main.home

import android.graphics.Rect
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.newposandroid.R
import com.newposandroid.base.BaseFragment
import com.newposandroid.databinding.FragmentHomeBinding
import com.newposandroid.utils.BouncyEdgeEffectFactory
import com.newposandroid.utils.Resource
import com.newposandroid.utils.toast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    private val productHorizontalAdapter by lazy { ProductHorizontalAdapter() }
    private val productGridAdapter by lazy { ProductGridAdapter() }

    private lateinit var viewModel: HomeViewModel

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHomeBinding = FragmentHomeBinding.inflate(inflater, container, false)

    override fun FragmentHomeBinding.initBinding() {
        viewModel = ViewModelProvider(requireActivity())[HomeViewModel::class.java]
        renderContent()
    }

    private fun FragmentHomeBinding.renderContent() {
        btnGridView.apply {
            ivIcon.setImageResource(R.drawable.ic_grid)
            ivIcon.setOnClickListener {
                if (rvProductsHorizontal.isVisible) {
                    rvProductsGrid.visibility = View.VISIBLE
                    rvProductsHorizontal.visibility = View.GONE
                } else {
                    rvProductsGrid.visibility = View.GONE
                    rvProductsHorizontal.visibility = View.VISIBLE
                }
            }
        }

        btnGridView.btnCircle.setOnClickListener {

        }

        rvProductsHorizontal.run {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            adapter = productHorizontalAdapter
            edgeEffectFactory = BouncyEdgeEffectFactory()
        }

        rvProductsGrid.run {
            addItemDecoration(GridSpacingItemDecoration(2, 30))
            layoutManager = GridLayoutManager(context, 2)
            adapter = productGridAdapter
            edgeEffectFactory = BouncyEdgeEffectFactory()
        }
    }

    override suspend fun FragmentHomeBinding.observer() {
        viewModel.listProduct.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Success -> {
                    it.data?.let { products ->
                        productHorizontalAdapter.setItems(products)
                        productGridAdapter.setItems(products)
                    }
                }
                else -> {}
            }
        }
    }
}

private class GridSpacingItemDecoration(private val spanCount: Int, private val spacing: Int) :
    RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view) // item position
        val column = position % spanCount // item column

        outRect.left = spacing - column * spacing / spanCount
        outRect.right = (column + 1) * spacing / spanCount

        if (position < spanCount) { // top edge
            outRect.top = spacing
        }
        outRect.bottom = spacing // item bottom
    }
}