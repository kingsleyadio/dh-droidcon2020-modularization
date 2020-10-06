package com.deliveryhero.workshop.dc2020.ui.rdp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider

class ViewModelFactory<T : ViewModel> @Inject constructor(private val viewModelProvider: Provider<T>) :
    ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return viewModelProvider.get() as T
    }
}