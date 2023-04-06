package com.abbas.gamesign.model.game

import com.abbas.gamesign.model.base.BaseModel
import com.abbas.gamesign.model.developer.DeveloperModel
import com.abbas.gamesign.model.metacritic.MetacriticModel
import java.util.*

data class GameDetailsModel(

    var name: String? = null,
    var description: String? = null,
    var metacriticPlatforms: List<MetacriticModel>? = null,
    var released: Date? = null,
    var developers: List<DeveloperModel>? = null

) : BaseModel()