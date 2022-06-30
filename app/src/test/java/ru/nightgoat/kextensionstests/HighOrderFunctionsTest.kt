package ru.nightgoat.kextensionstests

import io.github.nightgoat.kexcore.utils.constants.DateFormats.DATE_FORMAT_yyyy_MM_dd_HH_mm_ss
import org.junit.Assert
import org.junit.Test
import ru.nightgoat.kextensions.toStringFormatted
import ru.nightgoat.kextensions.tryOrNow
import java.util.*

class HighOrderFunctionsTest {


    @Test
    fun tryOrNow_test_1() {
        var now: Date? = Date()
        val foo = tryOrNow {
            now = Date()
            throw NullPointerException()
        }
        Assert.assertEquals(
            now?.toStringFormatted(DATE_FORMAT_yyyy_MM_dd_HH_mm_ss),
            foo.toStringFormatted(DATE_FORMAT_yyyy_MM_dd_HH_mm_ss)
        )
    }

    @Test
    fun tryOrNow_test_2() {
        val foo = tryOrNow {
            Date(1)
        }
        Assert.assertEquals(Date(1), foo)
    }
}