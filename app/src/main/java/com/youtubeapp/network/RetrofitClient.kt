package com.youtubeapp.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

const val BASE_URL = "https://www.googleapis.com/"

class RetrofitClient {

    // мы остановились мой друг на самом важном, т.е. принятие данных
    // 1:06:00 тел: 0706666119


    private val okhhtpClient: OkHttpClient = OkHttpClient().newBuilder()
        .connectTimeout(40, TimeUnit.SECONDS)
        .readTimeout(40, TimeUnit.SECONDS)
        .writeTimeout(40, TimeUnit.SECONDS)
        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(okhhtpClient)
        .build()

    fun instanceRetrofit(): YouTubeApi {
        return retrofit.create(YouTubeApi::class.java)
    }

}