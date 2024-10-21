package com.example.sprint6.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import java.text.NumberFormat
import java.util.Locale

@Composable
fun CardProducto(
    nombre:String,
    precio:Int,
    imagen:String,
    descripcion:String,
    precioAnterior:Int,
    credito:Boolean,
    onClick: () -> Unit
){

    val formatoMoneda = NumberFormat.getNumberInstance(Locale("es", "CL"))
    val precioFormateado = formatoMoneda.format(precio)
    val precioAnteriorFormateado = formatoMoneda.format(precioAnterior)
    val CreditoText = if (credito == true) {
        "Acepta  Tarjeta de Credito"
    } else {
        "Solo Efectivo"
    }
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
            .clickable { onClick() }
            .shadow(5.dp),
        shape = RoundedCornerShape(5.dp),
        elevation = CardDefaults.cardElevation(11.dp)
    ){
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
        ){
            Column {
                ImageProducto(imagen)
            }
            Column (
                modifier = Modifier
                    .padding(start = 15.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween
            ){
                Text(text = nombre, fontWeight = FontWeight.Bold)
                Text(text = "$ $precioFormateado".toString(), fontWeight = FontWeight.Bold)



            }
        }
    }

}
@Composable
fun ImageProducto(imagen:String){
    val image = rememberAsyncImagePainter(model = imagen)

    Image(
        painter = image,
        contentDescription = "",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(90.dp)
    )
}