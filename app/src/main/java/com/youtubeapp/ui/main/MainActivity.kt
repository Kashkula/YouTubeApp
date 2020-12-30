package com.youtubeapp.ui.main

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.youtubeapp.R
import com.youtubeapp.ui.wnetwork.UnNetworkFragment


@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        val isConnected: Boolean = activeNetwork?.isConnectedOrConnecting == true


        val fragmentManager: FragmentManager = supportFragmentManager

        if (isConnected)
            fragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment())
                .commit()
        else
            fragmentManager.beginTransaction()
                .replace(R.id.container, UnNetworkFragment())
                .commit()
    }
}