package com.abbas.gamesign.model.game

import android.os.Parcelable
import com.abbas.gamesign.model.base.BaseModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class GameScreenshotModel(

    var image: String? = null

) : BaseModel(), Parcelable