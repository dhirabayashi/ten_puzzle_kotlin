package com.github.dhirabayashi.tenpuzzle.kt.rpn

import org.assertj.core.api.Assertions.*
import org.junit.Test
import java.io.File

class RPNUtilsTest {
    @Test(expected = IllegalArgumentException::class)
    fun test_createRPNExpressionPattern_not_four() {
        RPNUtils.createRPNExpressionPattern("12345")
    }

    @Test(expected = IllegalArgumentException::class)
    fun test_createRPNExpressionPattern_not_num() {
        RPNUtils.createRPNExpressionPattern("123a")
    }

    @Test
    fun test_createRPNExpressionPattern() {
        val actual = RPNUtils.createRPNExpressionPattern("2501").sorted()

        val file = File(Thread.currentThread().contextClassLoader.getResource("expected2501.txt").toURI())
        val expected = file.readLines().sorted()

        assertThat(actual).isEqualTo(expected)
    }
}