package com.newposandroid.presentation.main.add_product.form

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.newposandroid.base.BaseFragment
import com.newposandroid.databinding.FragmentFormProductBinding

class FormProductFragment : BaseFragment<FragmentFormProductBinding>() {
    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentFormProductBinding = FragmentFormProductBinding.inflate(inflater, container, false)

    override suspend fun FragmentFormProductBinding.observer() {

    }

    override fun FragmentFormProductBinding.initBinding() {
        renderContent()
    }

    private fun FragmentFormProductBinding.renderContent() {
        tfProductName.apply {
            tvTitle.text = "Nama Produk"
            etInput.hint = "Masukkan nama produk"
        }

        tfProductDescription.apply {
            tvTitle.text = "Deskripsi Produk"
            etInput.hint = "Masukkan deskripsi produk"
        }
    }
}