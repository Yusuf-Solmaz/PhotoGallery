package com.yusuf.photogallery.imageFragmentTest

import androidx.fragment.app.FragmentFactory
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.filters.MediumTest
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
import com.yusuf.photogallery.view.SharingImageDirections

@MediumTest
@HiltAndroidTest
class ImageSharingFragmentTest {

    @get:Rule
    val hiltAndroidRule= HiltAndroidRule(this)

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
}