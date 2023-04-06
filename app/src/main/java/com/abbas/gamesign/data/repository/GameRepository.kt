package com.abbas.gamesign.data.repository

import com.abbas.gamesign.data.dto.base.ListResult
import com.abbas.gamesign.data.dto.game.GameDetailsDto
import com.abbas.gamesign.data.dto.game.GameListDto
import com.abbas.gamesign.data.dto.game.GameScreenshotDto
import com.abbas.gamesign.utils.Resource

interface GameRepository {

    suspend fun getGamesList(
        page: Int, size: Int, platforms: String, excludePlatforms: String, ordering: String, metacritic: String
    ): Resource<ListResult<GameListDto>>

    suspend fun getGameDetails(
        gameSlug: String
    ): Resource<GameDetailsDto>

    suspend fun getGameScreenshots(
        gameSlug: String
    ): Resource<ListResult<GameScreenshotDto>>

}