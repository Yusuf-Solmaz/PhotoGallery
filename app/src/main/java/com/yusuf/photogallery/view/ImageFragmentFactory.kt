package com.yusuf.photogallery.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.bumptech.glide.RequestManager
import com.yusuf.photogallery.adapter.FeedRecyclerAdapter
import com.yusuf.photogallery.adapter.ImageSearchRecyclerAdapter
import javax.inject.Inject

class ImageFragmentFactory @Inject constructor(
    private val imageSearchRecyclerAdapter: ImageSearchRecyclerAdapter,
    private val feedRecyclerAdapter: FeedRecyclerAdapter,
    private val glide: RequestManager
): FragmentFactory(){

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {

        return when(className){
            FeedFragment::class.java.name -> FeedFragment(feedRecyclerAdapter)
            ImageSearch::class.java.name -> ImageSearch(imageSearchRecyclerAdapter)
            SharingImage::class.java.name -> SharingImage(glide)
            else -> super.instantiate(classLoader, className)
        }

    }
}