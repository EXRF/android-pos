package com.newposandroid.presentation.auth.login

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.newposandroid.R
import com.newposandroid.base.BaseFragment
import com.newposandroid.databinding.FragmentLoginBinding
import com.newposandroid.presentation.HomeActivity
import com.newposandroid.utils.navigateAndClear

class LoginFragment : BaseFragment<FragmentLoginBinding>() {
    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentLoginBinding = FragmentLoginBinding.inflate(inflater, container, false)

    override suspend fun FragmentLoginBinding.observer() {

    }

    override fun FragmentLoginBinding.initBinding() {
        renderContent()
    }

    private fun FragmentLoginBinding.renderContent() {
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

        btnLogin.btn.setOnClickListener {
            context?.navigateAndClear(HomeActivity::class.java)
        }
    }
}