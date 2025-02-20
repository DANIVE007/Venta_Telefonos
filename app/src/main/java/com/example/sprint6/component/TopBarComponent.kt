package com.example.sprint6.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight

class TopBarComponent {
}@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarComponent(
    Titulo: String,
    mostrarBotton: Boolean = false,
    onClick: () -> Unit

){
    TopAppBar(
        title = { Text(text = Titulo, color = Color.White, fontWeight = FontWeight.Bold) },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color(0xFF286963)
        ),
        navigationIcon = {
            if (mostrarBotton){
                IconButton(onClick= { onClick() }) {
                    Icon(
                        Icons.AutoMirrored.Default.ArrowBack,
                        contentDescription = "",
                        tint = Color.White

                    )
                }
            }
        }
    )
}