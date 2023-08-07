package com.yusuf.photogallery.repo

import androidx.lifecycle.LiveData
import com.yusuf.photogallery.model.ImageResponse
import com.yusuf.photogallery.roomdb.Image
import com.yusuf.photogallery.util.Resource

interface ImageRepositoryInterface {

    suspend fun insertImage(image:Image)

    suspend fun deleteImage(image: Image)

    fun getImage(): LiveData<List<Image>>

    suspend fun searchImage (imageName: String): Resource<ImageResponse>


}