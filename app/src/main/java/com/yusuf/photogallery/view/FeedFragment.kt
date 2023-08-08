package com.yusuf.photogallery.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.RequestManager
import com.yusuf.photogallery.R
import com.yusuf.photogallery.databinding.FragmentFeedBinding
import javax.inject.Inject

class FeedFragment @Inject constructor(
    private val glide: RequestManager
) : Fragment(R.layout.fragment_feed) {


    private var fragmentBinding: FragmentFeedBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentFeedBinding.bind(view)
        fragmentBinding = binding

        binding.fab.setOnClickListener {
            findNavController().navigate(FeedFragmentDirections.actionFeedFragmentToSharingImage())
        }
    }

    override fun onDestroyView() {
        fragmentBinding = null
        super.onDestroyView()
    }

}