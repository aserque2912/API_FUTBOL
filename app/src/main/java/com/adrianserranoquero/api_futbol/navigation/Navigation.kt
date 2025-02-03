package com.adrianserranoquero.api_futbol.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.adrianserranoquero.api_futbol.network.Team
import com.adrianserranoquero.api_futbol.ui.TeamDetailScreen
import com.adrianserranoquero.api_futbol.ui.TeamScreen
import com.adrianserranoquero.api_futbol.viewmodel.FootballViewModel

// Definición de rutas de navegación
sealed class Screen(val route: String) {
    object TeamList : Screen("team_list")
    object TeamDetail : Screen("team_detail")
}

@Composable
fun AppNavigation(
    navController: NavHostController,
    viewModel: FootballViewModel
) {
    // Observa el flujo de equipos desde el ViewModel
    val teams = viewModel.teams.collectAsState().value

    // Configuración del NavHost
    NavHost(navController = navController, startDestination = Screen.TeamList.route) {
        // Pantalla de listado de equipos
        composable(route = Screen.TeamList.route) {
            TeamScreen(
                teams = teams,
                onTeamClick = { team ->
                    navController.navigate("${Screen.TeamDetail.route}/${team.id}")
                }
            )
        }

        // Pantalla de detalles de un equipo
        composable(route = "${Screen.TeamDetail.route}/{teamId}") { backStackEntry ->
            val teamId = backStackEntry.arguments?.getString("teamId")?.toIntOrNull()
            val selectedTeam = teams.find { it.id == teamId }
            selectedTeam?.let { team ->
                viewModel.fetchTeamStatistics(team.id) // Carga las estadísticas antes de mostrar la pantalla
                TeamDetailScreen(
                    team = team,
                    padding = PaddingValues(),
                    viewModel = viewModel,
                    navController = navController
                )
            }
        }
    }
}
