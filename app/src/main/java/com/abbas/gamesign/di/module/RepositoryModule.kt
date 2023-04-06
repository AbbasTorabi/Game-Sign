package com.abbas.gamesign.di.module

import com.abbas.gamesign.data.repository.GameRepository
import com.abbas.gamesign.data.repository.GameRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun provideGameRepository(gameRepository: GameRepositoryImpl): GameRepository = gameRepository

}