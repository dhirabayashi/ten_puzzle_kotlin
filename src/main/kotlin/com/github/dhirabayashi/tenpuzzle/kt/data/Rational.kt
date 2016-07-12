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

        val absN = Math.abs(n)
        val absD = Math.abs(d)
        val a = Math.max(absN, absD)
        val b = Math.min(absN, absD)

        val g = gcd(a, b)

        numerator = n / g
        denominator = d / g
    }

    constructor(n: Int) : this(n, 1)

    // ユークリッドの互除法で最大公約数を求める
    private fun gcd(a: Int, b: Int): Int = if(b == 0) a else gcd(b, a % b)

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

    operator fun minus(that: Rational) = this + (-that)

    override fun toString() = "${numerator}/${denominator}"

    /**
     * この分数の小数値を返す
     * @return この分数の小数値
     */
    fun doubleValue() = numerator.toDouble() / denominator.toDouble()
}