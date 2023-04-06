package com.abbas.gamesign.data.dto.developer

import com.abbas.gamesign.data.dto.base.BaseDto
import com.google.gson.annotations.SerializedName

data class DeveloperDto(

    @SerializedName("name") var name: String? = null

) : BaseDto()