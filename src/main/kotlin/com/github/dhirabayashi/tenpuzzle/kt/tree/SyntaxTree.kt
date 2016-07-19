package com.github.dhirabayashi.tenpuzzle.kt.tree

import com.github.dhirabayashi.tenpuzzle.kt.data.Rational
import java.util.*

/**
 * 構文木
 */
sealed class SyntaxTree<T> {

    /**
     * 式を評価する
     * @return 木の要素の評価結果
     */
    abstract fun eval(): T?

    /**
     * 構文木の数値
     */
    class Num(val value: Rational) : SyntaxTree<Rational>() {
        override fun eval() = value

        override fun toString(): String {
            val d = value.denominator
            return if(d == 1) value.numerator.toString() else value.toString()
        }
    }

    /**
     * 構文木の演算子
     */
//    class Operator(val c: Char, val left: SyntaxTree<Rational>, val right: SyntaxTree<Rational>) : SyntaxTree<Rational>() {

//        override fun eval() {
//            return when(c) {
//                '+' -> left.eval()?.plus(right.eval())
//            }
//        }

//    }
}
