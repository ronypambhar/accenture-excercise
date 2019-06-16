package com.app.accentureexcercise.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [AlbumModel::class], version = 1)
abstract class AppDb : RoomDatabase() {
    companion object {
        private var instance: AppDb? = null

        fun getInstance(context: Context): AppDb {
            if (instance == null) {
                instance = Room.databaseBuilder<AppDb>(context, AppDb::class.java, "albums")
                        .allowMainThreadQueries().fallbackToDestructiveMigration()
                        .build()
            }
            return instance!!
        }

        fun destroyInstance() {
            instance = null
        }
    }

    abstract fun getDao(): AlbumModelDao
}