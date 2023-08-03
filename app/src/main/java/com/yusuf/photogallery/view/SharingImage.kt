package com.yusuf.photogallery.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.yusuf.photogallery.R
import com.yusuf.photogallery.databinding.FragmentSharingImageBinding


class SharingImage : Fragment(R.layout.fragment_sharing_image) {

    private lateinit var fragmentBinding: FragmentSharingImageBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentSharingImageBinding.bind(view)
        fragmentBinding = binding

        binding.imageView.setOnClickListener {
            findNavController().navigate(SharingImageDirections.actionSharingImageToImageSearch())
        }
        
        val callback= object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().popBackStack()
            }
        }

        requireActivity().onBackPressedDispatcher.addCallback(callback)
    }

}