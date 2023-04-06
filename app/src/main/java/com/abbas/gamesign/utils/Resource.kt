package com.abbas.gamesign.utils

data class Resource<out T>(val success: Boolean, val data: T?, val message: String?, val code: Int?) {

    companion object {
        fun <T> success(data: T): Resource<T> {
            return Resource(true, data, null, 200)
        }

        fun <T> error(message: String, code: Int?, data: T? = null): Resource<T> {
            return Resource(false, data, message, code)
        }
    }
}

