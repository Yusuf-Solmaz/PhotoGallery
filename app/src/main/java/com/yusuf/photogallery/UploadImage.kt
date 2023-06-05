package com.yusuf.photogallery

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.yusuf.photogallery.databinding.ActivityUploadImageBinding

class UploadImage : AppCompatActivity() {

    lateinit var binding: ActivityUploadImageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUploadImageBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

    }
}