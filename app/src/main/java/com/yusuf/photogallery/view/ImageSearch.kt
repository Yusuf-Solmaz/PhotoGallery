package com.yusuf.photogallery.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.yusuf.photogallery.R
import com.yusuf.photogallery.databinding.FragmentImageSearchBinding

class ImageSearch : Fragment(R.layout.fragment_image_search) {


    private  var fragmentBinding: FragmentImageSearchBinding? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentImageSearchBinding.bind(view)
        fragmentBinding=binding



    }
}