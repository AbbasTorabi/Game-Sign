package com.abbas.gamesign.data.dto.platform

import com.google.gson.annotations.SerializedName

data class PlatformParentDto(

    @SerializedName("platform") var platform: PlatformDto? = null

)