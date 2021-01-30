package com.youtubeapp.ui.wnetwork

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import com.youtubeapp.R
import com.youtubeapp.databinding.FragmentUnNetworkBinding
import com.youtubeapp.ui.activity.main.MainFragment

@Suppress("DEPRECATION")
class UnNetworkFragment : Fragment() {

    private lateinit var binding: FragmentUnNetworkBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUnNetworkBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btn.setOnClickListener {
            val cm =
                requireActivity().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
            val isConnected: Boolean = activeNetwork?.isConnectedOrConnecting == true
            val fragmentManager: FragmentManager = requireActivity().supportFragmentManager

            if (isConnected)
                fragmentManager.beginTransaction()
                    .replace(R.id.container, MainFragment())
                    .commit()
            else
                Toast.makeText(context, R.string.проверка, Toast.LENGTH_SHORT).show()
        }
    }

}