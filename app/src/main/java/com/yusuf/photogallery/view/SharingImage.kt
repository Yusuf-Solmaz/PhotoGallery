package com.yusuf.photogallery.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.RequestManager
import com.yusuf.photogallery.R
import com.yusuf.photogallery.databinding.FragmentSharingImageBinding
import com.yusuf.photogallery.util.Status
import com.yusuf.photogallery.viewmodel.ImageViewModel
import javax.inject.Inject


class SharingImage @Inject constructor(
    private val glide: RequestManager
) : Fragment(R.layout.fragment_sharing_image) {

    private  var fragmentBinding : FragmentSharingImageBinding? = null

    lateinit var viewModel: ImageViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity())[ImageViewModel::class.java]

        val binding = FragmentSharingImageBinding.bind(view)
        fragmentBinding = binding

        subscribeToObservers()




        binding.imageView.setOnClickListener {
            findNavController().navigate(SharingImageDirections.actionSharingImageToImageSearch())
        }
        
        val callback= object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                viewModel.setSelectedImage("")
                findNavController().popBackStack()
            }
        }

        requireActivity().onBackPressedDispatcher.addCallback(callback)

        binding.saveButton.setOnClickListener {
            viewModel.makeImage(binding.titleText.text.toString(),binding.placeText.text.toString(),binding.dateText.text.toString())


        }
    }

    private fun subscribeToObservers(){
        viewModel.selectedImageUrl.observe(viewLifecycleOwner, Observer {url ->
            fragmentBinding?.let {binding ->
                glide.load(url).into(binding.imageView)
            }
        })

        viewModel.insertImageMessage.observe(viewLifecycleOwner, Observer {
            when(it.status){
                Status.SUCCESS -> {
                    Toast.makeText(requireContext(),"Success!",Toast.LENGTH_LONG).show()
                    findNavController().navigateUp()
                    viewModel.resetInsertArtMSG()
                }
                Status.ERROR -> {
                    Toast.makeText(requireContext(),it.message ?: "Failed!",Toast.LENGTH_LONG).show()
                    //viewModel.resetInsertArtMSG()
                }

                Status.LOADING ->{

                }
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        fragmentBinding = null
    }

}