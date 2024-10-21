package com.example.sprint6.di

import android.content.Context
import androidx.room.Room
import com.example.sprint6.datasource.DbDataSource
import com.example.sprint6.datasource.RestDataSource
import com.example.sprint6.model.ProductDao
import com.example.sprint6.util.Constants.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class DataSourceModule {

    @Singleton
    @Provides
    fun providesRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }

    @Singleton
    @Provides
    fun providesDataSource(retrofit: Retrofit): RestDataSource =
        retrofit.create(RestDataSource::class.java)

    @Singleton
    @Provides
    fun dbDataSource(@ApplicationContext context: Context): DbDataSource {
        return Room.databaseBuilder(
            context,
            DbDataSource::class.java,
            "Productos_database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun ProductDao(db: DbDataSource): ProductDao = db.productDao()
}