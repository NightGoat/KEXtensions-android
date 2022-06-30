package ru.nightgoat.kextensionstests

import org.junit.Assert
import org.junit.Test
import ru.nightgoat.kextensions.enumValueOrDefault
import ru.nightgoat.kextensions.enumValueOrNull

class StringExtTest {

    private enum class Foo {
        BAR,
        FAR
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
}