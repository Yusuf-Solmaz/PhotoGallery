package com.yusuf.photogallery.imageFragmentTest

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.filters.MediumTest
import com.google.common.truth.Truth.assertThat
import com.yusuf.photogallery.getOrAwaitValue
import com.yusuf.photogallery.launchFragmentInHiltContainer
import com.yusuf.photogallery.repo.FakeImageRepository
import com.yusuf.photogallery.view.ImageFragmentFactory
import com.yusuf.photogallery.view.ImageSearch
import com.yusuf.photogallery.view.SharingImage
import com.yusuf.photogallery.viewmodel.ImageViewModel
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import javax.inject.Inject
import com.yusuf.photogallery.R
import com.yusuf.photogallery.adapter.ImageSearchRecyclerAdapter

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

    /*
    @Test
    fun testSelectImage() {
        val navController = Mockito.mock(NavController::class.java)
        val selectedImageUrl = "yusufsolmaz.com"
        val testViewModel = ImageViewModel(FakeImageRepository())

        launchFragmentInHiltContainer<ImageSearch>(
            factory = fragmentFactory,
        ) {
            Navigation.setViewNavController(requireView(),navController)
            imageRecyclerAdapter.images = listOf(selectedImageUrl)
            viewModel = testViewModel
        }

        Espresso.onView(ViewMatchers.withId(R.id.imageView)).perform(
            RecyclerViewActions.actionOnItemAtPosition<ImageSearchRecyclerAdapter.ImageSearchHolder>(
                0,click()
            )

        )

        Mockito.verify(navController).popBackStack()

        assertThat(testViewModel.selectedImageUrl.getOrAwaitValue()).isEqualTo(selectedImageUrl)

    }
    */
}