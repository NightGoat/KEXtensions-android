package ru.nightgoat

import exists
import orIfNull
import orLet
import org.junit.Assert
import org.junit.Test

class OtherExtTest {

    @Test
    fun orIfNull_test_1() {
        val foo: Int? = null
        val bar = foo.orIfNull { 2 }
        Assert.assertEquals(2, bar)
    }

    @Test
    fun orIfNull_test_2() {
        val foo = 2
        val bar = foo.orIfNull { 3 }
        Assert.assertEquals(2, bar)
    }

    @Test
    fun orLet_test_1() {
        val foo: Int? = null
        val bar = 12
        fun baz(value: Int): Int {
            return value * 2
        }

        val far = foo.orLet(bar, ::baz)
        Assert.assertEquals(24, far)
    }

    @Test
    fun exists_test_1() {
        val foo: Int? = null
        Assert.assertEquals(false, foo.exists())
    }

    @Test
    fun exists_test_2() {
        val foo = 1
        Assert.assertEquals(true, foo.exists())
    }
}