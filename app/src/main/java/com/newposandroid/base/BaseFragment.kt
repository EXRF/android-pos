package com.newposandroid.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import kotlinx.coroutines.launch

//abstract class BaseFragment<T: ViewBinding> : Fragment() {
//    private var _viewBinding: T? = null
//    val viewBinding: T
//        get() = _viewBinding!!
//
//    // Don't forget to clear garbage collection
//    override fun onDestroyView() {
//        super.onDestroyView()
//        _viewBinding = null
//    }
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        _viewBinding = getViewBinding(inflater, container)
//        return _viewBinding?.root
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        with(viewBinding) {
//            initBinding()
//            viewLifecycleOwner.lifecycleScope.launch {
//                observer()
//            }
//        }
//    }
//
//    // Find view binding
//    protected abstract fun getViewBinding(inflater: LayoutInflater, container: ViewGroup?): T
//
//    // Init binding like setText, setImage, setAdapter, setOnClick
//    protected abstract fun T.initBinding()
//
//    // Handling state Success, Error, Loading
//    protected abstract suspend fun T.observer()
//}

abstract class BaseFragment<T: ViewBinding> : Fragment() {
    private var _viewBinding: T? = null
    val viewBinding: T
        get() = _viewBinding!!

    // Don't forget to clear garbage collection
    override fun onDestroyView() {
        super.onDestroyView()
        _viewBinding = null
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _viewBinding = getViewBinding(inflater, container)
        return _viewBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(viewBinding) {
            initBinding()
            viewLifecycleOwner.lifecycleScope.launchWhenCreated {
                setupState()
                setupEvent()
            }
        }
    }

    // Find view binding
    protected abstract fun getViewBinding(inflater: LayoutInflater, container: ViewGroup?): T

    // Init binding like setText, setImage, setAdapter, setOnClick
    protected abstract fun T.initBinding()

    // Handling state like Idle, Loading, Empty, Success
    protected abstract suspend fun T.setupState()
    // Handling one time call event like showToast, showSnackBar, navigate to other screen
    protected abstract suspend fun T.setupEvent()
}