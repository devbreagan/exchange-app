package com.gbreagan.challenge.exchange.domain.usecase

import com.gbreagan.challenge.exchange.domain.model.CurrencyInfo

class ConvertCurrencyUseCase {
    operator fun invoke(from: String, to: String, amount: Double, rates: List<CurrencyInfo>): Double?{
        return calculateExchangeRate(from, to, amount, rates)
    }

    private fun calculateExchangeRate(from: String, to: String, amount: Double, rates: List<CurrencyInfo>): Double? {
        val fromRate = rates.find { it.code == from }?.rate
        val toRate = rates.find { it.code == to }?.rate
        return if (fromRate != null && toRate != null) (toRate / fromRate) * amount else null
    }
}