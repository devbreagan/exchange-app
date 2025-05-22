package com.gbreagan.challenge.exchange.ui.view.option

import com.gbreagan.challenge.exchange.core.result.ResultData
import com.gbreagan.challenge.exchange.domain.model.CurrencyInfo
import com.gbreagan.challenge.exchange.domain.usecase.GetCurrenciesInfoUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceTimeBy
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
class OptionViewModelTest {

    private val testDispatcher = StandardTestDispatcher()

    private lateinit var viewModel: OptionViewModel
    private val getCurrenciesInfoUseCase: GetCurrenciesInfoUseCase = mockk()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = OptionViewModel(getCurrenciesInfoUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `loadOptions should update uiState with options on success`() = runTest {
        // Given
        val expectedOptions = listOf(CurrencyInfo("USD", "United States Dollar", 1.0))
        coEvery { getCurrenciesInfoUseCase() } returns ResultData.Success(expectedOptions)

        // When
        viewModel.loadOptions()
        advanceUntilIdle()

        // Then
        val state = viewModel.uiState.value
        assertEquals(false, state.isLoading)
        assertEquals(expectedOptions, state.options)
        assertNull(state.error)
    }

    @Test
    fun `loadOptions should update uiState with empty list on success`() = runTest {
        coEvery { getCurrenciesInfoUseCase() } returns ResultData.Success(emptyList())

        viewModel.loadOptions()
        advanceUntilIdle()

        val state = viewModel.uiState.value
        assertTrue(state.options.isEmpty())
        assertFalse(state.isLoading)
        assertNull(state.error)
    }

    @Test
    fun `loadOptions should update uiState with error message on failure`() = runTest {
        coEvery { getCurrenciesInfoUseCase() } returns ResultData.Failure(Exception("API failure"))

        viewModel.loadOptions()
        advanceUntilIdle()

        val state = viewModel.uiState.value
        assertEquals("Error al cargar datos", state.error)
        assertFalse(state.isLoading)
        assertTrue(state.options.isEmpty())
    }

    @Test
    fun `loadOptions should set loading state before getting result`() = runTest {
        // Given
        val expectedOptions = listOf(CurrencyInfo("USD", "United States Dollar", 1.0))
        coEvery { getCurrenciesInfoUseCase() } coAnswers {
            delay(100) // Simula carga
            ResultData.Success(expectedOptions)
        }

        // When
        val job = launch {
            viewModel.loadOptions()
        }

        // Comprobamos que al principio está cargando
        advanceTimeBy(50)

        val loadingState = viewModel.uiState.value
        assertTrue(loadingState.isLoading)

        advanceUntilIdle()
        job.join()

        // Then
        val finalState = viewModel.uiState.value
        assertFalse(finalState.isLoading)
        assertEquals(expectedOptions, finalState.options)
    }

    @Test
    fun `loadOptions should remain in loading state if ResultData is Loading`() = runTest {
        coEvery { getCurrenciesInfoUseCase() } returns ResultData.Loading

        viewModel.loadOptions()
        advanceUntilIdle()

        val state = viewModel.uiState.value
        assertTrue(state.isLoading)
        assertTrue(state.options.isEmpty())
        assertNull(state.error)
    }
}
