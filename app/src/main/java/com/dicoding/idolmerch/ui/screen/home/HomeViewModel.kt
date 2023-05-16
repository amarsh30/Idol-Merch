package com.dicoding.idolmerch.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.idolmerch.data.MerchRepository
import com.dicoding.idolmerch.model.OrderMerch
import com.dicoding.idolmerch.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: MerchRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<OrderMerch>>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<OrderMerch>>>
        get() = _uiState

    fun getAllMercers() {
        viewModelScope.launch {
            repository.getAllMercers()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { orderMercers ->
                    _uiState.value = UiState.Success(orderMercers)
                }
        }
    }
}