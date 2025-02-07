package com.newposandroid.presentation.main.home

import android.util.Log
import com.newposandroid.base.BaseViewModel
import com.newposandroid.domain.model.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

//@OptIn(ObsoleteCoroutinesApi::class)
//@HiltViewModel
//class HomeViewModel @Inject constructor(
//    coroutineContext: CoroutineContext,
//) : BaseViewModel(coroutineContext) {
//    private val _listProduct = MutableLiveData<Resource<MutableList<Product>>>()
//    val listProduct: LiveData<Resource<MutableList<Product>>>
//        get() = _listProduct
//
//    val products: MutableList<Product> = mutableListOf(
//        Product(
//            name = "Nissan GTR R35",
//            desc = "The Legendary R35",
//            price = 1000f,
//            image = "https://autonetmagz.com/wp-content/uploads/2016/03/nissan-gt-r-2017.jpg"
//        ),
//        Product(
//            name = "Mercedes AMG G36",
//            desc = "The Legendary G36",
//            price = 2000f,
//            image = "https://images.squarespace-cdn.com/content/v1/5fbad9a57c058206994f27d7/1628332328915-7U7L7FFTV2YNR2JK2LBQ/01.jpg?format=2500w"
//        ),
//        Product(
//            name = "Lexus LFA",
//            desc = "LFA Nurburgring Edition",
//            price = 4000f,
//            image = "https://img.inews.co.id/media/1050/files/inews_new/2021/08/25/25___Lexus_1.jpg"
//        ),
//        Product(
//            name = "Land Rover Defender",
//            desc = "Legacy Defender",
//            price = 1000f,
//            image = "https://media.cdn-jaguarlandrover.com/api/v2/images/105393/w/1600.jpg"
//        )
//    )
//
//    init {
//        _listProduct.value = Resource.Success(products)
//    }
//
//    fun addProduct(products: MutableList<Product>?) {
//        _listProduct.value?.data?.addAll(products ?: mutableListOf())
//        _listProduct.value = Resource.Success(_listProduct.value?.data ?: mutableListOf())
//    }
//}

sealed class HomeState {
    object Idle : HomeState()

    object Loading : HomeState()

    data class Success(val products: MutableList<Product>) : HomeState()
}

sealed class HomeEvent {
    object Error : HomeEvent()
}

@ObsoleteCoroutinesApi
@HiltViewModel
class HomeViewModel @Inject constructor(
    coroutineContext: CoroutineContext
) : BaseViewModel<HomeState, HomeEvent>(HomeState.Idle, coroutineContext) {
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

    suspend fun init() {
        mutableState.emit(HomeState.Success(products))
    }

    fun addProduct(products: MutableList<Product>) {
        adaptiveScope.launch {
            val listProducts: MutableList<Product> =
                if (mutableState.value is HomeState.Success) (mutableState.value as HomeState.Success).products else mutableListOf()
            mutableState.emit(HomeState.Loading)
            listProducts.addAll(products)
            Log.d("suuu", "${listProducts.size}")
            mutableState.emit(HomeState.Success(listProducts))
        }
    }
}