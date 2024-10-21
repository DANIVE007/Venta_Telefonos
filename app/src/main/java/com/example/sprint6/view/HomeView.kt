package com.example.sprint6.view

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.sprint6.component.CardProducto
import com.example.sprint6.viewModel.ProductViewModel



@Composable
fun HomeView(
    viewModel: ProductViewModel,
    navController: NavController
){
    LaunchedEffect(Unit) {
        viewModel.getAllAPI()
    }
    Scaffold (
        modifier = Modifier
            .fillMaxSize()
    ){ innerPadding ->
        val productos by viewModel.productos.collectAsState(listOf())
        LazyColumn(
            modifier = Modifier
               .padding(innerPadding)
        ) {
            items(productos) {item ->
                CardProducto(
                    item.name,
                    item.price,
                    item.image,
                    item.description,
                    item.lastPrice,
                    item.credit,



                ){
                    navController.navigate("Detail/${item.id}")
                }
            }

        }

    }
}