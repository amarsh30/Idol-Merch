package com.dicoding.idolmerch.ui.screen.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.idolmerch.data.MerchRepository
import com.dicoding.idolmerch.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CartViewModel(
    private val repository: MerchRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<CartState>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<CartState>>
        get() = _uiState

    fun getAddedOrderMercers() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            repository.getAddedOrderMercers()
                .collect { orderMerch ->
                    val totalRequiredPrice =
                        orderMerch.sumOf { it.merch.requiredPrice * it.count }
                    _uiState.value = UiState.Success(CartState(orderMerch, totalRequiredPrice))
                }
        }
    }

    fun updateOrderMerch(merchId: Long, count: Int) {
        viewModelScope.launch {
            repository.updateOrderMerch(merchId, count)
                .collect { isUpdated ->
                    if (isUpdated) {
                        getAddedOrderMercers()
                    }
                }
        }
    }
}