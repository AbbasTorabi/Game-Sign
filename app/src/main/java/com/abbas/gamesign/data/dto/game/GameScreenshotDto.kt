package com.abbas.gamesign.data.dto.game

import com.abbas.gamesign.data.dto.base.BaseDto
import com.google.gson.annotations.SerializedName

data class GameScreenshotDto(

    @SerializedName("image") var image: String? = null

) : BaseDto()