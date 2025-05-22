package com.gbreagan.challenge.exchange.data.mapper

import com.gbreagan.challenge.exchange.data.datasource.local.entity.OperationEntity
import com.gbreagan.challenge.exchange.data.datasource.local.entity.SymbolsEntity
import com.gbreagan.challenge.exchange.data.datasource.remote.dto.ExchangeRateDto
import com.gbreagan.challenge.exchange.domain.model.Operation
import com.gbreagan.challenge.exchange.domain.model.CurrencyInfo

fun List<SymbolsEntity>.toCurrencyInfoList(ratesDto: ExchangeRateDto): List<CurrencyInfo> {
    return this.mapNotNull { symbol ->
        ratesDto.rates[symbol.code]?.let { rate ->
            CurrencyInfo(
                code = symbol.code,
                name = symbol.name,
                rate = rate
            )
        }
    }
}

fun Operation.toEntity() = OperationEntity(
    amountSend = amountSend,
    amountReceive = amountReceive,
    timestamp = timestamp,
    from = from,
    to = to
)

fun OperationEntity.toModel() = Operation(
    amountSend = amountSend,
    amountReceive = amountReceive,
    timestamp = timestamp,
    from = from,
    to = to
)