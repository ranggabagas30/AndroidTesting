package com.excercise.androidtesting

import android.view.View
import android.widget.AdapterView
import android.widget.TextView
import androidx.test.espresso.matcher.BoundedMatcher
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher

object MatcherUtility {
    // assert that a data item is not in the adapter
    fun withAdaptedData(dataMatcher: Matcher<Any>): Matcher<View> {
        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description?) {
                description?.appendText("with class name: ")
                dataMatcher.describeTo(description)
            }

            override fun matchesSafely(item: View?): Boolean {
                if (item !is AdapterView<*>) {
                    return false
                }

                val adapter = item.adapter
                for (i in 0 until adapter.count) {
                    if (dataMatcher.matches(adapter.getItem(i))) {
                        return true
                    }
                }
                return false
            }
        }
    }
}