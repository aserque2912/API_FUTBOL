package com.adrianserranoquero.api_futbol.ui.theme

import com.adrianserranoquero.api_futbol.network.Team
import com.adrianserranoquero.api_futbol.viewmodel.FootballViewModel
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage

@Composable
fun TeamsScreen(navController: NavController, viewModel: FootballViewModel) {
    val teams = viewModel.teams.collectAsState().value

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Equipos de Fútbol") }
            )
        }
    ) { paddingValues ->
        if (teams.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                Text("No hay equipos disponibles.")
            }
        } else {
            LazyColumn(
                contentPadding = PaddingValues(16.dp),
                modifier = Modifier.padding(paddingValues)
            ) {
                items(teams) { team ->
                    TeamItem(team, navController)
                }
            }
        }
    }
}


@Composable
fun TeamItem(team: Team, navController: NavController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { navController.navigate("detail/${team.id}") },
        elevation = 4.dp
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(text = team.name, style = MaterialTheme.typography.h6)
            }
            AsyncImage(
                model = team.logo,
                contentDescription = "Logo del equipo",
                modifier = Modifier.size(50.dp)
            )
        }
    }
}
