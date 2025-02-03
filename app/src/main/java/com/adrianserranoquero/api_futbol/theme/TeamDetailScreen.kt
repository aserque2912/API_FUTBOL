package com.adrianserranoquero.api_futbol.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.adrianserranoquero.api_futbol.network.Team
import com.adrianserranoquero.api_futbol.viewmodel.FootballViewModel

@Composable
fun TeamDetailScreen(
    team: Team,
    padding: PaddingValues,
    viewModel: FootballViewModel,
    navController: NavHostController
) {
    val teamStats = viewModel.teamStats.collectAsState().value

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Team Details", fontSize = 20.sp) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = androidx.compose.material.icons.Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(padding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Imagen del logotipo del equipo
            Box(
                modifier = Modifier
                    .size(128.dp)
                    .clip(CircleShape)
            ) {
                Image(
                    painter = rememberAsyncImagePainter(model = team.logo),
                    contentDescription = "Team Logo",
                    modifier = Modifier.fillMaxSize()
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Información básica del equipo
            Card(
                elevation = 4.dp,
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Team Name: ${team.name}",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(text = "Country: ${team.country}", fontSize = 16.sp)
                    Text(text = "Code: ${team.code}", fontSize = 16.sp)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            teamStats?.let {
                Card(
                    elevation = 4.dp,
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = "Statistics:",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(text = "Matches Played: ${it.played}", fontSize = 16.sp)
                        Text(text = "Wins: ${it.wins}", fontSize = 16.sp)
                        Text(text = "Draws: ${it.draws}", fontSize = 16.sp)
                        Text(text = "Losses: ${it.losses}", fontSize = 16.sp)
                        Text(text = "Goals For: ${it.goalsFor}", fontSize = 16.sp)
                        Text(text = "Goals Against: ${it.goalsAgainst}", fontSize = 16.sp)
                    }
                }
            }
        }
    }
}
