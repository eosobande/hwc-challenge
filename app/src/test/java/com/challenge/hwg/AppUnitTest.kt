package com.challenge.hwg

import com.challenge.hwg.extension.progress
import com.challenge.hwg.extension.rating
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 */
class AppUnitTest {

    @Test
    fun ratingIsCorrect() {
        assertEquals("0.9", 9.rating)
        assertEquals("8.8", 88.rating)
        assertEquals("10.0", 100.rating)
    }

    @Test
    fun progressIsCorrect() {
        assertEquals(.7f, 70.progress)
        assertEquals(.55f, 55.progress)
        assertEquals(1f, 100.progress)
    }

}