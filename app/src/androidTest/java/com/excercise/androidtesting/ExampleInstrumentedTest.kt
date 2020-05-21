package com.excercise.androidtesting

import android.view.LayoutInflater
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.LayoutAssertions.noOverlaps
import androidx.test.espresso.assertion.PositionAssertions.*
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
@LargeTest
class ExampleInstrumentedTest {

    @get:Rule // will be executed before any function annotated with @Test and @Before, and will be terminated after @After annotated function
    // Espresso needs at least one ActivityTestRule to specify the activity
    var activityTestRule = ActivityTestRule<MainActivity>(MainActivity::class.java)

    @Test
    fun view_isHelloWorldShown() {
        onView(withText("Hello World!")) // withText() is a Matcher to match UI having text "Hello World!" . onView returns object with type of ViewInteraction
            .check(matches(isDisplayed())) // matches accept the view matcher and return view assertion, which can be checked by check method of ViewInteraction
    }

    @Test
    fun view_isRanggaShown() {
        onView(withId(R.id.btn_show))
            .perform(click())

        onView(withText("This is Rangga"))
            .check(matches(isDisplayed()))
    }

    @Test
    fun view_doesWorldNotExist() {
        onView(withText("World!"))
            .check(doesNotExist())
    }

    @Test
    fun view_isBottomAlignedWith() {
        onView(withId(R.id.spinner_test))
            .check(isBottomAlignedWith(
                withId(R.id.btn_show)
            ))
    }

    @Test
    fun view_isCompletelyBelow() {
        onView(withId(R.id.spinner_test))
            .check(isCompletelyBelow(
                withId(R.id.btn_show)
            ))
    }

    @Test
    fun view_isCompletelyLeftOf() {
        onView(withId(R.id.tv_text_left))
            .check(isCompletelyLeftOf(
                withId(R.id.btn_show)
            ))
    }

    @Test
    fun view_noOverlaps() {
        onView(withId(R.id.tv_text_left))
            .check(noOverlaps(
                withId(R.id.btn_show)
            ))

        onView(withId(R.id.tv_text_left))
            .check(noOverlaps())
    }

    @Test
    fun view_hasSibling() {
        onView(withId(R.id.btn_show))
            .check(matches(hasSibling(withId(R.id.tv_text_left))))
    }

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.excercise.androidtesting", appContext.packageName)
    }
}
