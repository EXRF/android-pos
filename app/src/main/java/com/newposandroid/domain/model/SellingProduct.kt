package com.newposandroid.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SellingProduct(
    val name: String = "",
    val price: String = "",
    val percentage: Int = 0,
    val sales: Int = 0,
) : Parcelable