package com.example.sprint6.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(producto: Producto)

    @Query("SELECT * FROM productos ORDER BY id")
    fun getAll(): Flow<List<Producto>>

}