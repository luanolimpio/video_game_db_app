package com.example.videogamedbapp.di

import com.example.videogamedbapp.data.repositories.RAWGRepositoryImpl
import com.example.videogamedbapp.domain.repositories.RAWGRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindNewsRepository(repository: RAWGRepositoryImpl): RAWGRepository
}