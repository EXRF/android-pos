package com.newposandroid.base

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

class SingleLiveEvent<T> : MutableLiveData<T>() {
    private val pending = mutableSetOf<LifecycleOwner>()

    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
        pending.add(owner)
        super.observe(owner) { value ->
            if (pending.contains(owner)) {
                pending.remove(owner)
                observer.onChanged(value)
            }
        }
    }

    fun call(value: T? = null) {
        postValue(value)
    }
}