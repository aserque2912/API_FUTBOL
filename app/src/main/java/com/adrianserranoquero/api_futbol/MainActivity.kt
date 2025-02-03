package com.adrianserranoquero.api_futbol
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import com.adrianserranoquero.api_futbol.navigation.AppNavigation
import com.adrianserranoquero.api_futbol.theme.FutbolAppTheme
import com.adrianserranoquero.api_futbol.viewmodel.FootballViewModel
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FutbolAppTheme {
                val footballViewModel: FootballViewModel = viewModel()
                AppNavigation(footballViewModel)
            }
        }
    }
}
