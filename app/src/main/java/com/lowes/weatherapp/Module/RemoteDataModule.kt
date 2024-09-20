package com.lowes.weatherapp.Module

import com.lowes.weatherapp.data.RemoteRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RemoteDataModule {
    @Provides
    fun repository() = RemoteRepositoryImpl()
}