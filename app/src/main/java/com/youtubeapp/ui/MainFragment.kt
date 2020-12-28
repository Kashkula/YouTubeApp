package com.youtubeapp.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.youtubeapp.R
import com.youtubeapp.databinding.MainFragmentBinding

class MainFragment : Fragment() {

    private lateinit var adapter: ItemAdapter
    private lateinit var list: MutableList<String>

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: MainFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MainFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        init()

    }

    private fun init() {
        list = mutableListOf()
        addList()
        adapter = ItemAdapter(list)
        binding.rv.adapter = adapter
    }

    private fun addList() {
        list.let {
            for (i in 1..15) {
                it.add("fdsafjkl")
            }
        }
    }

}