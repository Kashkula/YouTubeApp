package com.youtubeapp.repository

import androidx.lifecycle.liveData
import com.youtubeapp.network.Resource
import com.youtubeapp.network.RetrofitClient
import kotlinx.coroutines.Dispatchers

class YouTubeRepository {


    val key = "AIzaSyDNWAAfVD8nd6X--WF7aI2q7di8MrOquNM"
    val channelId = "UCiGm_E4ZwYSHV3bcW1pnSeQ"
    val part = "snippet,contentDetails"

    private val api = RetrofitClient().instanceRetrofit()

    fun fetchPlaylists() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = api.fetchPlaylists(part, channelId, key)))
        } catch (ex: Exception) {
            emit(Resource.error(data = null, message = ex.message.toString()))
        }
    }

    fun fetchVideoListById(id: String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(
                Resource.success(
                    data = api.fetchDetailPlaylistById(
                        part,
                        pageToken = null,
                        playlistId = id,
                        key = key
                    )
                )
            )
        } catch (ex: Exception) {
            emit(Resource.error(data = null, message = ex.message.toString()))
        }

    }


//    fun fetchPlaylistsFromServer(): MutableLiveData<PlaylistInfo?> {
//        val data = MutableLiveData<PlaylistInfo?>()
//        api.fetchPlaylists(part, channelId, key).enqueue(object : Callback<PlaylistInfo?> {
//            override fun onFailure(call: Call<PlaylistInfo?>, t: Throwable) {
//               data = null
//            }
//            override fun onResponse(call: Call<PlaylistInfo?>, response: Response<PlaylistInfo?>) {
//                data.value = response.body()
//            }
//        })
//        return data
//    }


}