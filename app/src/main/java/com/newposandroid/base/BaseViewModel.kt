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
}

