package com.abbas.gamesign.di.module

import android.content.Context
import com.abbas.gamesign.App
import com.abbas.gamesign.BuildConfig
import com.abbas.gamesign.data.api.*
import com.abbas.gamesign.di.qualifier.ApiKey
import com.abbas.gamesign.di.qualifier.BaseUrl
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun providesMainApplicationInstance(@ApplicationContext context: Context): App {
        return context as App
    }

    @Provides
    @Singleton
    @BaseUrl
    fun provideBaseUrl(): String = BuildConfig.BASE_URL

    @Provides
    @Singleton
    @ApiKey
    fun provideApiKey(): String = BuildConfig.API_KEY

    @Singleton
    @Provides
    fun provideApiInterceptor(@ApiKey apiKey: String) = ApiInterceptor(apiKey)

    @Singleton
    @Provides
    fun provideOkHttpClient(apiInterceptor: ApiInterceptor) =
        OkHttpClient.Builder().readTimeout(40, TimeUnit.SECONDS).writeTimeout(40, TimeUnit.SECONDS)
            .addInterceptor(apiInterceptor).build()

    @Provides
    @Singleton
    fun provideRetrofit(@BaseUrl baseUrl: String, okHttpClient: OkHttpClient): Retrofit {
        val gson = GsonBuilder().create()
        val retrofit = Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(
            NullOnEmptyConverterFactory()
        ).addConverterFactory(GsonConverterFactory.create(gson)).client(okHttpClient).build()
        retrofit.create(ApiService::class.java)
        return retrofit
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit) = retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideApiHelper(apiHelper: ApiHelperImpl): ApiHelper = apiHelper

}