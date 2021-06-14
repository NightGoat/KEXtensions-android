package ru.nightgoat.kextensionstests

import org.junit.Assert
import org.junit.Test
import ru.nightgoat.kextensions.*

class BooleanExtTest {

    private var _null: Boolean? = null

    @Test
    fun orTrue_test_1() {
        Assert.assertEquals(true, _null.orTrue())
    }

    @Test
    fun orTrue_test_2() {
        Assert.assertEquals(false, false.orTrue())
    }

    @Test
    fun orFalse_test_1() {
        Assert.assertEquals(false, _null.orFalse())
    }

    @Test
    fun orFalse_test_2() {
        Assert.assertEquals(true, true.orFalse())
    }

    @Test
    fun takeIfTrue_test_1() {
        Assert.assertNotNull(true.takeIfTrue())
    }

    @Test
    fun takeIfTrue_test_2() {
        Assert.assertNull(false.takeIfTrue())
    }

    @Test
    fun takeIfFalse_test_1() {
        Assert.assertNotNull(false.takeIfFalse())
    }

    @Test
    fun takeIfFalse_test_2() {
        Assert.assertNull(true.takeIfFalse())
    }

    @Test
    fun doIfTrue_test_1() {
        var foo = 2
        true.doIfTrue {
            foo += 2
        }
        Assert.assertEquals(4, foo)
    }

    @Test
    fun doIfTrue_test_2() {
        var foo = 2
        false.doIfTrue {
            foo += 2
        }
        Assert.assertEquals(2, foo)
    }

    @Test
    fun doIfFalse_test_1() {
        var foo = 2
        false.doIfFalse {
            foo += 2
        }
        Assert.assertEquals(4, foo)
    }

    @Test
    fun doIfFalse_test_2() {
        var foo = 2
        true.doIfFalse {
            foo += 2
        }
        Assert.assertEquals(2, foo)
    }

    @Test
    fun toBinary_test_1(){
        val foo = true.toBinary()
        Assert.assertEquals(1, foo)
    }

    @Test
    fun toBinary_test_2(){
        val foo = false.toBinary()
        Assert.assertEquals(0, foo)
    }
}