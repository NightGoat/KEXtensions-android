package ru.nightgoat

import orZero
import org.junit.Assert
import org.junit.Test
import reverse
import toNegative
import toPositive

class IntExtTest {

    @Test
    fun orZeroTest_1() {
        val nullableInt: Int? = null
        Assert.assertEquals(0, nullableInt.orZero())
    }

    @Test
    fun orZeroTest_2() {
        var nullableInt: Int? = null
        nullableInt = 42
        Assert.assertEquals(42, nullableInt.orZero())
    }

    @Test
    fun toNegativeTest_1(){
        val foo = 42
        Assert.assertEquals(-42, foo.toNegative())
    }

    @Test
    fun toNegativeTest_2(){
        val foo = -42
        Assert.assertEquals(-42, foo.toNegative())
    }

    @Test
    fun toPositiveTest_1(){
        val foo = -42
        Assert.assertEquals(42, foo.toPositive())
    }

    @Test
    fun toPositiveTest_2(){
        val foo = 42
        Assert.assertEquals(42, foo.toPositive())
    }

    @Test
    fun reverseTest_1(){
        val foo = 42
        Assert.assertEquals(-42, foo.reverse())
    }

    @Test
    fun reverseTest_2(){
        val foo = 42
        Assert.assertEquals(-42, foo.reverse())
    }

    @Test
    fun reverseTest_3(){
        val foo = 42
        Assert.assertEquals(42, foo.reverse().reverse())
    }
}