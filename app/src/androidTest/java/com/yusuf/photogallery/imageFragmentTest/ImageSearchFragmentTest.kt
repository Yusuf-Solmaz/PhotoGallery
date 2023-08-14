package com.yusuf.photogallery.imageFragmentTest

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso
import androidx.test.filters.MediumTest
import com.yusuf.photogallery.launchFragmentInHiltContainer
import com.yusuf.photogallery.view.ImageFragmentFactory
import com.yusuf.photogallery.view.ImageSearch
import com.yusuf.photogallery.view.SharingImage
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import javax.inject.Inject

@MediumTest
@HiltAndroidTest
@ExperimentalCoroutinesApi
class ImageSearchFragmentTest {


    @get:Rule
    val hiltAndroidRule= HiltAndroidRule(this)

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Inject
    lateinit var fragmentFactory: ImageFragmentFactory

    @Before
    fun setup(){
        hiltAndroidRule.inject()
    }

}