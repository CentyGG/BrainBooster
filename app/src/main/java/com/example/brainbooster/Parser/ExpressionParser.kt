package com.example.brainbooster.Parser

import java.util.*

class ExpressionParser(private val expression: String) {
    private val operators = mapOf(
        "+" to 1,
        "-" to 1,
        "*" to 2,
        "/" to 2
    )

    fun evaluate(): Int {
        val postfix = infixToPostfix(expression)
        return evaluatePostfix(postfix)
    }

    private fun infixToPostfix(infix: String): List<String> {
        val postfix = mutableListOf<String>()
        val stack = Stack<String>()

        val tokens = infix.split("\\s".toRegex())

        for (token in tokens) {
            if (token.toIntOrNull() != null) {
                postfix.add(token)
            } else if (token in operators) {
                while (!stack.isEmpty() && stack.peek() in operators && operators[stack.peek()]!! >= operators[token]!!) {
                    postfix.add(stack.pop())
                }
                stack.push(token)
            }
        }

        while (!stack.isEmpty()) {
            postfix.add(stack.pop())
        }

        return postfix
    }

    private fun evaluatePostfix(postfix: List<String>): Int {
        val stack = Stack<Int>()

        for (token in postfix) {
            if (token.toIntOrNull() != null) {
                stack.push(token.toInt())
            } else {
                val rightOperand = stack.pop()
                val leftOperand = stack.pop()
                val result = when (token) {
                    "+" -> leftOperand + rightOperand
                    "-" -> leftOperand - rightOperand
                    "*" -> leftOperand * rightOperand
                    "/" -> leftOperand / rightOperand
                    else -> throw IllegalArgumentException("Unknown operator: $token")
                }
                stack.push(result)
            }
        }

        return stack.pop()
    }
}