package com.adrianserranoquero.api_futbol.network
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query
data class Team(val id: Int, val name: String, val logo: String)
data class TeamsResponse(val response: List<TeamWrapper>)
data class TeamWrapper(val team: Team)
interface FootballApiService {
    @GET("teams")
    suspend fun getTeams(
        @Header("x-apisports-key") apiKey: String,
        @Query("league") leagueId: Int,
        @Query("season") season: Int
    ): TeamsResponse
}