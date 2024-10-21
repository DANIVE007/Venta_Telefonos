// Test Unitario Busca que Validar la funcionalidad del método Clean()
package com.example.sprint6.viewModel

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.sprint6.Repository.ProductRepository
import com.example.sprint6.state.ProductState
import kotlinx.coroutines.ExperimentalCoroutinesApi

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock

@ExperimentalCoroutinesApi
class ProductViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: ProductViewModel
    private val repository = mock(ProductRepository::class.java)

    @Before
    fun setup() {
        viewModel = ProductViewModel(repository)
    }

    @Test
    fun `clean resets the state to initial values`() {
        // Asignamos un estado modificado al ViewModel
        viewModel.state = ProductState(
            nombre = "Producto de prueba",
            precio = 1000,
            imagen = "http://example.com/image.png",
            descripcion = "Descripción de prueba",
            precioAnterior = 1200,
            credito = true
        )

        // Llamamos a la función clean
        viewModel.clean()

        // Verificamos que el estado ha sido reseteado
        val expectedState = ProductState()
        assertEquals(expectedState, viewModel.state)
    }
}
