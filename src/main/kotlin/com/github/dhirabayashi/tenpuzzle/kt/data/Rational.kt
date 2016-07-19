package com.github.dhirabayashi.tenpuzzle.kt.data

/**
 * 有理数
 */
class Rational(n: Int, d: Int) {

    val numerator: Int
    val denominator: Int

    init {
        if(d == 0) {
            throw IllegalArgumentException("denominator is zero.")
        }

        val g = gcd(Math.abs(n), Math.abs(d))

        numerator = n / g
        denominator = d / g
    }

    constructor(n: Int) : this(n, 1)

    // ユークリッドの互除法で最大公約数を求める
    private tailrec fun gcd(a: Int, b: Int): Int = if(b == 0) a else gcd(b, a % b)

    /**
     * 符号反転
     */
    operator fun unaryMinus() = Rational(-numerator, denominator)

    /**
     * 足し算
     * @param that 足すRational
     * @return 足し算結果
     */
    operator fun plus(that: Rational): Rational {
        val numerator = this.numerator * that.denominator + that.numerator * this.denominator
        val denominator = this.denominator * that.denominator

        return Rational(numerator, denominator)
    }

    /**
     * 引き算
     * @param that 引くRational
     * @return 引き算結果
     */
    operator fun minus(that: Rational) = this + (-that)

    /**
     * 掛け算
     * @param that 掛けるRational
     * @return 掛け算結果
     */
    operator fun times(that: Rational): Rational {
        val numerator = this.numerator * that.numerator
        val denominator = this.denominator * that.denominator

        return Rational(numerator, denominator)
    }

    /**
     * 割り算
     * @param that 割るRational
     * @return 割り算結果
     * @throws ArithmeticException thatの分子が0の場合
     */
    operator fun div(that: Rational): Rational {
        if(that.numerator == 0) {
            throw ArithmeticException("numerator of divistor is zero")
        }

        val numerator = this.numerator * that.denominator
        val denominator = this.denominator * that.numerator

        return Rational(numerator, denominator)
    }

    override fun toString() = "${numerator}/${denominator}"

    /**
     * この分数の小数値を返す
     * @return この分数の小数値
     */
    fun doubleValue() = numerator.toDouble() / denominator.toDouble()
}