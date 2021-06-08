package ru.nightgoat

import minus
import orNow
import org.junit.Assert
import org.junit.Test
import plus
import java.util.*

class DateExtTest {

    @Test
    fun date_plus_test_1() {
        val date1 = Date(1)
        val date2 = Date(2)
        Assert.assertEquals(Date(3), date1 + date2)
    }

    @Test
    fun date_minus_test_1() {
        val date1 = Date(2)
        val date2 = Date(1)
        Assert.assertEquals(Date(1), date1 - date2)
    }

    @Test
    fun orNow_test_1() {
        val _null: Date? = null
        Assert.assertEquals(Date(), _null.orNow())
    }
}