package com.abbas.gamesign.model.game

import android.os.Parcelable
import com.abbas.gamesign.model.base.BaseModel
import com.abbas.gamesign.model.platform.PlatformModel
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class GameListModel(
    var name: String? = null,
    var slug: String? = null,
    var metacritic: Int? = null,
    var platforms: ArrayList<PlatformModel>? = null,
    var released: Date? = null,
    var backgroundImage: String? = null
) : BaseModel(), Parcelable