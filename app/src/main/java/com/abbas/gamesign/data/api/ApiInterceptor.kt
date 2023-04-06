package com.abbas.gamesign.data.api

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class ApiInterceptor constructor(private val apiKey: String) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {

        val url = chain.request()
            .url
            .newBuilder()
            .addQueryParameter("key", apiKey)
            .build()

        return chain.proceed(chain.request().newBuilder().url(url).build())

    }
}
