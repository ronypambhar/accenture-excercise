package com.app.accentureexcercise.ui

import android.app.Application
import com.app.accentureexcercise.database.AlbumModelDao
import com.app.accentureexcercise.database.AppDb

class MyApp : Application() {
    private lateinit var dao: AlbumModelDao

    companion object {
        private lateinit var mInstance: MyApp

        @Synchronized
        fun getInstance(): MyApp {
            return mInstance
        }
    }

    override fun onCreate() {
        super.onCreate()
        mInstance = this
        dao = AppDb.getInstance(applicationContext).getDao()
    }

    fun getDao(): AlbumModelDao = dao
}