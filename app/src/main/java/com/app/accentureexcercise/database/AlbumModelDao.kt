package com.app.accentureexcercise.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.Query

@Dao
interface AlbumModelDao {

    @Insert(onConflict = IGNORE)
    fun insertAlbum(album: AlbumModel)

    @Query("select * from AlbumModel")
    fun getAlbums(): LiveData<List<AlbumModel>>

}