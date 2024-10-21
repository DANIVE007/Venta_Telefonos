package com.example.sprint6

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.sprint6.navigation.NavManager
import com.example.sprint6.ui.theme.Sprint6Theme
import com.example.sprint6.viewModel.ProductViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        val viewModel : ProductViewModel by viewModels()
        setContent {
            Sprint6Theme {
                NavManager(viewModel)
            }
        }
    }
}

