package com.adrianserranoquero.api_futbol.navigation
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.*
import com.adrianserranoquero.api_futbol.ui.theme.TeamDetailScreen
import com.adrianserranoquero.api_futbol.ui.theme.TeamsScreen
import com.adrianserranoquero.api_futbol.viewmodel.FootballViewModel
@Composable
fun AppNavigation(viewModel: FootballViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "teams") {
        composable("teams") {
            TeamsScreen(navController, viewModel)
        }
        composable("detail/{teamId}") { backStackEntry ->
            val teamId = backStackEntry.arguments?.getString("teamId")
            val team = viewModel.teams.collectAsState().value.find { it.id == teamId?.toIntOrNull() }
            if (team != null) {
                TeamDetailScreen(navController, team)
            }
        }
    }
}
