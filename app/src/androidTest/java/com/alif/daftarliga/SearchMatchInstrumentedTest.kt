package com.alif.daftarliga

import android.view.KeyEvent
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.alif.daftarliga.R.id.*
import com.alif.daftarliga.view.MainActivity
import org.hamcrest.Matchers
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SearchMatchInstrumentedTest {
    @Rule
    @JvmField
    val activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun testSearchMatch() {
        onView(withId(main_tabs))
        onView(withText("Search")).perform(click())
        onView(withId(search_view_match))
        val appCompatImageView = onView(
            Matchers.allOf(
                withClassName(Matchers.`is`("androidx.appcompat.widget.AppCompatImageView")),
                withContentDescription("Search")
            )
        )
        appCompatImageView.perform(click())
        onView(withId(search_view_match))
            .perform(typeText("Liverpool"))
            .perform(pressKey(KeyEvent.KEYCODE_ENTER))
        onView(withId(rv_searched_matches))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(8))
            .perform(click())
        onView(withContentDescription("Navigate up")).perform(click())
    }
}