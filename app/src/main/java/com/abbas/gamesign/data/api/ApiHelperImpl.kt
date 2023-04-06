package com.abbas.gamesign.data.api

import com.abbas.gamesign.data.dto.base.ListResult
import com.abbas.gamesign.data.dto.game.GameDetailsDto
import com.abbas.gamesign.data.dto.game.GameListDto
import com.abbas.gamesign.data.dto.game.GameScreenshotDto
import com.abbas.gamesign.utils.Resource
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(private val apiService: ApiService) : ApiHelper, ApiProvider() {

    override suspend fun getGamesList(
        page: Int,
        size: Int,
        platforms: String,
        excludePlatforms: String,
        ordering: String,
        metacritic: String
    ): Resource<ListResult<GameListDto>> = getResult { apiService.getGamesList(page, size, platforms, excludePlatforms, ordering, metacritic) }

    override suspend fun getGameDetails(gameSlug: String): Resource<GameDetailsDto> = getResult { apiService.getGameDetails(gameSlug) }

    override suspend fun getGameScreenshots(gameSlug: String): Resource<ListResult<GameScreenshotDto>> = getResult { apiService.getGameScreenshots(gameSlug) }

}