package com.newposandroid.presentation.main.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.newposandroid.base.BaseFragment
import com.newposandroid.databinding.FragmentProfileBinding

class ProfileFragment : BaseFragment<FragmentProfileBinding>() {
    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentProfileBinding = FragmentProfileBinding.inflate(inflater, container, false)

    override suspend fun FragmentProfileBinding.setupEvent() {
    }

    override suspend fun FragmentProfileBinding.setupState() {


    }


    override fun FragmentProfileBinding.initBinding() {
        renderContent()
    }

    private fun FragmentProfileBinding.renderContent() {
        tfUsername.apply {
            tvTitle.text = "Username"
            etInput.hint = "Abdul Shop"
        }

        tfPhoneNumber.apply {
            tvTitle.text = "Nomor handphone"
            etInput.hint = "081234567890"
        }

        tfEmail.apply {
            tvTitle.text = "Email"
            etInput.hint = "abdulgani@gmail.com"
        }
    }
}