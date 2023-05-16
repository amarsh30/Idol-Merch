package com.dicoding.idolmerch.data

import com.dicoding.idolmerch.model.FakeMerchDataSource
import com.dicoding.idolmerch.model.OrderMerch
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

class MerchRepository {
    private val orderMercers = mutableListOf<OrderMerch>()

    init {
        if (orderMercers.isEmpty()) {
            FakeMerchDataSource.dummyMercers.forEach {
                orderMercers.add(OrderMerch(it, 0))
            }
        }
    }

    fun getAllMercers(): Flow<List<OrderMerch>> {
        return flowOf(orderMercers)
    }

    fun getOrderMerchById(merchId: Long): OrderMerch {
        return orderMercers.first {
            it.merch.id == merchId
        }
    }

    fun updateOrderMerch(merchId: Long, newCountValue: Int): Flow<Boolean> {
        val index = orderMercers.indexOfFirst { it.merch.id == merchId }
        val result = if (index >= 0) {
            val orderMerch = orderMercers[index]
            orderMercers[index] =
                orderMerch.copy(merch = orderMerch.merch, count = newCountValue)
            true
        } else {
            false
        }
        return flowOf(result)
    }

    fun getAddedOrderMercers(): Flow<List<OrderMerch>> {
        return getAllMercers()
            .map { orderMercers ->
                orderMercers.filter { orderMerch ->
                    orderMerch.count != 0
                }
            }
    }

    companion object {
        @Volatile
        private var instance: MerchRepository? = null

        fun getInstance(): MerchRepository =
            instance ?: synchronized(this) {
                MerchRepository().apply {
                    instance = this
                }
            }
    }
}