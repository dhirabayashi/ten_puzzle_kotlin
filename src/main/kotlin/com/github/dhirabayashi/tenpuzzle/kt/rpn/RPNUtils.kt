package com.github.dhirabayashi.tenpuzzle.kt.rpn

/**
 * 逆ポーランド記法のユーティリティ
 */
object RPNUtils {

    /**
     * オペランドの数
     */
    private val OPERAND_LENGTH = 4

    /**
     * 演算子リスト
     */
    private val OPERATORS = listOf('+', '-', '*', '/')

    /**
     * 式の桁数
     */
    private val EXPRESSION_LENGTH = OPERAND_LENGTH + OPERATORS.size - 1


    /**
     * num を元にありうる逆ポーランド記法の式の全パターンを生成する。戻り値を返さずlist の状態を変更する
     * @param num 対象の数字
     * *
     * @param list 式を追加するList
     * *
     * @param expression 逆ポーランド記法の式
     * *
     * @param flag 数値が決まったかどうかのフラグ
     * *
     * @param numCount 式が含む数値の数
     * *
     * @param operatorCount 式が含む演算子の数
     */
    private fun createRPNExpressionPattern(num: String, list: MutableList<String>, expression: CharArray, flag: BooleanArray, numCount: Int, operatorCount: Int) {
        // 数値と演算子が全て決まったかどうか
        if (numCount + operatorCount == EXPRESSION_LENGTH) {
            list.add(String(expression))
        }

        // 演算子を入れることができるか
        // 演算子の数 = 数値の数 - 1のため、差が2以上あれば演算子を入れる余地がある
        if (2 <= numCount - operatorCount) {
            OPERATORS.forEach {
                expression[numCount + operatorCount] = it
                createRPNExpressionPattern(num, list, expression, flag, numCount, operatorCount + 1)
            }
        }

        if (numCount < OPERAND_LENGTH) {
            for (i in 0..OPERAND_LENGTH - 1) {
                if (!flag[i]) {
                    flag[i] = true
                    expression[numCount + operatorCount] = num.toCharArray()[i]
                    createRPNExpressionPattern(num, list, expression, flag, numCount + 1, operatorCount)
                    flag[i] = false
                }
            }
        }
    }

    /**
     * 4桁の数字を元にありうる逆ポーランド記法の式の全パターンを生成する。
     * @param num 対象の4桁の数字。
     * *
     * @throws IllegalArgumentException num が4桁の数字ではない場合
     * *
     * @return 逆ポーランド記法の式のList
     */
    fun createRPNExpressionPattern(num: String): MutableList<String> {
        if (num.length != OPERAND_LENGTH || !num.matches("^\\d+$".toRegex())) {
            throw IllegalArgumentException("num is illegal format.")
        }

        val list = mutableListOf<String>()

        // boolean の初期値はfalse
        createRPNExpressionPattern(num, list, CharArray(EXPRESSION_LENGTH), BooleanArray(OPERAND_LENGTH), 0, 0)
        // list は可変なので状態が変更されている
        return list
    }
}