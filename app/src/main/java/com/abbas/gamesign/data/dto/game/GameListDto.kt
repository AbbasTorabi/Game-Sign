package com.abbas.gamesign.data.dto.game

import com.abbas.gamesign.data.dto.base.BaseDto
import com.abbas.gamesign.data.dto.platform.PlatformParentDto
import com.google.gson.annotations.SerializedName
import java.util.*

data class GameListDto(

    @SerializedName("name") var name: String? = null,
    @SerializedName("slug") var slug: String? = null,
    @SerializedName("metacritic") var metacritic: Int? = null,
    @SerializedName("parent_platforms") var platforms: ArrayList<PlatformParentDto>? = null,
    @SerializedName("released") var released: Date? = null,
    @SerializedName("background_image") var backgroundImage: String? = null

) : BaseDto()