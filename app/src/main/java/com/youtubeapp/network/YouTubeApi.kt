package com.youtubeapp.network

import com.youtubeapp.ui.playlists.PlaylistInfo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface YouTubeApi {
    @GET("youtube/v3/playlists")
    suspend fun fetchPlaylists(
        @Query("part") part: String,
        @Query("channelId") channelId: String,
        @Query("key") key: String
    ): PlaylistInfo

    @GET("youtube/v3/playlistItems")
    suspend fun fetchDetailPlaylistById(
        @Query("part") part: String,
        @Query("pageToken") pageToken: String?,
        @Query("playlistId") playlistId: String,
        @Query("key") key: String
    ): PlaylistInfo
}