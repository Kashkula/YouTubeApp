package com.youtubeapp.ui.main

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.youtubeapp.databinding.MainFragmentBinding
import com.youtubeapp.ui.playlists.PlaylistItem


@Suppress("DEPRECATION")
class MainFragment : Fragment() {

    private lateinit var adapter: ItemAdapter
    private lateinit var list: MutableList<PlaylistItem>

    private lateinit var vm: MainViewModel
    private lateinit var binding: MainFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MainFragmentBinding.inflate(inflater)
        return binding.root
    }

    @SuppressLint("MissingPermission")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        vm = ViewModelProvider(this).get(MainViewModel::class.java)


        init()



        fetchData()


    }

    private fun fetchData() {
        vm.fetchPlaylistsFromServer().observe(requireActivity(), Observer {
            adapter.addList(it!!.items)
            Log.d("TAG", "fetchData: " + it.items!![0].snippet?.thumbnails?.medium?.url)
        })
    }

    private fun init() {
        list = mutableListOf()
        adapter = ItemAdapter(list)
        binding.rv.adapter = adapter
    }
}