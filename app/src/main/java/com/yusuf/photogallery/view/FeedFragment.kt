package com.yusuf.photogallery.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.yusuf.photogallery.R
import com.yusuf.photogallery.adapter.FeedRecyclerAdapter
import com.yusuf.photogallery.databinding.FragmentFeedBinding
import com.yusuf.photogallery.viewmodel.ImageViewModel
import javax.inject.Inject

class FeedFragment @Inject constructor(
    private val recyclerAdapter: FeedRecyclerAdapter
) : Fragment(R.layout.fragment_feed) {


    private var fragmentBinding: FragmentFeedBinding? = null
    lateinit var viewModel : ImageViewModel

    private val swipeCallBack = object : ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT){
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            return true
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val selectedImage = recyclerAdapter.images[viewHolder.layoutPosition]
            viewModel.deleteImage(selectedImage)
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity())[ImageViewModel::class.java]

        val binding = FragmentFeedBinding.bind(view)
        fragmentBinding = binding

        subscribeToObservers()

        binding.recyclerViewFeed.adapter= recyclerAdapter
        binding.recyclerViewFeed.layoutManager=LinearLayoutManager(requireContext())

        ItemTouchHelper(swipeCallBack).attachToRecyclerView(binding.recyclerViewFeed)

        binding.fab.setOnClickListener {
            findNavController().navigate(FeedFragmentDirections.actionFeedFragmentToSharingImage())
        }
    }

    private fun subscribeToObservers(){
        viewModel.imageList.observe(viewLifecycleOwner, Observer {
            recyclerAdapter.images = it
        })
    }

    override fun onDestroyView() {
        fragmentBinding = null
        super.onDestroyView()
    }

}