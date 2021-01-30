package com.youtubeapp.ui.activity.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.youtubeapp.network.Resource
import com.youtubeapp.repository.YouTubeRepository
import com.youtubeapp.ui.playlists.PlaylistInfo

class MainViewModel : ViewModel() {


    fun fetchPlaylistsFromServer(): LiveData<Resource<PlaylistInfo?>> {
        return YouTubeRepository().fetchPlaylists()
    }
}