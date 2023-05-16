package com.dicoding.idolmerch.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dicoding.idolmerch.data.MerchRepository
import com.dicoding.idolmerch.ui.screen.cart.CartViewModel
import com.dicoding.idolmerch.ui.screen.detail.DetailMerchViewModel
import com.dicoding.idolmerch.ui.screen.home.HomeViewModel

class ViewModelFactory(private val repository: MerchRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(DetailMerchViewModel::class.java)) {
            return DetailMerchViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(CartViewModel::class.java)) {
            return CartViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}