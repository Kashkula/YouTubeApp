package com.youtubeapp.data

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.youtubeapp.ui.model.PlaylistInfo

interface PlaylistInfoDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addPlaylistInfo(model: MutableList<PlaylistInfo>)

    @Query("SELECT * FROM playListInfo")
    fun getPlaylists(): MutableList<PlaylistInfo>

    @Query("DELETE FROM playListInfo")
    fun deleteAll()

}