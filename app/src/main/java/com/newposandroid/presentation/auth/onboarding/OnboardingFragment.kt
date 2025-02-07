package com.newposandroid.presentation.auth.onboarding

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.newposandroid.R
import com.newposandroid.base.BaseFragment
import com.newposandroid.databinding.FragmentOnboardingBinding

class OnboardingFragment : BaseFragment<FragmentOnboardingBinding>() {
    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentOnboardingBinding = FragmentOnboardingBinding.inflate(inflater, container, false)

    override suspend fun FragmentOnboardingBinding.setupEvent() {
    }

    override suspend fun FragmentOnboardingBinding.setupState() {

    }

    override fun FragmentOnboardingBinding.initBinding() {
        renderContent()
    }

    private fun FragmentOnboardingBinding.renderContent() {
        btnNewAccount.tvTitle.text = getString(R.string.create_new_account)

        btnLogin.btn.setOnClickListener {
            findNavController().navigate(R.id.fragmentLogin)
        }

        btnNewAccount.btn.setOnClickListener {
            findNavController().navigate(R.id.fragmentSignUp)
        }
    }
}