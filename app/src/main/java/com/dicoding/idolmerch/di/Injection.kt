package com.dicoding.idolmerch.di

import com.dicoding.idolmerch.data.MerchRepository

object Injection {
    fun provideRepository(): MerchRepository {
        return MerchRepository.getInstance()
    }
}