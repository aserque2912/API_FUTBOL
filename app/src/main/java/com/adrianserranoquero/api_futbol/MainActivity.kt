package com.adrianserranoquero.api_futbol

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.adrianserranoquero.api_futbol.navigation.AppNavigation
import com.adrianserranoquero.api_futbol.ui.theme.FutbolAppTheme
import com.adrianserranoquero.api_futbol.viewmodel.FootballViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FutbolAppTheme {
                val footballViewModel: FootballViewModel = viewModel()
                val navController = rememberNavController()

                AppNavigation( navController = navController, viewModel = footballViewModel )
            }
        }
    }
}
