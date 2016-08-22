package com.github.dhirabayashi.tenpuzzle.kt

import com.github.dhirabayashi.tenpuzzle.kt.rpn.*
import java.util.stream.IntStream

private val ANSWER = 10.0

private fun solve(num: String): List<String> = RPNUtils.createRPNExpressionPattern(num)
        .map{ RPNExpression(it) }
        .filter{ it.eval()?.doubleValue() == ANSWER }
        .map{ it.toInfix() }
        .distinct()

private class Result internal constructor(private val num: String, list: List<String>) {
    private val size: Int
    private val answers: String

    init {
        this.size = list.size
        this.answers = if (size == 0) "" else "\t" + list.joinToString("\t")
    }

    override fun toString(): String {
        return String.format("%s\t%d%s", num, size, answers)
    }
}

fun main(args: Array<String>) {
    IntStream.rangeClosed(0, 9999).parallel()
            .mapToObj{ String.format("%04d", it) }
            .map({ Result(it, solve(it)) })
            .forEachOrdered(::println)
}
