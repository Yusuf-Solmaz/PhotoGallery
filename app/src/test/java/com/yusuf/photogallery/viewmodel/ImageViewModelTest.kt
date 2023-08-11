package com.yusuf.photogallery.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.yusuf.photogallery.MainCoroutineRule
import com.yusuf.photogallery.getOrAwaitValueTest
import com.yusuf.photogallery.repo.FakeImageRepository
import com.google.common.truth.Truth.assertThat
import com.yusuf.photogallery.util.Status

import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ImageViewModelTest {


    /*
    private lateinit var imageDao :  ImageDao
    private lateinit var retrofitApi: RetrofitApi
    private var imageRepository = ImageRepository(imageDao,retrofitApi)
    */
    /*

    Main thread'de işlem yapak için yapıyoruz.
     */
    @get: Rule
    var ınstantTaskExecutorRule= InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel: ImageViewModel

    @Before
    fun setUp(){
        // viewModel = ImageViewModel()  Test ederken normal kullandığımız repoyu değil, kendi oluşturduğumuz fake repoyu test ederiz
        // Bu kullanımdaki amaç retrofiti veya diğer DI'ları değil kendi metodlarımızı test etmektir.

        //viewModel = ImageViewModel(imageRepository)
        viewModel = ImageViewModel(FakeImageRepository())
    }


    @Test
    fun `insert image without date return error` (){
        viewModel.makeImage("Test Title","Test Place","")
        val value = viewModel.insertImageMessage.getOrAwaitValueTest()  // value artık LiveData değil Resoource<Image> oldu.
        assertThat(value.status).isEqualTo(Status.ERROR)
    }

    @Test
    fun `insert image without title return error` (){
        viewModel.makeImage("","Test Place","Test Date")
        val value = viewModel.insertImageMessage.getOrAwaitValueTest()
        assertThat(value.status).isEqualTo(Status.ERROR)
    }

    @Test
    fun `insert image without place return error` (){
        viewModel.makeImage("Test Title","","Test Date")
        val value = viewModel.insertImageMessage.getOrAwaitValueTest()
        assertThat(value.status).isEqualTo(Status.ERROR)
    }
}