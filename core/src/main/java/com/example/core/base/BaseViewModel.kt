package com.example.core.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.plus
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlin.coroutines.CoroutineContext
@ObsoleteCoroutinesApi
abstract class BaseViewModel<STATE, EVENT>(
    initialState: STATE,
    private val coroutineContext: CoroutineContext
) : ViewModel() {
    protected val mutableState = MutableStateFlow(initialState)
    val state: StateFlow<STATE> = mutableState

    protected val eventChannel = Channel<EVENT>(Channel.BUFFERED)
    val event = eventChannel.receiveAsFlow()

    protected val adaptiveScope: CoroutineScope
        get() = viewModelScope + coroutineContext

    protected suspend fun sendEvent(event: EVENT) {
        eventChannel.send(event)
    }

    protected suspend fun emitState(state: STATE) {
        mutableState.emit(state)
    }
}

@ObsoleteCoroutinesApi
abstract class MVViewModel<EVENT>(private val coroutineContext: CoroutineContext) : ViewModel() {
    private val eventChannel = Channel<EVENT>(Channel.BUFFERED)
    val event = eventChannel.receiveAsFlow()

    protected suspend fun sendEvent(event: EVENT) {
        eventChannel.send(event)
    }

    protected val adaptiveScope: CoroutineScope
        get() = viewModelScope + coroutineContext
}