package com.abbas.gamesign.data.dto.metacritic

import com.abbas.gamesign.data.dto.platform.PlatformDto
import com.google.gson.annotations.SerializedName

data class MetacriticDto(

    @SerializedName("metascore") var metaScore: Int? = null,
    @SerializedName("url") var url: String? = null,
    @SerializedName("platform") var platform: PlatformDto? = null

)