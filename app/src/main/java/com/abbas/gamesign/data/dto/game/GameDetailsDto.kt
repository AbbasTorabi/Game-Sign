package com.abbas.gamesign.data.dto.game

import com.abbas.gamesign.data.dto.base.BaseDto
import com.abbas.gamesign.data.dto.developer.DeveloperDto
import com.abbas.gamesign.data.dto.metacritic.MetacriticDto
import com.google.gson.annotations.SerializedName
import java.util.*

data class GameDetailsDto(

    @SerializedName("name") var name: String? = null,
    @SerializedName("description") var description: String? = null,
    @SerializedName("metacritic_platforms") var metacriticPlatforms: List<MetacriticDto>? = null,
    @SerializedName("released") var released: Date? = null,
    @SerializedName("developers") var developers: List<DeveloperDto>? = null

) : BaseDto()