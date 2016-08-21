package com.github.dhirabayashi.tenpuzzle.kt.rpn

import com.github.dhirabayashi.tenpuzzle.kt.data.Rational
import com.github.dhirabayashi.tenpuzzle.kt.tree.SyntaxTree
import java.util.*

/**
 * 逆ポーランド記法の式
 */
class RPNExpression(expression: String) {
    private val syntaxTree: SyntaxTree<Rational>

    init {
        val stack = LinkedList<Char>()
        expression.toCharArray().forEach { stack.push(it) }

        syntaxTree = createTree(stack)

        if(!stack.isEmpty() || !(syntaxTree is SyntaxTree.Operator)) {
            throw IllegalArgumentException("expression is illegal format.");
        }
    }

    // 逆ポーランド記法の式を逆から（スタックの取り出し順）から解析していけばちょうど構文木になる
    private fun createTree(stack: LinkedList<Char>): SyntaxTree<Rational> {

        if(stack.isEmpty()) {
            throw IllegalArgumentException("expression is illegal format.")
        }

        val c = stack.pop()
        if(Character.isDigit(c)) {
            return SyntaxTree.Num(Rational(Character.digit(c, 10)))
        } else {
            // 右が先
            val right = createTree(stack)
            val left = createTree(stack)

            return SyntaxTree.Operator(c, left, right)
        }
    }

    /**
     * この式を表す中置記法の式を返す
     * @return 中置記法の式
     */
    fun toInfix(): String {
        val tmp = syntaxTree.toString()
        return tmp.substring(1, tmp.length - 1)
    }

    /**
     * 式を評価する。
     * @return 評価された値。評価できない場合は空のOptional
     */
    fun eval(): Rational? {
        return syntaxTree.eval()
    }
}
