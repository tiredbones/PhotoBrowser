package com.tiredbones.photobrowser.extensions

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.tiredbones.photobrowser.base.BaseViewModel

inline fun <reified T : BaseViewModel> FragmentActivity.getViewModel(viewModelFactory: ViewModelProvider.Factory): T =
    ViewModelProviders.of(this, viewModelFactory).get(T::class.java)

