package com.android_pos.presentation.auth.onboarding

import android.view.LayoutInflater
import android.view.ViewGroup
import com.android_pos.R
import com.android_pos.base.BaseFragment
import androidx.navigation.fragment.findNavController
import com.android_pos.databinding.FragmentOnboardingBinding

class OnboardingFragment : BaseFragment<FragmentOnboardingBinding>() {
    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentOnboardingBinding {
        return FragmentOnboardingBinding.inflate(inflater, container, false)
    }

    override fun FragmentOnboardingBinding.initBinding() {
        btnNewAccount.tvTitle.text = "Create new account"

        btnLogin.btn.setOnClickListener {
            findNavController().navigate(R.id.fragmentLogin)
        }
    }

    override suspend fun FragmentOnboardingBinding.setupEvent() {
    }

    override suspend fun FragmentOnboardingBinding.setupState() {
    }
}