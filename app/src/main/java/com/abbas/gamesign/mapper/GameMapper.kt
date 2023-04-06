package com.abbas.gamesign.mapper

import com.abbas.gamesign.data.dto.game.GameDetailsDto
import com.abbas.gamesign.data.dto.game.GameListDto
import com.abbas.gamesign.data.dto.game.GameScreenshotDto
import com.abbas.gamesign.data.dto.platform.PlatformParentDto
import com.abbas.gamesign.model.game.GameDetailsModel
import com.abbas.gamesign.model.game.GameListModel
import com.abbas.gamesign.model.game.GameScreenshotModel
import com.abbas.gamesign.model.platform.PlatformModel
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper
interface GameMapper {

    @Mapping(source = "platform.name", target = "name")
    fun map(model: PlatformParentDto): PlatformModel

    fun map(model: List<GameListDto>): List<GameListModel>

    fun map(model: GameDetailsDto): GameDetailsModel

    fun mapScreenshots(model: List<GameScreenshotDto>): ArrayList<GameScreenshotModel>

}