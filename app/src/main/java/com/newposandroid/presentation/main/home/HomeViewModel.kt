package com.newposandroid.presentation.main.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.newposandroid.base.BaseViewModel
import com.newposandroid.domain.model.Product
import com.newposandroid.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.plus
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@OptIn(ObsoleteCoroutinesApi::class)
@HiltViewModel
class HomeViewModel @Inject constructor(
    coroutineContext: CoroutineContext,
) : BaseViewModel(coroutineContext) {
    private val _listProduct = MutableLiveData<Resource<MutableList<Product>>>()
    val listProduct: LiveData<Resource<MutableList<Product>>>
        get() = _listProduct

    val products: MutableList<Product> = mutableListOf(
        Product(
            name = "Nissan GTR R35",
            desc = "The Legendary R35",
            price = 1000f,
            image = "https://autonetmagz.com/wp-content/uploads/2016/03/nissan-gt-r-2017.jpg"
        ),
        Product(
            name = "Mercedes AMG G36",
            desc = "The Legendary G36",
            price = 2000f,
            image = "https://images.squarespace-cdn.com/content/v1/5fbad9a57c058206994f27d7/1628332328915-7U7L7FFTV2YNR2JK2LBQ/01.jpg?format=2500w"
        ),
        Product(
            name = "Lexus LFA",
            desc = "LFA Nurburgring Edition",
            price = 4000f,
            image = "https://img.inews.co.id/media/1050/files/inews_new/2021/08/25/25___Lexus_1.jpg"
        ),
        Product(
            name = "Land Rover Defender",
            desc = "Legacy Defender",
            price = 1000f,
            image = "https://media.cdn-jaguarlandrover.com/api/v2/images/105393/w/1600.jpg"
        )
    )

    init {
        _listProduct.value = Resource.Success(products)
    }

    fun addProduct(products: MutableList<Product>?) {
        _listProduct.value?.data?.addAll(products ?: mutableListOf())
        _listProduct.value = Resource.Success(_listProduct.value?.data ?: mutableListOf())
    }
}