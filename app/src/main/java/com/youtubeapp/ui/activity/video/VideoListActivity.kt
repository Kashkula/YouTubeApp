package com.youtubeapp.ui.activity.video

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.youtubeapp.R
import com.youtubeapp.databinding.ActivityVideoListBinding
import com.youtubeapp.network.Status
import com.youtubeapp.ui.activity.main.ItemAdapter
import com.youtubeapp.ui.playlists.PlaylistItem

class VideoListActivity : AppCompatActivity(), ItemAdapter.Listener {

    private lateinit var playlistItem: PlaylistItem
    private lateinit var vm: VideoListViewModel
    private lateinit var adapter: ItemAdapter
    private lateinit var binding: ActivityVideoListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVideoListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        adapter = ItemAdapter(this)
        binding.rv.adapter = adapter

        vm = ViewModelProvider(this).get(VideoListViewModel::class.java)
        playlistItem = intent.getSerializableExtra("KEY") as PlaylistItem

        Log.d("tag", playlistItem.id.toString())


        playlistItem.id?.let { it ->
            vm.fetchVideosByIdFromServer(it).observe(this, Observer {
                if (it != null) {
                    when(it.status) {
                        Status.SUCCESS -> it.data?.items.let { data -> adapter.addList(data) }

                    }
                    Log.d("tag", it.data.toString())

                }
            })
        }
    }

    override fun onItemClicked(item: PlaylistItem) {

    }
}