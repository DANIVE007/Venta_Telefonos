// Test Instumental que evalua, comprueba y validar la persistencia de datos en la base de datos Room
// DbDataSourceTest.kt
package com.example.sprint6.datasource

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.core.app.ApplicationProvider
import androidx.room.Room
import com.example.sprint6.model.ProductDao
import com.example.sprint6.model.Producto
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DbDataSourceTest {

    private lateinit var db: DbDataSource
    private lateinit var dao: ProductDao

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<android.content.Context>()
        db = Room.inMemoryDatabaseBuilder(context, DbDataSource::class.java)
            .allowMainThreadQueries()
            .build()
        dao = db.productDao()
    }

    @After
    fun closeDb() {
        db.close()
    }

    @Test
    fun writeProductAndReadInList() = runBlocking {
        val producto = Producto(
            id = 1,
            name = "Producto Test",
            price = 1000,
            image = "http://example.com/image.png",
            description = "Descripción de prueba",
            lastPrice = 1200,
            credit = true
        )
        dao.insert(producto)
        val productos = dao.getAll().first()
        assertEquals(1, productos.size)
        assertEquals(producto, productos[0])
    }
}
