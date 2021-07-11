package com.excercise.androidtesting

import androidx.annotation.IntegerRes
import com.nhaarman.mockito_kotlin.*
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.*
import org.mockito.ArgumentCaptor.forClass
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner
import java.util.*
import kotlin.collections.ArrayList

// Enable mockito annotations using RunWith
@RunWith(MockitoJUnitRunner::class)
class ExampleMockitoTest {

    @Before
    fun setup() {

        // Enable mockito annotations using initMocks()
        //MockitoAnnotations.initMocks(this)
    }

    // example mock the real object
    // Once created, a mock will remember all interactions.
    // Then you can selectively verify whatever interactions you are interested in.
    @Test
    fun mockRealObjectExample() {
        val list = mock<ArrayList<Any>>()
        list.add("One")
        list.clear()

        // verification
        verify(list).add("One")
        verify(list).clear()
    }

    // When using an instance of mock object, then will ignore the working of method calls
    // for example here calling on add() method will not add element to the list (ignored)
    // using mock()
    @Test
    fun whenNotUseMockAnnotation_thenCorrect() {
        var list = mock<ArrayList<Any>>()
        list.add("one")
        list.add("two")
        println("size: ${list.size}")
        verify(list).add("one")
        Assert.assertEquals(0, list.size)

        // Stub
        whenever(list.size).thenReturn(100)
        Assert.assertEquals(100, list.size)

        list = mock<ArrayList<Any>> {
            // Using kotlin stubbing
            on { size }.doReturn(100)
        }
        list.add("one")
        Assert.assertEquals(100, list.size)
    }

    @Mock
    lateinit var mockedList: ArrayList<String>

    // using @Mock
    @Test
    fun whenUserMockAnnotation_thenMockIsInjected() {
        mockedList.add("One")
        verify(mockedList).add("One")
        Assert.assertEquals(0, mockedList.size)

        whenever(mockedList.size).thenReturn(100)
        Assert.assertEquals(100, mockedList.size)
    }

    // using spy()
    @Test
    fun whenNotUseSpyAnnotation_thenCorrect() {
        val spyList: ArrayList<String> = spy()
        spyList.add("one")
        spyList.add("two")

        // using real method
        verify(spyList).add("one")
        verify(spyList).add("two")

        Assert.assertEquals(2, spyList.size)

        // Stubbed the method to return 100, instead of 2
        doReturn(100).whenever(spyList).size
        Assert.assertEquals(100, spyList.size)
    }

    // using @Spy
    @Spy lateinit var spiedList: ArrayList<String>
    @Test
    fun whenUseSpyAnnotation_thenSpyIsInjectedCorrectly() {
        val spiedList: ArrayList<String> = spy()
        spiedList.add("one")
        spiedList.add("two")

        verify(spiedList).add("one")
        verify(spiedList).add("two")

        Assert.assertEquals(2, spiedList.size)
        doReturn(100).whenever(spiedList).size

        Assert.assertEquals(100, spiedList.size)
    }

    // using ArgumentCaptor.forClass
    @Test
    fun whenNotUseCaptorAnnotation_thenCorrect() {
        val mockedList: ArrayList<String> = mock()
        val arg: ArgumentCaptor<String> = forClass(String::class.java)
        mockedList.add("one")
        verify(mockedList).add(arg.capture())

        Assert.assertEquals("one", arg.value)
    }

    // using @Captor
    @Mock
    lateinit var mockedStringList: ArrayList<String>
    @Captor
    lateinit var argCaptor: ArgumentCaptor<String>
    @Test
    fun whenUseCaptorAnnotation_thenTheSam() {
        mockedStringList.add("one")
        verify(mockedStringList ).add(argCaptor.capture())

        Assert.assertEquals("one", argCaptor.value)
    }

    // using @InjectMock
    @Mock
    lateinit var injectClass1: InjectClass1
    @Mock
    lateinit var injectClass2: InjectClass2
    @InjectMocks
    lateinit var simpleClassUnderTest: SimpleClassUnderTest
    @Test
    fun whenUseInjectMockAnnotation_thenVerifyInjectClass2AddListener() {
        simpleClassUnderTest.initialize()
        verify(injectClass2).addListener(any())
    }

    @Test
    fun testMoreThanOneReturnValue() {
        val i: Iterator<String> = mock()
        whenever(i.next()).thenReturn("Mockito").thenReturn("rocks")
        val result = i.next() + " " + i.next()
        Assert.assertEquals("Mockito rocks", result)
    }

    @Test
    fun testReturnValueDepenedentOnMethodParameter() {
        val c: Comparable<String> = mock()
        whenever(c.compareTo("Mockito")).thenReturn(1)
        whenever(c.compareTo("Eclipse")).thenReturn(2)
        Assert.assertEquals(1, c.compareTo("Mockito"))
    }

    @Test
    fun testReturnValueIndependentOnMethodParameter() {
        val c: Comparable<Int> = mock()
        whenever(c.compareTo(ArgumentMatchers.anyInt())).thenReturn(-1)
        Assert.assertEquals(-1, c.compareTo(9))
    }

    @Test
    fun testReturnValueIndependentOnMethodParamter2() {
        val c: Comparable<Int> = mock()
        whenever(c.compareTo(isA())).thenReturn(0)
    }

    @Test
    fun usingDoReturnWhen() {
        val properties: Properties = mock()
        doReturn(42).whenever(properties).getShowSize()
        val value = properties.getShowSize()
        Assert.assertEquals(42, value)
    }

    @Test
    fun usingSpy_testLinkedListSpyWrong() {
        val list = LinkedList<String>()
        val spy = spy(list)

        // throws IndexOutOfBoundsException (list is still empty)
        whenever(spy[0]).thenReturn("foo")

        Assert.assertEquals("foo", spy[0])
    }

    @Test
    fun usingSpy_testLinkedListSpyCorrect() {
        val list = LinkedList<String>()
        val spy = spy(list)

        // using doReturn for stubbing
        doReturn("foo").whenever(spy)[0]

        Assert.assertEquals("foo", spy[0])
    }

    // testing behavioural
    // to verify that the method on the mock object has been called
    // with certain parameter
    @Test
    fun testVerify() {
        // create and configure mock
        val properties = mock<Properties>()
        whenever(properties.getShowSize()).thenReturn(43)

        // call method setShowSize on the mock with parameter 12
        properties.setShowSize(12)
        properties.getShowSize()
        properties.getShowSize()

        // check if method setShowSize was called with the parameter 12
        verify(properties).setShowSize(eq(12))

        // was the method called twice
        verify(properties, times(2)).getShowSize()

        // other alternatives for verifying the number of method calls for a method
//        verify(
//            properties,
//            never()
//        ).setShowSize(12) // give wrong result, because it is actually called once with param 12
        verify(
            properties,
            never()
        ).setShowSize(1) // give right result since never called this method with param 1
        //verify(properties, never()).setShowSize(anyInt())
        verify(properties, atLeastOnce()).getShowSize()
        verify(properties, atLeast(2)).getShowSize()
        verify(properties, atMost(3)).getShowSize()

        // This let;s your check that no other methods where called on this object
        // You called it after you have verified the expected method calls
        verifyNoMoreInteractions(properties)
    }

    // test if-then conditionals
    @Test
    fun testConditionals_whenTrue() {
        // SUT
        val injectClass1: InjectClass1 = mock()
        val injectClass2: InjectClass2 = mock()

        // Given
        whenever(injectClass1.check(100)).thenReturn(true)

        val simpleClassUnderTest = SimpleClassUnderTest(injectClass1, injectClass2)
        simpleClassUnderTest.check(100, 1)

        verify(injectClass1).saveValues(eq(100), eq(1))
    }

    @Test(expected = IllegalStateException::class)
    fun testConditionals_whenFalse() {
        // SUT
        val injectClass1: InjectClass1 = mock()
        val injectClass2: InjectClass2 = mock()

        whenever(injectClass1.check(100)).thenReturn(false)
        val simpleClassUnderTest = SimpleClassUnderTest(injectClass1, injectClass2)
        simpleClassUnderTest.check(100, 1)
        // till here, we expected that it will throw an exception
    }

    // using Answer
    @Test
    fun testAnswer() {
        val injectClass1: InjectClass1 = mock()
        val injectClass2: InjectClass2 = mock()
        val simpleClassUnderTest = SimpleClassUnderTest(injectClass1, injectClass2)

        // stub injectClass2.addListener to return onSimpleClass
        val firstArgument = 100
        val secondArgument = "tada"
        doAnswer {
            val callback: SimpleClassUnderTest.SimpleClassCallback = it.getArgument(0)
            callback.onSimpleClass(firstArgument, secondArgument)
        }.whenever(injectClass2).addListener(any())

        // call the method
        simpleClassUnderTest.initialize()

        // verify it is called with equal argument
        verify(injectClass1).check(eq(firstArgument))
    }
}
