package com.github.dhirabayashi.tenpuzzle.kt.rpn

import org.assertj.core.api.Assertions.*
import org.junit.Test

class RPNExpressionTest {
    @Test
    fun test_plus() {
        val actual = RPNExpression("42+").eval()
        assertThat(actual).isNotNull()
        assertThat(actual?.doubleValue()).isEqualTo(6.0)
    }

    @Test
    fun test_minus() {
        var actual = RPNExpression("42-").eval()
        assertThat(actual).isNotNull()
        assertThat(actual?.doubleValue()).isEqualTo(2.0)

        actual = RPNExpression("16-").eval()
        assertThat(actual).isNotNull()
        assertThat(actual?.doubleValue()).isEqualTo(-5.0)
    }

    @Test
    fun test_times() {
        val actual = RPNExpression("42*").eval()
        assertThat(actual).isNotNull()
        assertThat(actual?.doubleValue()).isEqualTo(8.0)
    }

    @Test
    fun test_div() {
        var actual = RPNExpression("82/").eval()
        assertThat(actual).isNotNull()
        assertThat(actual?.doubleValue()).isEqualTo(4.0)

        actual = RPNExpression("52/").eval()
        assertThat(actual).isNotNull()
        assertThat(actual?.doubleValue()).isEqualTo(2.5)

        actual = RPNExpression("50/").eval()
        assertThat(actual).isNull()
    }

    @Test(expected = IllegalArgumentException::class)
    fun test_illegalOperator() {
        RPNExpression("82?")
    }

    @Test(expected = IllegalArgumentException::class)
    fun test_tooManyOperand() {
        RPNExpression("12+3")
    }

    @Test(expected = IllegalArgumentException::class)
    fun test_tooFewOperand() {
        RPNExpression("1+")
    }

    // NNNNooo
    @Test
    fun test_pattern1() {
        val actual = RPNExpression("1234+-*").eval()
        assertThat(actual?.doubleValue()).isEqualTo(-5.0)
    }

    // NNNoNoo
    @Test
    fun test_pattern2() {
        val actual = RPNExpression("873/3*-").eval()
        assertThat(actual?.doubleValue()).isEqualTo(1.0)
    }

    // NNoNNoo
    @Test
    fun test_pattern3() {
        val actual = RPNExpression("03+56/*").eval()
        assertThat(actual?.doubleValue()).isEqualTo(2.5)
    }

    // NNoNoNo
    @Test
    fun test_pattern4() {
        val actual = RPNExpression("56*8+3+").eval()
        assertThat(actual?.doubleValue()).isEqualTo(41.0)
    }

    // NNNooNo
    @Test
    fun test_pattern5() {
        val actual = RPNExpression("467+-2-").eval()
        assertThat(actual?.doubleValue()).isEqualTo(-11.0)
    }

    @Test
    fun test_toInfix() {
        assertThat(RPNExpression("12+").toInfix()).isEqualTo("1+2")

        assertThat(RPNExpression("1234+-*").toInfix()).isEqualTo("1*(2-(3+4))")
        assertThat(RPNExpression("873/3*-").toInfix()).isEqualTo("8-((7/3)*3)")
        assertThat(RPNExpression("03+56/*").toInfix()).isEqualTo("(0+3)*(5/6)")
        assertThat(RPNExpression("56*8+3+").toInfix()).isEqualTo("((5*6)+8)+3")
        assertThat(RPNExpression("467+-2-").toInfix()).isEqualTo("(4-(6+7))-2")
    }
}
