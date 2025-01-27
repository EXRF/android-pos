package com.newposandroid.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.plus
import kotlin.coroutines.CoroutineContext

@ObsoleteCoroutinesApi
abstract class BaseViewModel(
    private val coroutineContext: CoroutineContext
): ViewModel()  {
    protected val adaptiveScope: CoroutineScope
        get() = viewModelScope + coroutineContext

    // SingleLiveEvents for common UI interactions
    val navigationEvent = SingleLiveEvent<String>()
    val toastEvent = SingleLiveEvent<String>()
    val otherEvent = SingleLiveEvent<Any>() // Add other events as needed

    // Helper methods for triggering events
    fun navigate(destination: String) {
        navigationEvent.call(destination)
    }

    fun showToast(message: String) {
        toastEvent.call(message)
    }

    fun triggerOtherEvent(data: Any) {
        otherEvent.call(data)
    }
}

