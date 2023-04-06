package com.abbas.gamesign.data.api

import com.abbas.gamesign.data.dto.base.ListResult
import com.abbas.gamesign.data.dto.game.GameDetailsDto
import com.abbas.gamesign.data.dto.game.GameListDto
import com.abbas.gamesign.data.dto.game.GameScreenshotDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("games")
    suspend fun getGamesList(
        @Query("page") page: Int, @Query("page_size") size: Int, @Query("platforms") platforms: String, @Query("exclude_platforms") excludePlatforms: String, @Query("ordering") ordering: String, @Query("metacritic") metacritic: String
    ): Response<ListResult<GameListDto>>

    @GET("games/{game_slug}")
    suspend fun getGameDetails(
        @Path("game_slug") gameSlug: String,
    ): Response<GameDetailsDto>

    @GET("games/{game_slug}/screenshots")
    suspend fun getGameScreenshots(
        @Path("game_slug") gameSlug: String,
    ): Response<ListResult<GameScreenshotDto>>

}