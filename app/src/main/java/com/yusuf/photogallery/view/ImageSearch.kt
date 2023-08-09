package com.yusuf.photogallery.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.yusuf.photogallery.R
import com.yusuf.photogallery.adapter.ImageSearchRecyclerAdapter
import com.yusuf.photogallery.databinding.FragmentImageSearchBinding
import com.yusuf.photogallery.util.Status
import com.yusuf.photogallery.viewmodel.ImageViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class ImageSearch @Inject constructor(
    private val imageSearchRecyclerAdapter: ImageSearchRecyclerAdapter
): Fragment(R.layout.fragment_image_search) {


    private  var fragmentBinding: FragmentImageSearchBinding? = null
    lateinit var viewModel: ImageViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity())[ImageViewModel::class.java]

        val binding = FragmentImageSearchBinding.bind(view)
        fragmentBinding=binding

        var job: Job? = null

        binding.searchImageText.addTextChangedListener {
            job?.cancel()
            job = lifecycleScope.launch {
                delay(1000)
                it?.let {
                    if (it.toString().isNotEmpty()) {
                        viewModel.searchForImage(it.toString())
                    }
                }
            }
        }

        subscribeToObservers()

        binding.searchImageRecyclerView.adapter = imageSearchRecyclerAdapter
        binding.searchImageRecyclerView.layoutManager = GridLayoutManager(requireContext(),3)
        imageSearchRecyclerAdapter.setOnItemClickListener {
            findNavController().popBackStack()
            viewModel.setSelectedImage(it)
        }




    }


    private fun subscribeToObservers(){
        viewModel.imagesList.observe(viewLifecycleOwner, Observer {
            when(it.status){
                Status.SUCCESS ->{
                    val urls = it.data?.hits?.map { imageResult -> imageResult.previewURL }
                    imageSearchRecyclerAdapter.imagesUrl=urls ?: listOf()
                    fragmentBinding?.progressBar?.visibility = View.GONE
                }
                Status.ERROR ->{
                    Toast.makeText(requireContext(),it.message ?: "Something Went Wrong!",Toast.LENGTH_LONG).show()
                    fragmentBinding?.progressBar?.visibility = View.GONE
                }
                Status.LOADING ->{
                    fragmentBinding?.progressBar?.visibility = View.VISIBLE
            }


            }
        })
    }


    override fun onDestroyView() {
        fragmentBinding = null
        super.onDestroyView()
    }
}