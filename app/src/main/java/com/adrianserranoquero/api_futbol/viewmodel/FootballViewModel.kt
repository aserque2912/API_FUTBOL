package com.adrianserranoquero.api_futbol.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adrianserranoquero.api_futbol.network.FootballApiService
import com.adrianserranoquero.api_futbol.network.RetrofitInstance
import com.adrianserranoquero.api_futbol.network.Team
import com.adrianserranoquero.api_futbol.network.TeamStats
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FootballViewModel : ViewModel() {
    private val apiKey = "eafe43ae36244722638962552bfa3642"
    private val leagueId = 39
    private val season = 2023

    // Flow for the list of teams
    private val _teams = MutableStateFlow<List<Team>>(emptyList())
    val teams: StateFlow<List<Team>> get() = _teams

    // Flow for team statistics
    private val _teamStats = MutableStateFlow<TeamStats?>(null)
    val teamStats: StateFlow<TeamStats?> get() = _teamStats

    init {
        fetchTeams()
    }

    // Fetch the list of teams
    private fun fetchTeams() {
        val service = RetrofitInstance.retrofit.create(FootballApiService::class.java)
        viewModelScope.launch {
            try {
                val response = service.getTeams(apiKey, leagueId, season)
                if (response.response.isNotEmpty()) {
                    _teams.value = response.response.map { it.team }
                } else {
                    _teams.value = emptyList()
                }
            } catch (e: Exception) {
                e.printStackTrace()
                _teams.value = emptyList()
            }
        }
    }

    // Fetch team statistics
    fun fetchTeamStatistics(teamId: Int) {
        val service = RetrofitInstance.retrofit.create(FootballApiService::class.java)
        viewModelScope.launch {
            try {
                val response = service.getTeamStatistics(apiKey, teamId, leagueId, season)
                println("Team Statistics Response: $response") // Debugging
                _teamStats.value = response.response
            } catch (e: Exception) {
                println("Error fetching team statistics: ${e.message}") // Debugging
                e.printStackTrace()
                _teamStats.value = null
            }
        }
    }
}
