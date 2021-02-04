package com.youtubeapp.ui.activity.main

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.youtubeapp.databinding.MainFragmentBinding
import com.youtubeapp.network.Status
import com.youtubeapp.ui.activity.video.VideoListActivity
import com.youtubeapp.ui.model.PlaylistItem


@Suppress("DEPRECATION")
class MainFragment : Fragment(), ItemAdapter.Listener {

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
            when (it.status) {
                Status.SUCCESS -> {
                    adapter.addList(it.data?.items)
                }
                Status.ERROR -> Toast.makeText(
                    requireContext(),
                    it.message ?: "ERROR",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }

    private fun init() {
        list = mutableListOf()
        adapter = ItemAdapter(this)
        binding.rv.adapter = adapter
    }

    override fun onItemClicked(item: PlaylistItem) {
        val intent = Intent(requireContext(), VideoListActivity::class.java)
        intent.putExtra("KEY", item)
        startActivity(intent)
    }
}