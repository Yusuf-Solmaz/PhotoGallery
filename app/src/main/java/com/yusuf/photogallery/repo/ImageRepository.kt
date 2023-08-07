package com.yusuf.photogallery.repo

import androidx.lifecycle.LiveData
import com.yusuf.photogallery.model.ImageResponse
import com.yusuf.photogallery.roomdb.Image

class ImageRepository : ImageRepositoryInterface {
    override suspend fun insertImage(image: Image) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteImage(image: Image) {
        TODO("Not yet implemented")
    }

    override fun getArt(): LiveData<List<Image>> {
        TODO("Not yet implemented")
    }

    override suspend fun searchImage(imageName: String): ImageResponse {
        TODO("Not yet implemented")
    }
}