package com.android_pos.presentation.auth.signup

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.android_pos.R
import com.android_pos.base.BaseFragment
import com.android_pos.databinding.FragmentSignupBinding

class SignUpFragment : BaseFragment<FragmentSignupBinding>() {
    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSignupBinding = FragmentSignupBinding.inflate(inflater, container, false)


    override fun FragmentSignupBinding.initBinding() {
        appbar.tvTitle.text = "Sign Up"
        appbar.ivBack.setOnClickListener {
            findNavController().popBackStack()
        }

        tvSignup.setOnClickListener {
            findNavController().navigate(R.id.action_fragmentSignUp_to_fragmentLogin)
        }

        tfUsername.tvTitle.text = "Username"
        tfUsername.etInput.hint = "Masukkan username"

        tfPhoneNumber.tvTitle.text = "Phone number"
        tfPhoneNumber.etInput.hint = "ex: 08123456789"

        tfPassword.tvTitle.text = "Password"
        tfPassword.etInput.hint = "At least 8 characters"

        tfConfirmPassword.tvTitle.text = "Confirm password"
        tfConfirmPassword.etInput.hint = "At least 8 characters"

        btnSignup.tvTitle.text = "Sign Up"
    }

    override suspend fun FragmentSignupBinding.setupEvent() {
    }

    override suspend fun FragmentSignupBinding.setupState() {
    }

}