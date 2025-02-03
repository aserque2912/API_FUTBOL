package com.adrianserranoquero.api_futbol.viewmodel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adrianserranoquero.api_futbol.network.FootballApiService
import com.adrianserranoquero.api_futbol.network.RetrofitInstance
import com.adrianserranoquero.api_futbol.network.Team
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
class FootballViewModel : ViewModel() {
    private val apiKey = "eafe43ae36244722638962552bfa3642"
    private val leagueId = 39
    private val season = 2023
    private val _teams = MutableStateFlow<List<Team>>(emptyList())
    val teams: StateFlow<List<Team>> get() = _teams
    init {
        fetchTeams()
    }
    private fun fetchTeams() {
        val service = RetrofitInstance.retrofit.create(FootballApiService::class.java)
        viewModelScope.launch {
            try {
                val response = service.getTeams(apiKey, leagueId, season)
                if (response.response.isNotEmpty()) {
                    _teams.value = response.response.map { it.team }
                } else {
                    // Manejar el caso en el que no hay equipos
                    _teams.value = emptyList()
                }
            } catch (e: Exception) {
                e.printStackTrace()
                _teams.value = emptyList() // Mantener la app estable
            }
        }
    }
}