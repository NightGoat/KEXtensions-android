package ru.nightgoat.kextensionstests

import org.junit.Assert
import org.junit.Test
import ru.nightgoat.kextensions.utils.Try

class TryTest {

    @Test
    fun try_test_1() {
        val error = Try {
            2 / 0
        }
        Assert.assertTrue(error.isFail())
    }

    @Test
    fun try_test_2() {
        val error = Try {
            2 / 2
        }
        Assert.assertTrue(error.isSuccess())
    }

    @Test
    fun try_test_finally_1() {
        var variable = 0
        Try {
            2 / 2
        }.finally { divisionResult ->
            divisionResult?.let {
                variable = it
            }
        }
        Assert.assertEquals(variable, 1)
    }

    @Test
    fun try_test_finally_2() {
        var variable = 0
        Try {
            2 / 0
        }.finally {
            variable = 1
        }
        Assert.assertEquals(variable, 1)
    }

    @Test
    fun try_test_finally_3() {
        var variable = 0
        Try(finallyBlock = {
            variable = 1
        }) {
            2 / 0
        }
        Assert.assertEquals(variable, 1)
    }

    @Test
    fun try_test_finally_4() {
        var variable: Int? = 0
        Try(finallyBlock = {
            variable = it
        }) {
            2 / 2
        }
        Assert.assertEquals(variable, 1)
    }
}