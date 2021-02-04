package com.youtubeapp.ui.activity.video

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.youtubeapp.databinding.ActivityVideoListBinding
import com.youtubeapp.network.Status
import com.youtubeapp.ui.activity.main.ItemAdapter
import com.youtubeapp.ui.model.PlaylistItem

@Suppress("DEPRECATION")
class VideoListActivity : AppCompatActivity(), ItemAdapter.Listener {

    private lateinit var playlistItem: PlaylistItem
    private lateinit var vm: VideoListViewModel
    private lateinit var adapter: ItemAdapter
    private lateinit var binding: ActivityVideoListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVideoListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()

        playlistItem = intent.getSerializableExtra("KEY") as PlaylistItem

        getFetchVideo()

    }

    private fun getFetchVideo() {
        playlistItem.id?.let { it ->
            vm.fetchVideosByIdFromServer(it).observe(this, Observer {
                if (it != null)
                    when (it.status) {
                        Status.SUCCESS -> it.data?.items.let { data -> adapter.addList(data) }
                    }
            })
        }
    }

    private fun init() {
        vm = ViewModelProvider(this).get(VideoListViewModel::class.java)
        adapter = ItemAdapter(this)
        binding.rv.adapter = adapter

    }

    override fun onItemClicked(item: PlaylistItem) {

    }
}