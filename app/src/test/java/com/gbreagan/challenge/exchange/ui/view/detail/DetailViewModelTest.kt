package com.gbreagan.challenge.exchange.ui.view.detail

import com.gbreagan.challenge.exchange.core.result.ResultData
import com.gbreagan.challenge.exchange.domain.model.Operation
import com.gbreagan.challenge.exchange.domain.usecase.GetOperationsUseCase
import io.mockk.coEvery
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
class DetailViewModelTest {

    private val testDispatcher = StandardTestDispatcher()

    private lateinit var viewModel: DetailViewModel
    private val getOperationsUseCase: GetOperationsUseCase = mockk()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = DetailViewModel(getOperationsUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `loadOperations should update uiState with operations on success`() = runTest {
        val expectedOperations = listOf(
            Operation(100.0, 95.0, "USD", "EUR", 1747892942)
        )

        coEvery { getOperationsUseCase() } returns ResultData.Success(expectedOperations)

        viewModel.loadOperations()
        advanceUntilIdle()

        val state = viewModel.uiState.value
        assertFalse(state.isLoading)
        assertEquals(expectedOperations, state.operations)
        assertNull(state.error)
    }

    @Test
    fun `loadOperations should update uiState with error message on failure`() = runTest {
        coEvery { getOperationsUseCase() } returns ResultData.Failure(Exception("Error"))

        viewModel.loadOperations()
        advanceUntilIdle()

        val state = viewModel.uiState.value
        assertFalse(state.isLoading)
        assertEquals("Error al cargar datos", state.error)
        assertTrue(state.operations.isEmpty())
    }

    @Test
    fun `loadOperations should set loading state before result`() = runTest {
        coEvery { getOperationsUseCase() } returns ResultData.Loading

        viewModel.loadOperations()
        advanceUntilIdle()

        val state = viewModel.uiState.value
        assertTrue(state.isLoading)
        assertTrue(state.operations.isEmpty())
        assertNull(state.error)
    }
}
