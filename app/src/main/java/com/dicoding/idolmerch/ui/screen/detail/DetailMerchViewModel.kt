package com.dicoding.idolmerch.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.idolmerch.data.MerchRepository
import com.dicoding.idolmerch.model.Merch
import com.dicoding.idolmerch.model.OrderMerch
import com.dicoding.idolmerch.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailMerchViewModel(
    private val repository: MerchRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<OrderMerch>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<OrderMerch>>
        get() = _uiState

    fun getMerchById(merchId: Long) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = UiState.Success(repository.getOrderMerchById(merchId))
        }
    }

    fun addToCart(merch: Merch, count: Int) {
        viewModelScope.launch {
            repository.updateOrderMerch(merch.id, count)
        }
    }
}