package com.abbas.gamesign.data.repository

import com.abbas.gamesign.data.api.ApiHelper
import com.abbas.gamesign.data.dto.base.ListResult
import com.abbas.gamesign.data.dto.game.GameDetailsDto
import com.abbas.gamesign.data.dto.game.GameListDto
import com.abbas.gamesign.data.dto.game.GameScreenshotDto
import com.abbas.gamesign.utils.Resource
import javax.inject.Inject

class GameRepositoryImpl @Inject constructor(private val apiHelper: ApiHelper) : GameRepository {

    override suspend fun getGamesList(
        page: Int, size: Int, platforms: String, excludePlatforms: String, ordering: String, metacritic: String
    ): Resource<ListResult<GameListDto>> = apiHelper.getGamesList(page, size, platforms, excludePlatforms, ordering, metacritic)

    override suspend fun getGameDetails(gameSlug: String): Resource<GameDetailsDto> = apiHelper.getGameDetails(gameSlug)

    override suspend fun getGameScreenshots(gameSlug: String): Resource<ListResult<GameScreenshotDto>> = apiHelper.getGameScreenshots(gameSlug)

}