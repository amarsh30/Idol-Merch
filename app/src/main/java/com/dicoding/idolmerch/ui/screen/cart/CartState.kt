package com.dicoding.idolmerch.ui.screen.cart

import com.dicoding.idolmerch.model.OrderMerch

data class CartState(
    val orderMerch: List<OrderMerch>,
    val totalRequiredPrice: Int
)