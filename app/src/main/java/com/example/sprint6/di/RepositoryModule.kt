package com.example.sprint6.di

import com.example.sprint6.Repository.ProductRepository
import com.example.sprint6.Repository.ProductRepositoryImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun procutoRepository(productoRepositoryImp: ProductRepositoryImp): ProductRepository

}