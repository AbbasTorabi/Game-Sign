package com.abbas.gamesign.interfaces

interface ItemClickListener<T: Any> {

    fun <T> onClick(model: T)

}