package com.newposandroid.presentation.main.add_product.photo

import android.view.LayoutInflater
import android.view.ViewGroup
import com.newposandroid.base.BaseFragment
import com.newposandroid.databinding.FragmentPhotoProductBinding

class PhotoProductFragment : BaseFragment<FragmentPhotoProductBinding>() {
    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentPhotoProductBinding = FragmentPhotoProductBinding.inflate(inflater, container, false)

    override suspend fun FragmentPhotoProductBinding.setupEvent() {
    }

    override suspend fun FragmentPhotoProductBinding.setupState() {


    }


    override fun FragmentPhotoProductBinding.initBinding() {
        renderContent()
    }

    private fun FragmentPhotoProductBinding.renderContent() {

    }
}