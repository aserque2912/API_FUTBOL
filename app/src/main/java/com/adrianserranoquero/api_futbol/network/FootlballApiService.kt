package com.adrianserranoquero.api_futbol.network

import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

// Response classes for teams
data class TeamsResponse(val response: List<TeamWrapper>)
data class TeamWrapper(val team: Team)

// Team data model
data class Team(
    val id: Int, // Team ID
    val name: String, // Team Name
    val league: Int, // League ID
    val season: Int, // Season year (YYYY)
    val country: String, // Country of the team
    val code: String, // 3-character team code
    val venue: Int, // Venue ID
    val logo: String // URL for the team logo
)

// Response classes for team statistics
data class TeamStatsResponse(val response: TeamStats)
data class TeamStats(
    val played: Int, // Matches played
    val wins: Int, // Matches won
    val draws: Int, // Matches drawn
    val losses: Int, // Matches lost
    val goalsFor: Int, // Goals scored
    val goalsAgainst: Int // Goals conceded
)

// API service interface
interface FootballApiService {
    @GET("teams")
    suspend fun getTeams(
        @Header("x-apisports-key") apiKey: String,
        @Query("league") leagueId: Int,
        @Query("season") season: Int
    ): TeamsResponse

    @GET("team/statistics")
    suspend fun getTeamStatistics(
        @Header("x-apisports-key") apiKey: String,
        @Query("team") teamId: Int,
        @Query("league") leagueId: Int,
        @Query("season") season: Int
    ): TeamStatsResponse
}
