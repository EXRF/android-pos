package com.android_pos.presentation.auth.login

import android.view.LayoutInflater
import android.view.ViewGroup
import com.android_pos.base.BaseFragment
import com.android_pos.databinding.FragmentLoginBinding
import androidx.navigation.fragment.findNavController
import com.android_pos.R

class LoginFragment : BaseFragment<FragmentLoginBinding>() {
    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentLoginBinding = FragmentLoginBinding.inflate(inflater, container, false)


    override fun FragmentLoginBinding.initBinding() {
        btnLogin.tvTitle.text = getString(R.string.login)

        tfUsername.tvTitle.text = "Username"
        tfUsername.etInput.hint = "Masukkan username"

        tfPassword.tvTitle.text = "Password"
        tfPassword.etInput.hint = "Masukkan password"

        appbar.ivBack.setOnClickListener {
            findNavController().popBackStack()
        }

        tvSignup.setOnClickListener {
            findNavController().navigate(R.id.action_fragmentLogin_to_fragmentSignUp)
        }
    }

    override suspend fun FragmentLoginBinding.setupEvent() {
    }

    override suspend fun FragmentLoginBinding.setupState() {
    }

}