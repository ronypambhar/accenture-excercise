package com.app.accentureexcercise.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class AlbumModel(@PrimaryKey
                      val userId: Int = 0,
                      val id: Int?,
                      val title: String?) : Serializable