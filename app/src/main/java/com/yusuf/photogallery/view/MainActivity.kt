package com.yusuf.photogallery.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.yusuf.photogallery.R
import com.yusuf.photogallery.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

    }
}