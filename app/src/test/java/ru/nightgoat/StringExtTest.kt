package ru.nightgoat

import enumValueOrDefault
import enumValueOrNull
import isEmail
import isIPAddress
import isPhone
import isWhiteSpace
import normalize
import orIfEmpty
import orZero
import org.junit.Assert
import org.junit.Test
import takeIfEmpty
import takeIfNotEmpty
import toDoubleOrDefault
import toDoubleOrZero
import toIntOrDefault
import toIntOrZero
import toLongOrDefault
import toLongOrZero
import trimZeros

class StringExtTest {

    private val digitString = "42"
    private val emptyString = ""
    private val notEmptyString = "foo"
    private var _null: String? = null

    private enum class Foo {
        BAR,
        FAR
    }

    @Test
    fun orIfEmpty_test_1() {
        Assert.assertEquals(notEmptyString, emptyString.orIfEmpty { "foo" })
    }

    @Test
    fun orIfEmpty_test_2() {
        Assert.assertEquals(notEmptyString, notEmptyString.orIfEmpty { "bar" })
    }

    @Test
    fun toDoubleOrZero_test_1() {
        Assert.assertEquals(42.0, digitString.toDoubleOrZero(), 0.0)
    }

    @Test
    fun toDoubleOrZero_test_2() {
        Assert.assertEquals(0.0, notEmptyString.toDoubleOrZero(), 0.0)
    }

    @Test
    fun toDoubleOrDefault_test_1() {
        Assert.assertEquals(123.0, notEmptyString.toDoubleOrDefault(123.0), 0.0)
    }

    @Test
    fun toDoubleOrDefault_test_2() {
        Assert.assertEquals(42.0, digitString.toDoubleOrDefault(123.0), 0.0)
    }

    @Test
    fun toIntOrZero_test_1() {
        Assert.assertEquals(42, digitString.toIntOrZero())
    }

    @Test
    fun toIntOrZero_test_2() {
        Assert.assertEquals(0, notEmptyString.toIntOrZero())
    }

    @Test
    fun toIntOrDefault_test_1() {
        Assert.assertEquals(123, notEmptyString.toIntOrDefault(123))
    }

    @Test
    fun toIntOrDefault_test_2() {
        Assert.assertEquals(42, digitString.toIntOrDefault(123))
    }

    @Test
    fun toLongOrZero_test_1() {
        Assert.assertEquals(42L, digitString.toLongOrZero())
    }

    @Test
    fun toLongOrZero_test_2() {
        Assert.assertEquals(0L, notEmptyString.toLongOrZero())
    }

    @Test
    fun toLongOrDefault_test_1() {
        Assert.assertEquals(123L, notEmptyString.toLongOrDefault(123L))
    }

    @Test
    fun toLongOrDefault_test_2() {
        Assert.assertEquals(42L, digitString.toLongOrDefault(123L))
    }

    @Test
    fun trimZeros_test_1() {
        Assert.assertEquals("42", "00042".trimZeros())
    }

    @Test
    fun trimZeros_test_2() {
        Assert.assertEquals("42", "42".trimZeros())
    }

    @Test
    fun orZero_test_1() {
        Assert.assertEquals("0", _null.orZero())
    }

    @Test
    fun orZero_test_2() {
        Assert.assertEquals(notEmptyString, notEmptyString.orZero())
    }

    @Test
    fun takeIfNotEmpty_test_1() {
        Assert.assertNull(emptyString.takeIfNotEmpty())
    }

    @Test
    fun takeIfNotEmpty_test_2() {
        Assert.assertNotNull(notEmptyString.takeIfNotEmpty())
    }

    @Test
    fun takeIfEmpty_test_1() {
        Assert.assertNull(notEmptyString.takeIfEmpty())
    }

    @Test
    fun takeIfEmpty_test_2() {
        Assert.assertNotNull(emptyString.takeIfEmpty())
    }

    @Test
    fun isWhiteSpace_test_1() {
        Assert.assertEquals(true, emptyString.isWhiteSpace())
    }

    @Test
    fun isWhiteSpace_test_2() {
        Assert.assertEquals(false, notEmptyString.isWhiteSpace())
    }

    @Test
    fun isWhiteSpace_test_3() {
        Assert.assertEquals(true, " ".isWhiteSpace())
    }

    @Test
    fun normalize_test_1() {
        Assert.assertEquals("Hello world!", "HellO wOrld!".normalize())
    }

    @Test
    fun enumValueOrNull_test_1() {
        Assert.assertEquals(Foo.BAR, "BAR".enumValueOrNull<Foo>())
    }

    @Test
    fun enumValueOrNull_test_2() {
        Assert.assertNull("BAZ".enumValueOrNull<Foo>())
    }

    @Test
    fun enumValueOrDefault_test_1() {
        Assert.assertEquals(Foo.BAR, "BAR".enumValueOrDefault(Foo.FAR))
    }

    @Test
    fun enumValueOrDefault_test_2() {
        Assert.assertEquals(Foo.FAR, "BAZ".enumValueOrDefault(Foo.FAR))
    }

    @Test
    fun isEmail_test_1() {
        Assert.assertTrue("test@test.com".isEmail())
    }

    @Test
    fun isEmail_test_2() {
        Assert.assertFalse("test.com".isEmail())
    }

    @Test
    fun isPhone_test_1() {
        Assert.assertTrue("+79211234567".isPhone())
    }

    @Test
    fun isPhone_test_2() {
        Assert.assertFalse("ABC".isPhone())
    }

    @Test
    fun isIPAddress_test_1() {
        Assert.assertTrue("192.168.0.1".isIPAddress())
    }

    @Test
    fun isIPAddress_test_2() {
        Assert.assertFalse("Hello world!".isIPAddress())
    }
}