package com.abbas.gamesign.data.dto.base

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ListResult<T>(

    @SerializedName("results") var results: List<T>? = null,
    @SerializedName("count") var count: Int? = null

) : Serializable