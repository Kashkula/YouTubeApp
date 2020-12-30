package com.youtubeapp.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.youtubeapp.repository.YouTubeRepository
import com.youtubeapp.ui.playlists.PlaylistInfo

class MainViewModel : ViewModel() {

    var repo = YouTubeRepository()

    fun fetchPlaylistsFromServer(): MutableLiveData<PlaylistInfo?> {
        return repo.fetchPlaylistsFromServer()
    }
}