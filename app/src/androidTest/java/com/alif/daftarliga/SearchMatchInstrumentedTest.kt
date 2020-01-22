package com.alif.daftarliga

import android.view.KeyEvent
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.alif.daftarliga.R.id.*
import com.alif.daftarliga.view.MainActivity
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers
import org.hamcrest.TypeSafeMatcher
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
                withContentDescription(R.string.content_desc_search),
                childAtPosition(
                    Matchers.allOf(
                        withClassName(Matchers.`is`("android.widget.LinearLayout")),
                        childAtPosition(
                            withId(search_view_match),
                            0
                        )
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        appCompatImageView.perform(click())

        onView(withId(search_view_match))
            .perform(typeText("Liverpool"))
            .perform(pressKey(KeyEvent.KEYCODE_ENTER))
        onView(withId(rv_searched_matches))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(8))
            .perform(click())

        val appCompatImageButton = onView(
            Matchers.allOf(
                withContentDescription(R.string.content_desc_navigate_up),
                childAtPosition(
                    Matchers.allOf(
                        withId(action_bar),
                        childAtPosition(
                            withId(action_bar_container),
                            0
                        )
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        appCompatImageButton.perform(click())
    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}