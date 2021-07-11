package com.excercise.androidtesting

import android.util.Log
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder

/**
 * In JUnit, the methods annotated with different annotation will be executed in specific order as shown below,
 *
    @BeforeClass

    @Rule

    @Before

    @Test

    @After

    @AfterClass
 * */
class ComputationUnitTest {

    var computation: Computation? = null

    @Rule @JvmField
    var folder = TemporaryFolder()

    @Test
    fun file_isCreated() {
        folder.newFolder("MyTestFolder")
        val fileTest = folder.newFile("MyTestFile.txt")
        assertTrue(fileTest.exists())
    }

    @Test // jUnit function to specify that the function is jUnit test case
    fun sum_isCorrect() {
        assertEquals(4, computation?.sum(2, 2))
    }

    @Test
    fun multiply_isCorrect() {
        assertEquals(10, computation?.multiply(2, 5))
    }

    @Before // execute before any test cases
    fun createComputationObject() {
        //Log.d("create computation", "create computation object")
        computation = Computation()
    }

    @After // after all test cases are executed
    fun destroyComputationObject() {
        computation = null
    }
}