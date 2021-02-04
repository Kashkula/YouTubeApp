package com.youtubeapp.ui.activity.video

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.youtubeapp.network.Resource
import com.youtubeapp.repository.YouTubeRepository
import com.youtubeapp.ui.model.PlaylistInfo

class VideoListViewModel : ViewModel() {

    fun fetchVideosByIdFromServer(id: String): LiveData<Resource<PlaylistInfo?>> {
        return YouTubeRepository().fetchVideoListById(id)
    }
}
