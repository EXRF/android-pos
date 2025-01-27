package com.newposandroid.presentation.main.cashier

import android.view.LayoutInflater
import android.view.ViewGroup
import com.newposandroid.base.BaseFragment
import com.newposandroid.databinding.FragmentCashierBinding

class CashierFragment : BaseFragment<FragmentCashierBinding>() {
    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentCashierBinding = FragmentCashierBinding.inflate(inflater, container, false)

    override suspend fun FragmentCashierBinding.observer() {

    }

    override fun FragmentCashierBinding.initBinding() {
        renderContent()
    }

    private fun FragmentCashierBinding.renderContent() {

    }
}