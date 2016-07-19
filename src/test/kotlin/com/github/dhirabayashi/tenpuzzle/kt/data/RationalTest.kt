package com.github.dhirabayashi.tenpuzzle.kt.data

import org.assertj.core.api.Assertions.*
import org.junit.Test

class RationalTest {
    @Test
    fun test_constructor() {
        var sut = Rational(1, 2)
        assertThat(sut.toString()).isEqualTo("1/2")
        assertThat(sut.numerator).isEqualTo(1)
        assertThat(sut.denominator).isEqualTo(2)
        assertThat(sut.doubleValue().compareTo(1.0 / 2.0)).isEqualTo(0)

        sut = Rational(4, -3)
        assertThat(sut.toString()).isEqualTo("4/-3")
        assertThat(sut.numerator).isEqualTo(4)
        assertThat(sut.denominator).isEqualTo(-3)
        assertThat(sut.doubleValue().compareTo(4.0 / -3.0)).isEqualTo(0)

        sut = Rational(-2, 10)
        assertThat(sut.toString()).isEqualTo("-1/5")
        assertThat(sut.numerator).isEqualTo(-1)
        assertThat(sut.denominator).isEqualTo(5)
        assertThat(sut.doubleValue().compareTo(-1.0 / 5.0)).isEqualTo(0)
    }

    @Test(expected = IllegalArgumentException::class)
    fun test_constructor_zeroDenominator() {
        Rational(1, 0)
    }

    @Test
    fun test_plus() {
        var actual = Rational(2, 7) + Rational(3, 7)
        assertThat(actual.toString()).isEqualTo("5/7")

        actual = Rational(1, 2) + Rational(1, 2)
        assertThat(actual.toString()).isEqualTo("1/1")

        actual = Rational(2, 3) + Rational(-1, 4)
        assertThat(actual.toString()).isEqualTo("5/12")
    }

    @Test
    fun test_minus() {
        var actual = Rational(4, 5) - Rational(2, 7)
        assertThat(actual.toString()).isEqualTo("18/35")

        actual = Rational(9, 4) - Rational(6, 8)
        assertThat(actual.toString()).isEqualTo("3/2")

        actual = Rational(1, 3) - Rational(1, 2)
        assertThat(actual.toString()).isEqualTo("-1/6")
    }

    @Test
    fun test_times() {
        var actual = Rational(2, 5) * Rational(2, 3)
        assertThat(actual.toString()).isEqualTo("4/15")

        actual = Rational(-1, 4) * Rational(2, 8)
        assertThat(actual.toString()).isEqualTo("-1/16")

        actual = Rational(-3, 7) * Rational(-2, 5)
        assertThat(actual.toString()).isEqualTo("6/35")
    }

    @Test
    fun test_div() {
        var actual = Rational(2, 5) / Rational(2, 3)
        assertThat(actual.toString()).isEqualTo("3/5")

        actual = Rational(-1, 4) / Rational(2, 8)
        assertThat(actual.toString()).isEqualTo("-1/1")

        actual = Rational(-3, 7) / Rational(-2, 5)
        assertThat(actual.toString()).isEqualTo("-15/-14")
    }
}