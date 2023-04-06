package com.abbas.gamesign.model.platform

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PlatformModel(

    var name: String? = null

) : Parcelable