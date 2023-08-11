package com.yusuf.photogallery.viewmodel

import androidx.lifecycle.ViewModelProvider
import com.yusuf.photogallery.api.RetrofitApi
import com.yusuf.photogallery.repo.FakeImageRepository
import com.yusuf.photogallery.repo.ImageRepository
import com.yusuf.photogallery.repo.ImageRepositoryInterface
import com.yusuf.photogallery.roomdb.ImageDao
import org.junit.Before

class ImageViewModelTest {

    private lateinit var viewModel: ImageViewModel

    /*
    private lateinit var imageDao :  ImageDao
    private lateinit var retrofitApi: RetrofitApi
    private var imageRepository = ImageRepository(imageDao,retrofitApi)
    */

    @Before
    fun setUp(){
        // viewModel = ImageViewModel()  Test ederken normal kullandığımız repoyu değil, kendi oluşturduğumuz fake repoyu test ederiz
        // Bu kullanımdaki amaç retrofiti veya diğer DI'ları değil kendi metodlarımızı test etmektir.

        //viewModel = ImageViewModel(imageRepository)
        viewModel = ImageViewModel(FakeImageRepository())

    }
}