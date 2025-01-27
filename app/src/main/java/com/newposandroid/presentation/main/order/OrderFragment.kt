package com.newposandroid.presentation.main.order

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.newposandroid.base.BaseFragment
import com.newposandroid.databinding.FragmentOrderBinding
import com.newposandroid.utils.convertRupiah

class OrderFragment : BaseFragment<FragmentOrderBinding>() {
    private var amount: String = ""

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentOrderBinding = FragmentOrderBinding.inflate(inflater, container, false)

    override suspend fun FragmentOrderBinding.observer() {

    }

    override fun FragmentOrderBinding.initBinding() {
        renderContent()
    }

    private fun FragmentOrderBinding.renderContent() {
        updateAmount("0")

        with(tvAmount) {
            btnClear.setOnClickListener {
                amount = ""
                text = "0".toInt().convertRupiah()
            }

            btnDelete.setOnClickListener {
                try {
                    if (amount.isNotEmpty()) {
                        amount = amount.dropLast(1)
                    }
                    text = if (amount.isEmpty()) "0".toInt().convertRupiah() else amount.toInt()
                        .convertRupiah()
                } catch (e: NumberFormatException) {
                    amount = ""
                    text = "0".toInt().convertRupiah()
                }
            }

            btnEnter.setOnClickListener {
                text = if (amount.isEmpty()) "0".toInt().convertRupiah() else amount.toInt()
                    .convertRupiah()
            }

            btn1.setOnClickListener {
                updateAmount("1")
            }

            btn2.setOnClickListener {
                updateAmount("2")
            }

            btn3.setOnClickListener {
                updateAmount("3")
            }

            btn4.setOnClickListener {
                updateAmount("4")
            }

            btn5.setOnClickListener {
                updateAmount("5")
            }

            btn6.setOnClickListener {
                updateAmount("6")
            }

            btn7.setOnClickListener {
                updateAmount("7")
            }

            btn8.setOnClickListener {
                updateAmount("8")
            }

            btn9.setOnClickListener {
                updateAmount("9")
            }

            btn0.setOnClickListener {
                updateAmount("0")
            }

            btn000.setOnClickListener {
                updateAmount("000")
            }
        }
    }

    private fun FragmentOrderBinding.updateAmount(value: String) {
        // validate length of amount. cannot append if length more than 10
        if (amount.length >= 10) return

        try {
            amount += value
            val numericAmount = amount.toBigInteger() // Use BigInteger for large numbers
            tvAmount.text = numericAmount.convertRupiah()
        } catch (e: NumberFormatException) {
            tvAmount.text = "0".toInt().convertRupiah()
            amount = "" // Reset `amount` to prevent further errors
        }
    }
}