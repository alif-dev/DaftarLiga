package com.alif.daftarliga

import android.view.KeyEvent
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import androidx.test.uiautomator.UiDevice
import com.alif.daftarliga.R.id.*
import com.alif.daftarliga.utilities.EspressoIdlingResource
import com.alif.daftarliga.view.MainActivity
import org.hamcrest.Matchers
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class SearchMatchInstrumentedTest {
    @Rule
    @JvmField
    val activityRule = ActivityTestRule(MainActivity::class.java)

    @Before
    fun registerIdlingResource() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
    }

    @Test
    fun testSearchMatch() {
        onView(withId(main_tabs)).check(matches(isDisplayed()))
        onView(withText("Search")).perform(click())
        onView(withId(search_view_match))
        val appCompatImageView = onView(
            Matchers.allOf(
                withClassName(Matchers.`is`("androidx.appcompat.widget.AppCompatImageView")),
                withContentDescription(R.string.content_desc_search)
            )
        )
        appCompatImageView.perform(click())
        onView(withId(search_view_match))
            .perform(typeText("Liverpool"))
            .perform(pressKey(KeyEvent.KEYCODE_ENTER))
        onView(withId(rv_searched_matches))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(8))
        onView(withId(rv_searched_matches))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(8, click()))
        onView(withId(action_favorite_match)).check(matches(isDisplayed()))
        onView(withId(action_favorite_match)).perform(click())
        val device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
        device.pressBack()
        onView(withId(main_tabs)).check(matches(isDisplayed()))
        onView(withText("Favorites")).perform(click())
    }

    @After
    fun unregisterIdlingResource() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)
    }
}