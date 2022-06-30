package ru.nightgoat.kextensionstests

import org.junit.Assert
import org.junit.Test
import ru.nightgoat.kextensions.*

class DoubleExtTest {

    @Test
    fun dropZeros_test_1() {
        val foo = 0.10
        Assert.assertEquals("0.1", foo.dropZeros())
    }

    @Test
    fun dropZeros_test_2() {
        val foo = 0.101
        Assert.assertEquals("0.101", foo.dropZeros())
    }

    @Test
    fun dropZeros_test_3() {
        val foo: Double? = null
        Assert.assertEquals("0", foo.dropZeros())
    }

    @Test
    fun sumWith_test_1() {
        val foo = 0.1
        val bar = 0.2
        Assert.assertEquals(0.3, foo.sumWith(bar), 0.0)
    }

    @Test
    fun minWith_test_1() {
        val foo = 0.3
        val bar = 0.1
        Assert.assertEquals(0.2, foo.minWith(bar), 0.0)
    }

    @Test
    fun roundTo_test_1() {
        val foo = 0.12345
        Assert.assertEquals(0.12, foo.roundTo(2), 0.0)
    }

    @Test
    fun roundTo_test_2() {
        val foo = 0.12345
        Assert.assertNotEquals(0.12, foo.roundTo(3), 0.0)
    }

    @Test
    fun divWith_test_1() {
        val foo = 0.1
        Assert.assertNull(foo.divWith(0.0))
    }

    @Test
    fun divWith_test_2() {
        val foo = 4.0
        Assert.assertEquals(2.0, foo.divWith(2.0))
    }

    @Test
    fun toStringWithoutScientificNotation_test_1() {
        val foo = 100000000.123
        Assert.assertEquals("100000000.123", foo.toStringWithoutScientificNotation())
    }
}