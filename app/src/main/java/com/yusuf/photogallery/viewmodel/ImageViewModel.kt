package com.yusuf.photogallery.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yusuf.photogallery.model.ImageResponse
import com.yusuf.photogallery.repo.ImageRepositoryInterface
import com.yusuf.photogallery.roomdb.Image
import com.yusuf.photogallery.util.Resource
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

class ImageViewModel @Inject constructor(
    private val repository: ImageRepositoryInterface
) : ViewModel() {

    // Feed Fragment
    val imageList = repository.getImage()

    //
    private val images = MutableLiveData<Resource<ImageResponse>>()
    val imagesList: LiveData<Resource<ImageResponse>>
        get() = images


    private val selectedImage = MutableLiveData<String>()
    val selectedImageUrl : LiveData<String>
        get() = selectedImage

    // Sharing Image Fragment
    private var insertImageMsg = MutableLiveData<Resource<Image>>()
    val insertImageMessage: LiveData<Resource<Image>>
        get() = insertImageMsg

    fun resetInsertArtMSG(){
        insertImageMsg = MutableLiveData<Resource<Image>>()
    }

    fun setSelectedImage(image: String){
        selectedImage.postValue(image)
    }

    fun deleteImage(image: Image) = viewModelScope.launch {
        repository.deleteImage(image)
    }

    fun insertImage(image: Image) = viewModelScope.launch {
        repository.insertImage(image)
    }

    fun searchForImage(searchString: String){
        if (searchString.isEmpty()){
            return
        }

        images.value = Resource.loading(null)
        viewModelScope.launch {
            val response = repository.searchImage(searchString)
            images.value = response
        }
    }

    fun makeImage(title: String , place: String , date: String){
        if (title.isEmpty() || place.isEmpty() || date.isEmpty()){
            insertImageMsg.postValue(Resource.error("Fields cannot be left blank",null))
            return
        }

        val dateInt = try {
            date.toInt()
        }
        catch (e: Exception){
            insertImageMsg.postValue(Resource.error("Year should be number!",null))
            return
        }
        val image = Image(title,place,date,selectedImage.value ?:"")
        insertImage(image)
        setSelectedImage("")
        insertImageMsg.postValue(Resource.success(image))
    }

}



























