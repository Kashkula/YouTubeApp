package com.youtubeapp.ui.activity.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.youtubeapp.databinding.ListItemPlaylistsBinding
import com.youtubeapp.ui.model.PlaylistItem

class ItemAdapter(private val listener: Listener) :
    RecyclerView.Adapter<ItemAdapter.ViewHolder>() {


    private lateinit var binding: ListItemPlaylistsBinding
    private var list = ArrayList<PlaylistItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding =
            ListItemPlaylistsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding.root)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model = list[position]

        holder.itemView.apply {
            Glide.with(this).load(model.snippet?.thumbnails?.medium?.url.toString())
                .into(binding.iv)

            this.setOnClickListener {
                listener.onItemClicked(list[position])
            }
            binding.tvTitle.text = model.snippet?.title
        }
    }

    fun addList(list: MutableList<PlaylistItem>?) {
        this.list.let {
            it.clear()
            list?.let { it1 -> it.addAll(it1) }
        }
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    interface Listener {
        fun onItemClicked(item: PlaylistItem)
    }


}