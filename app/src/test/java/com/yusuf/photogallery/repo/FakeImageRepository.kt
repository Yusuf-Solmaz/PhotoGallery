package com.yusuf.photogallery.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.yusuf.photogallery.model.ImageResponse
import com.yusuf.photogallery.roomdb.Image
import com.yusuf.photogallery.util.Resource

class FakeImageRepository : ImageRepositoryInterface {

    private val images = mutableListOf<Image>()
    private val imagesLiveData = MutableLiveData<List<Image>>(images)

    override suspend fun insertImage(image: Image) {
        images.add(image)
        refreshData()
    }

    override suspend fun deleteImage(image: Image) {
       images.remove(image)
        refreshData()
    }

    override fun getImage(): LiveData<List<Image>> {
        return imagesLiveData
    }

    override suspend fun searchImage(imageName: String): Resource<ImageResponse> {
        return Resource.success(ImageResponse(listOf(),0,0))
    }

    private fun refreshData(){
        imagesLiveData.postValue(images)
    }
}