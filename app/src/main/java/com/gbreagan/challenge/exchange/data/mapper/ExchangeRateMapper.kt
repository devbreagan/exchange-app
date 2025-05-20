package com.gbreagan.challenge.exchange.data.mapper

import com.gbreagan.challenge.exchange.data.datasource.remote.dto.ExchangeRateDto
import com.gbreagan.challenge.exchange.domain.model.CurrencyInfo

//fun ExchangeRateDto.toDomainModel(): List<CurrencyInfo> {
//    return rates.map {
//        CurrencyInfo(
//            it.key,
//            it.value
//        )
//    }
//}