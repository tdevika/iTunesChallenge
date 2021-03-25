package com.devika.ituneschallenge.injection.module

import android.content.Context
import com.devika.ituneschallenge.data.database.ItunesDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {
    @Provides
    @Singleton
    fun ProvideDatabase(context: Context) = ItunesDatabase.buildDatabase(context)

    @Provides
    @Singleton
    fun ProvideDao(itunesDatabase: ItunesDatabase)= itunesDatabase.ItunesDao()
}