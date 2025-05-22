package com.gbreagan.challenge.exchange.domain.usecase

import com.gbreagan.challenge.exchange.domain.model.CurrencyInfo
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class ConvertCurrencyUseCaseTest {

    private val useCase = ConvertCurrencyUseCase()

    private val rates = listOf(
        CurrencyInfo("USD", "United States Dollar", 1.1311),
        CurrencyInfo("EUR", "Euro", 1.0), //0.9
        CurrencyInfo("JPY", "Japanese Yen", 110.0),
        CurrencyInfo("PEN", "Peruvian Sol", 4.1803)
    )

    @Test
    fun `convert currency returns correct value`() {
        val result = useCase.invoke("USD", "PEN", 100.0, rates)
        assertEquals(369.58, result)
        // result: (to / from) * 100 = -
        // result: (USD / PEN) * 100 = -
        // result: (1.1311 / 4.1003) * 100 = 369.57828662
    }

    @Test
    fun `returns null when from currency not found`() {
        val result = useCase.invoke("ABC", "EUR", 100.0, rates)
        assertNull(result)
    }

    @Test
    fun `returns null when to currency not found`() {
        val result = useCase.invoke("USD", "XYZ", 100.0, rates)
        assertNull(result)
    }

    @Test
    fun `returns null when rates list is empty`() {
        val result = useCase.invoke("USD", "EUR", 100.0, emptyList())
        assertNull(result)
    }

    @Test
    fun `correctly converts zero amount`() {
        val result = useCase.invoke("USD", "EUR", 0.0, rates)
        assertEquals(0.0, result)
    }
}
