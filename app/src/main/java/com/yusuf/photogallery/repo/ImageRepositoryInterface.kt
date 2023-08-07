package com.yusuf.photogallery.repo

import androidx.lifecycle.LiveData
import com.yusuf.photogallery.model.ImageResponse
import com.yusuf.photogallery.roomdb.Image

interface ImageRepositoryInterface {

    suspend fun insertImage(image:Image)

    suspend fun deleteImage(image: Image)

    fun getArt(): LiveData<List<Image>>

    suspend fun searchImage (imageName: String): ImageResponse


}