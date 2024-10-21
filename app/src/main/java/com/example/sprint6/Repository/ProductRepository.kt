package com.example.sprint6.Repository

import com.example.sprint6.datasource.RestDataSource
import com.example.sprint6.model.ProductDao
import com.example.sprint6.model.Producto
import com.example.sprint6.model.UserProduct
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


interface ProductRepository {
    suspend fun getProductosById(id: Int): UserProduct
    suspend fun getAllProductosAPI(): List<UserProduct>
    fun getAllProductosRoom(): Flow<List<Producto>>

}
class ProductRepositoryImp @Inject constructor(
    private val dataSource: RestDataSource,
    private val productDao: ProductDao
): ProductRepository {
    override suspend fun getProductosById(id: Int): UserProduct {
        val data = dataSource.getProductosById(id).body()!!
        val producto = UserProduct(
            data.id,
            data.name,
            data.price,
            data.image,
            data.description,
            data.lastPrice,
            data.credit

        )
        return producto


    }

    override suspend fun getAllProductosAPI(): List<UserProduct> {
        val data = dataSource.getProductos()
        data.forEach {
            val producto = Producto(
                it.id,
                it.name,
                it.price,
                it.image,
                it.description,
                it.lastPrice,
                it.credit
            )
            productDao.insert(producto)
        }
        return data
    }

    override fun getAllProductosRoom(): Flow<List<Producto>> = productDao.getAll()


}