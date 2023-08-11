package com.yusuf.photogallery.roomdb

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.yusuf.photogallery.getOrAwaitValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@SmallTest
@ExperimentalCoroutinesApi
class ImageDaoTest {

    @get: Rule
    var ınstantTaskExecutorRule= InstantTaskExecutorRule()

    private lateinit var imageDao: ImageDao
    private lateinit var database: ImagesDatabase

    @Before
    fun setup(){

        database= Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            ImagesDatabase::class.java).allowMainThreadQueries().build()

        imageDao = database.imageDao()

    }

    @After
    fun teardown(){
        database.close()
    }

    @Test
    fun insertImageTest() = runBlockingTest {
        val exampleImage = Image("Test Title","Test Place", "Test Date","testUrl.com",1)
        imageDao.insertImage(exampleImage)

        val list = imageDao.observeArts().getOrAwaitValue()
        assertThat(list).contains(exampleImage)
    }

    @Test
     fun deleteImageTest() = runBlockingTest {
        val exampleImage = Image("Test Title","Test Place", "Test Date","testUrl.com",1)
        imageDao.insertImage(exampleImage)
        imageDao.deleteImage(exampleImage)

        val list = imageDao.observeArts().getOrAwaitValue()

        assertThat(list).doesNotContain(exampleImage)
    }

}