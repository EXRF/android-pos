package com.newposandroid.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product(
    val name: String = "",
    val desc: String = "",
    val image: String = "",
    val price: Float = 0.0f,
) : Parcelable