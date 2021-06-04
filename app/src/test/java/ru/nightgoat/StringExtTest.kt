package ru.nightgoat

import junit.framework.Assert.assertEquals
import orIfEmpty
import org.junit.Assert
import org.junit.Test
import toDoubleOrZero

class StringExtTest {

    val digit = "123"
    val empty = ""
    val notEmpty = "foo"
    var _null: String? = null

    @Test
    fun orIfEmpty_test_1() {
        Assert.assertEquals(notEmpty, empty.orIfEmpty { "foo" })
    }

    @Test
    fun orIfEmpty_test_2() {
        Assert.assertEquals(notEmpty, notEmpty.orIfEmpty { "bar" })
    }

    @Test
    fun toDoubleOrZero_test_1() {
        assertEquals(123.0, digit.toDoubleOrZero())
    }

    @Test
    fun toDoubleOrZero_test_2() {
        assertEquals(0.0, notEmpty.toDoubleOrZero())
    }
}