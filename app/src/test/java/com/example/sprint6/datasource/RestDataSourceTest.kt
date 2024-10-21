// Test Unitario que verifica la respuesta de los endpoints utilizando MockWebServer y Retrofit.
package com.example.sprint6.datasource

import com.example.sprint6.model.UserProduct
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RestDataSourceTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var restDataSource: RestDataSource

    @Before
    fun setup() {
        mockWebServer = MockWebServer()
        mockWebServer.start()

        restDataSource = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/")) // Usamos la URL del MockWebServer
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RestDataSource::class.java)
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `getProductos devuelve lista de UserProduct`() = runBlocking {
        // Preparar la respuesta simulada
        val mockResponse = MockResponse()
            .setResponseCode(200)
            .setBody("""
                [
                    {
                        "id": 1,
                        "name": "Producto 1",
                        "price": 1000,
                        "image": "http://example.com/image1.png",
                        "description": "Descripción del producto 1",
                        "lastPrice": 1200,
                        "credit": true
                    },
                    {
                        "id": 2,
                        "name": "Producto 2",
                        "price": 2000,
                        "image": "http://example.com/image2.png",
                        "description": "Descripción del producto 2",
                        "lastPrice": 2200,
                        "credit": false
                    }
                ]
            """.trimIndent())
        mockWebServer.enqueue(mockResponse)

        // Llamar a la función que queremos probar
        val productos: List<UserProduct> = restDataSource.getProductos()

        // Verificar los resultados
        assertThat(productos).isNotNull()
        assertThat(productos.size).isEqualTo(2)
        assertThat(productos[0].name).isEqualTo("Producto 1")
        assertThat(productos[1].price).isEqualTo(2000)
    }
}
