package com.yusuf.photogallery.repo

import androidx.lifecycle.LiveData
import com.yusuf.photogallery.api.RetrofitApi
import com.yusuf.photogallery.model.ImageResponse
import com.yusuf.photogallery.roomdb.Image
import com.yusuf.photogallery.roomdb.ImageDao
import com.yusuf.photogallery.util.Resource
import java.lang.Exception
import javax.inject.Inject

class ImageRepository @Inject constructor(
    private val imageDao: ImageDao,
    private val retrofitApi: RetrofitApi
) : ImageRepositoryInterface {

    override suspend fun insertImage(image: Image) {
        imageDao.insertImage(image)
    }

    override suspend fun deleteImage(image: Image) {
        imageDao.deleteImage(image)
    }

    override fun getImage(): LiveData<List<Image>> {
            return imageDao.observeArts()
    }

    override suspend fun searchImage(imageName: String): Resource<ImageResponse> {
            return try {
                val response = retrofitApi.imageSearch(imageName)
                if (response.isSuccessful) {
                    response.body()?.let {
                        return@let Resource.success(it)
                    } ?: Resource.error("Error", null)
                } else {
                    Resource.error("Error", null)
                }
            }
            catch (e: Exception){
                Resource.error("Something went wrong!",null)
            }
    }
}