package com.example.sprint6.viewModel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sprint6.model.Producto
import com.example.sprint6.Repository.ProductRepository
import com.example.sprint6.state.ProductState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class ProductViewModel @Inject constructor(
    private val repo: ProductRepository
): ViewModel() {

    var state: ProductState by mutableStateOf(ProductState())

    val productos: Flow<List<Producto>> by lazy {
        repo.getAllProductosRoom()
    }
    fun getAllAPI() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                repo.getAllProductosAPI()
                Log.d("PRODUCTOS", repo.getAllProductosAPI().toString())
            }
        }
    }
    fun getProductoById(id: Int){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                val result = repo.getProductosById(id)
                state = state.copy(
                    nombre = result.name,
                    precio = result.price,
                    imagen = result.image,
                    descripcion = result.description,
                    precioAnterior = result.lastPrice,
                    credito = result.credit
                )
            }
        }
    }
    fun clean(){
        state = state.copy(
            nombre = "",
            precio = 0,
            imagen = "",
            descripcion = "",
            precioAnterior = 0,
            credito = false
        )
    }
}