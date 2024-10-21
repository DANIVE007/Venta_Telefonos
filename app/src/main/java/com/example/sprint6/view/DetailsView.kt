package com.example.sprint6.view

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.sprint6.component.TopBarComponent
import com.example.sprint6.viewModel.ProductViewModel
import java.text.NumberFormat
import java.util.Locale

@Composable
fun DetailView(
    viewModel: ProductViewModel,
    navController: NavController,
    id:Int
){

    LaunchedEffect(Unit) {
        viewModel.getProductoById(id)
    }
    DisposableEffect(Unit) {
        onDispose {
            viewModel.clean()
        }
    }

    Scaffold (
        topBar = {
            TopBarComponent(
                Titulo = "Equipo ${viewModel.state.nombre}",
                mostrarBotton = true,
                { navController.popBackStack() }
            )
        }
    ){
        ContentDetailView(it, viewModel,id)
    }
}
@Composable
fun ContentDetailView(
    paddingValues: PaddingValues,
    viewModel: ProductViewModel,
    id:Int
) {
    val codigo = id.toString()
    val state = viewModel.state
    val context = LocalContext.current
    val image = rememberAsyncImagePainter(model = state.imagen)
    val nombreEquipo = state.nombre.replace(oldValue = "", newValue = "_")
    var email = "info@novaera.cl"
    var asunto = "Formulario de Contacto Equipo {$nombreEquipo} id {$codigo}"
    var mensaje = "Hola\n" +
    "Vi el telefono {$nombreEquipo} de código {$codigo} y me gustaría que me contactaran a este correo o al siguiente número _________" +
            "Quedo atento.\n\n Gracias.”"

    // Formatear los precios usando NumberFormat con el locale adecuado
    val formatoMoneda = NumberFormat.getNumberInstance(Locale("es", "CL")) // Formato chileno
    val precioFormateado = formatoMoneda.format(state.precio)
    val precioAnteriorFormateado = formatoMoneda.format(state.precioAnterior)


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ){
        Image(
            painter = image,
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(350.dp)
        )
        Column(
            modifier = Modifier
                .padding(20.dp)
        ){

            Column(
                modifier = Modifier.weight(1f)
            ){
                Text(text= "Descripción: ${state.descripcion}", fontWeight = FontWeight.Bold)
                HorizontalDivider()
                Text(text= "Precio Anterior: $ $precioAnteriorFormateado", fontWeight = FontWeight.Bold)
                HorizontalDivider()
                Text(text= "Utimo Precio: $ $precioFormateado", fontWeight = FontWeight.Bold)
                HorizontalDivider()

            }
            Button(
                onClick = {
                    val intent = Intent(Intent.ACTION_SEND)
                    intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(email))
                    intent.putExtra(Intent.EXTRA_SUBJECT, asunto)
                    intent.putExtra(Intent.EXTRA_TEXT, mensaje)
                    intent.type = "message/rfc822"
                    context.startActivity(Intent.createChooser(intent, "Enviar e-mail"))


                }
            ){ Text(text = "Mas Detalles del Equipo") }
        }
    }
}
