package com.williamzabot.queens.data.di

import com.williamzabot.queens.data.repositories.QueenRepositoryImpl
import com.williamzabot.queens.domain.repositories.QueenRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
abstract class DataModule {

    @Singleton
    @Binds
    abstract fun injectQueenRepository(
        queenRepositoryImpl: QueenRepositoryImpl
    ): QueenRepository
}