package com.gbreagan.challenge.exchange.ui.view.home

import com.gbreagan.challenge.exchange.core.result.ResultData
import com.gbreagan.challenge.exchange.data.toTwoDecimalString
import com.gbreagan.challenge.exchange.domain.model.CurrencyInfo
import com.gbreagan.challenge.exchange.domain.usecase.ConvertCurrencyUseCase
import com.gbreagan.challenge.exchange.domain.usecase.GetCurrenciesInfoUseCase
import com.gbreagan.challenge.exchange.domain.usecase.SaveOperationUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class HomeViewModelTest {

    private val testDispatcher = StandardTestDispatcher()

    private val convertCurrencyUseCase: ConvertCurrencyUseCase = mockk()
    private val getCurrenciesInfoUseCase: GetCurrenciesInfoUseCase = mockk()
    private val saveOperationUseCase: SaveOperationUseCase = mockk(relaxed = true)

    private lateinit var viewModel: HomeViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        viewModel =
            HomeViewModel(convertCurrencyUseCase, getCurrenciesInfoUseCase, saveOperationUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `onAmountSendChange updates amountToSend if valid decimal`() {
        val validAmount = "123.45"
        viewModel.onAmountSendChange(validAmount)
        assertEquals(validAmount, viewModel.uiState.value.amountToSend)
    }

    @Test
    fun `onAmountSendChange ignores invalid decimal`() {
        val invalidAmount = "12.34.56"
        viewModel.onAmountSendChange(invalidAmount)
        assertEquals("", viewModel.uiState.value.amountToSend)  // empty
    }

    @Test
    fun `onAmountReceiveChange updates amountToReceive if valid decimal`() {
        val validAmount = "99.99"
        viewModel.onAmountReceiveChange(validAmount)
        assertEquals(validAmount, viewModel.uiState.value.amountToReceive)
    }

    @Test
    fun `onAmountReceiveChange ignores invalid decimal`() {
        val invalidAmount = "abc"
        viewModel.onAmountReceiveChange(invalidAmount)
        assertEquals("", viewModel.uiState.value.amountToReceive)  // empty
    }

    @Test
    fun `convertCurrency updates uiState with error on failure`() = runTest {
        val errorMsg = "Network error"
        coEvery { getCurrenciesInfoUseCase() } returns ResultData.Failure(Exception(errorMsg))

        viewModel.convertCurrency("USD", "EUR")
        advanceUntilIdle()

        val state = viewModel.uiState.value
        assertEquals(errorMsg, state.error)
        assertFalse(state.isLoading)
    }

    @Test
    fun `convertCurrency sets loading true on loading result`() = runTest {
        coEvery { getCurrenciesInfoUseCase() } returns ResultData.Loading

        viewModel.convertCurrency("USD", "EUR")
        advanceUntilIdle()

        val state = viewModel.uiState.value
        assertTrue(state.isLoading)
    }

    @Test
    fun `convertCurrency updates amountToReceive and calls saveOperation on success`() = runTest {
        val rates = mockk<List<CurrencyInfo>>() // mock
        val amountToSend = "100.0"
        val convertedValue = 120.0

        // Given
        coEvery { getCurrenciesInfoUseCase() } returns ResultData.Success(rates)
        coEvery {
            convertCurrencyUseCase(
                "USD",
                "EUR",
                amountToSend.toDouble(),
                rates
            )
        } returns convertedValue
        coEvery {
            saveOperationUseCase(
                any(),
                any(),
                any(),
                any()
            )
        } returns ResultData.Success(true)

        // When
        viewModel.onAmountSendChange(amountToSend)

        viewModel.convertCurrency("USD", "EUR")
        advanceUntilIdle()

        val state = viewModel.uiState.value

        // Then
        assertEquals(convertedValue.toTwoDecimalString(), state.amountToReceive)
        assertFalse(state.isLoading)
        assertNull(state.error)

        coVerify(exactly = 1) { // convert success
            saveOperationUseCase(
                amountSend = amountToSend.toDouble(),
                amountReceive = convertedValue,
                from = "USD",
                to = "EUR"
            )
        }
    }
}
