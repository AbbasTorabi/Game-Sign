package com.abbas.gamesign.model.metacritic

import com.abbas.gamesign.model.platform.PlatformModel

data class MetacriticModel(

    var metaScore: Int? = null,
    var url: String? = null,
    var platform: PlatformModel? = null

)