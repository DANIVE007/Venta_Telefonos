package com.example.sprint6.model

data class ApiResponse(
    val products: List<UserProduct>

)

data class UserProduct(
    val id: Int,
    val name: String,
    val price: Int,
    val image: String,
    val description: String,
    val lastPrice: Int,
    val credit: Boolean

)