package com.youtubeapp.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.youtubeapp.databinding.ListItemBinding
import com.youtubeapp.ui.playlists.PlaylistItem

class ItemAdapter(private var list: MutableList<PlaylistItem>) :
    RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    private lateinit var binding: ListItemBinding


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding.root)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model = list[position]

        Glide.with(holder.itemView).load(model.snippet?.thumbnails?.medium?.url.toString())
            .into(binding.iv)

        binding.tvTitle.text = model.snippet?.title
    }

    fun addList(list: MutableList<PlaylistItem>?) {
        this.list.let {
            it.clear()
            list?.let { it1 -> it.addAll(it1) }
        }
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)


}