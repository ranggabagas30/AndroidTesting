package com.excercise.androidtesting

import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import androidx.test.rule.ActivityTestRule
import org.hamcrest.Matchers.*
import org.hamcrest.core.Is.`is`
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SmallTest
class SpinnerInstrumentedTest {

    @get:Rule
    val activityTestRule = ActivityTestRule<MainActivity>(MainActivity::class.java)

    @Test
    fun spinner_isResultCorrect() {
        onView(withId(R.id.spinner_test)) // open the item selection
            .perform(click())

        // by using onData, we force out desired element into view hierarchy of spinner's ListView
        onData(allOf(`is`(instanceOf(String::class.java)),
            `is`("Red Velvet"))).perform(click())

        onView(withId(R.id.tv_text))
            .check(matches(withText(containsString("Red Velvet"))))
    }

//    @Test
//    fun spinner_testDataItemNotInAdapter() {
//        onView(withId(R.id.spinner_test))
//            .check(matches(not(MatcherUtility.withAdaptedData())))
//    }
}