package com.yusuf.photogallery.roomdb

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.yusuf.photogallery.getOrAwaitValue
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named

@SmallTest
@ExperimentalCoroutinesApi
@HiltAndroidTest
class ImageDaoTest {

    @get: Rule
    var ınstantTaskExecutorRule= InstantTaskExecutorRule()

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    @Named("testDatabase")
    lateinit var database: ImagesDatabase

    private lateinit var imageDao: ImageDao


    @Before
    fun setup(){

        /*
        database= Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            ImagesDatabase::class.java).allowMainThreadQueries().build()
*/
        hiltRule.inject()

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