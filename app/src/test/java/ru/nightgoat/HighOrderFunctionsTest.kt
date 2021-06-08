package ru.nightgoat

import org.junit.Assert
import org.junit.Test
import tryOrDefault
import tryOrEmpty
import tryOrNow
import tryOrNull
import java.lang.NullPointerException
import java.util.*

class HighOrderFunctionsTest {

    @Test
    fun tryOrDefault_test_1() {
        val foo = tryOrDefault(1) {
            throw NullPointerException()
        }
        Assert.assertEquals(1, foo)
    }

    @Test
    fun tryOrDefault_test_2() {
        val foo = tryOrDefault(1) {
            2
        }
        Assert.assertEquals(2, foo)
    }

    @Test
    fun tryOrNull_test_1() {
        val foo = tryOrNull {
            throw NullPointerException()
        }
        Assert.assertNull(foo)
    }

    @Test
    fun tryOrNull_test_2() {
        val foo = tryOrNull {
            1
        }
        Assert.assertEquals(1, foo)
    }

    @Test
    fun tryOrEmpty_test_1() {
        val foo = tryOrEmpty {
            throw NullPointerException()
        }
        Assert.assertEquals("", foo)
    }

    @Test
    fun tryOrEmpty_test_2() {
        val foo = tryOrEmpty {
            "bar"
        }
        Assert.assertEquals("bar", foo)
    }

    @Test
    fun tryOrNow_test_1() {
        var now: Date? = Date()
        val foo = tryOrNow {
            now = Date()
            throw NullPointerException()
        }
        Assert.assertEquals(now, foo)
    }

    @Test
    fun tryOrNow_test_2() {
        val foo = tryOrNow {
            Date(1)
        }
        Assert.assertEquals(Date(1), foo)
    }
}