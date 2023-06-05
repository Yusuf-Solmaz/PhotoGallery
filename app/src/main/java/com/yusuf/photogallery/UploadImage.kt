package com.yusuf.photogallery

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import com.yusuf.photogallery.databinding.ActivityUploadImageBinding

class UploadImage : AppCompatActivity() {

    lateinit var binding: ActivityUploadImageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUploadImageBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.imageView4.visibility= ImageView.VISIBLE
    }

    fun chooseImage(view: View){

    }

    fun saveImage(view: View){

    }

}