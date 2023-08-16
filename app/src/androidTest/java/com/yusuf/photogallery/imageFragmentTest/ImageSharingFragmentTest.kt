package com.yusuf.photogallery.imageFragmentTest

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.fragment.app.FragmentFactory
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.MediumTest
import com.google.common.truth.Truth.assertThat
import com.yusuf.photogallery.launchFragmentInHiltContainer
import com.yusuf.photogallery.view.ImageFragmentFactory
import com.yusuf.photogallery.view.SharingImage
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import javax.inject.Inject
import com.yusuf.photogallery.R
import com.yusuf.photogallery.getOrAwaitValue
import com.yusuf.photogallery.repo.FakeImageRepository
import com.yusuf.photogallery.roomdb.Image
import com.yusuf.photogallery.view.SharingImageDirections
import com.yusuf.photogallery.viewmodel.ImageViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi

@MediumTest
@HiltAndroidTest
@ExperimentalCoroutinesApi
class ImageSharingFragmentTest {

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

    @Test
    fun testImageSharingFragmentToImageSearchFragment(){

        val navController= Mockito.mock(NavController::class.java)

        launchFragmentInHiltContainer<SharingImage>(
            factory =  fragmentFactory
        ){
            Navigation.setViewNavController(requireView(),navController)
        }
        Espresso.onView(ViewMatchers.withId(R.id.imageView)).perform(ViewActions.click())

        Mockito.verify(navController).navigate(
            SharingImageDirections.actionSharingImageToImageSearch())
    }

    @Test
    fun testOnBackPressed(){
        val navController= Mockito.mock(NavController::class.java)

        launchFragmentInHiltContainer<SharingImage>(
            factory =  fragmentFactory
        ){
            Navigation.setViewNavController(requireView(),navController)
        }

        pressBack()
        Mockito.verify(navController).popBackStack()
    }

     @Test
    fun testSave() {
         val testViewModel = ImageViewModel(FakeImageRepository())
         launchFragmentInHiltContainer<SharingImage>(
             factory = fragmentFactory
         ) {
            viewModel = testViewModel
        }

        onView(withId(R.id.imageName)).perform(replaceText("Mona Lisa"))
        onView(withId(R.id.placeText)).perform(replaceText("Da Vinci"))
        onView(withId(R.id.dateText)).perform(replaceText("1700"))
        onView(withId(R.id.saveButton)).perform(click())

        assertThat(testViewModel.imageList.getOrAwaitValue()).contains(
            Image(
                "Mona Lisa",
                "Da Vinci",
                "1700","")
        )

    }
}