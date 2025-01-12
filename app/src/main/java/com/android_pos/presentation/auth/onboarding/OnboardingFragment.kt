package com.android_pos.presentation.auth.onboarding

import android.view.LayoutInflater
import android.view.ViewGroup
import com.android_pos.base.BaseFragment
import com.android_pos.databinding.FragmentOnboardingBinding

class OnboardingFragment : BaseFragment<FragmentOnboardingBinding>() {
    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentOnboardingBinding {
        return FragmentOnboardingBinding.inflate(inflater, container, false)
    }

    override fun FragmentOnboardingBinding.initBinding() {
        println("muehehe")
    }

    override suspend fun FragmentOnboardingBinding.setupEvent() {
    }

    override suspend fun FragmentOnboardingBinding.setupState() {
    }
}