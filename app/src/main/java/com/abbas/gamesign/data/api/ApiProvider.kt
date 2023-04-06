package com.abbas.gamesign.data.api

import com.abbas.gamesign.utils.Resource
import retrofit2.Response

abstract class ApiProvider {

    protected suspend fun <T> getResult(call: suspend () -> Response<T>): Resource<T> {
        try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    return Resource.success(body)
                }
            } else {
                return Resource.error(response.message(), response.code())
            }
            return error(response.message(), response.code())
        } catch (e: Exception) {
            return error(e.message ?: e.toString(), 400)
        }
    }

    private fun <T> error(message: String, code: Int): Resource<T> {
        return Resource.error("Network call has failed for a following reason: $message", code)
    }

}