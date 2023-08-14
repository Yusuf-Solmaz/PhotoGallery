package com.yusuf.photogallery.imageFragmentTest

import androidx.fragment.app.FragmentFactory
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.filters.MediumTest
import com.yusuf.photogallery.launchFragmentInHiltContainer
import com.yusuf.photogallery.view.FeedFragment
import com.yusuf.photogallery.view.FeedFragment_Factory
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import javax.inject.Inject
import com.yusuf.photogallery.R
import com.yusuf.photogallery.view.FeedFragmentDirections
import com.yusuf.photogallery.view.ImageFragmentFactory
import com.yusuf.photogallery.view.SharingImage

@MediumTest
@HiltAndroidTest
class FeedFragmentTest {

    @get:Rule
    var hiltAndroidRule = HiltAndroidRule(this)

    @Inject
    lateinit var fragmentFactory: ImageFragmentFactory

    @Before
    fun setup() {
        hiltAndroidRule.inject()
    }

    @Test
    fun testNavigationFromArtToArtDetails() {
        val navController = Mockito.mock(NavController::class.java)

        launchFragmentInHiltContainer<FeedFragment>(
            factory = fragmentFactory
        ) {
            Navigation.setViewNavController(requireView(),navController)
        }

        Espresso.onView(ViewMatchers.withId(R.id.fab)).perform(ViewActions.click())
        Mockito.verify(navController).navigate(
            FeedFragmentDirections.actionFeedFragmentToSharingImage()
        )
    }
}