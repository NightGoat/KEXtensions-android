package ru.nightgoat.kextensionstests

import org.junit.Assert
import org.junit.Test
import ru.nightgoat.kextensions.sumByDoubleSafe

class CollectionsExtTest {

    @Test
    fun sumByDoubleSafe_test_1() {
        val list = listOf(0.1, 0.2)
        Assert.assertEquals(0.3, list.sumByDoubleSafe { it }, 0.0)
    }
}