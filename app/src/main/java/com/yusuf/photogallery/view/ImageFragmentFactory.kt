package com.yusuf.photogallery.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.bumptech.glide.RequestManager
import javax.inject.Inject

class ImageFragmentFactory @Inject constructor(
    private val glide: RequestManager
): FragmentFactory(){

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {

        return when(className){
            FeedFragment::class.java.name -> FeedFragment(glide)
            else -> super.instantiate(classLoader, className)
        }

    }
}