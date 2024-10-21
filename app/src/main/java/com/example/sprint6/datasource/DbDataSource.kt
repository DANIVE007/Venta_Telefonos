package com.example.sprint6.datasource

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.sprint6.model.ProductDao
import com.example.sprint6.model.Producto


@Database(
    entities = [Producto::class],
    version = 1,

)
abstract class DbDataSource : RoomDatabase() {
    abstract fun productDao(): ProductDao
}

