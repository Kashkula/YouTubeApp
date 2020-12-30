package com.youtubeapp.repository

import androidx.lifecycle.MutableLiveData
import com.youtubeapp.network.RetrofitClient
import com.youtubeapp.ui.playlists.PlaylistInfo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class YouTubeRepository {


    val key = "AIzaSyDNWAAfVD8nd6X--WF7aI2q7di8MrOquNM"
    val channelId = "UCiGm_E4ZwYSHV3bcW1pnSeQ"
    val part = "snippet,contentDetails"

    private val api = RetrofitClient().instanceRetrofit()

    fun fetchPlaylistsFromServer(): MutableLiveData<PlaylistInfo?> {
        val data = MutableLiveData<PlaylistInfo?>()
        api.fetchPlaylists(part, channelId, key).enqueue(object : Callback<PlaylistInfo?> {
            override fun onFailure(call: Call<PlaylistInfo?>, t: Throwable) {
//                data = null
            }

            override fun onResponse(call: Call<PlaylistInfo?>, response: Response<PlaylistInfo?>) {
                data.value = response.body()
            }
        })
        return data
    }


}