package com.devika.ituneschallenge.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.devika.ituneschallenge.data.model.ItunesData

@Database(entities = [ItunesData::class], version = 1, exportSchema = false)
abstract class ItunesDatabase : RoomDatabase() {
    abstract fun ItunesDao(): ItunesDao

    companion object {
        fun buildDatabase(context: Context): ItunesDatabase {
            return Room.databaseBuilder(context, ItunesDatabase::class.java, "itune_database")
                .fallbackToDestructiveMigration().build()
        }
    }
}