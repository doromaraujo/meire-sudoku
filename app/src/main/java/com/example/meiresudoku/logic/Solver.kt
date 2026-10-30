package com.example.meiresudoku.logic

import kotlin.random.Random

class Solver {
    private fun findEmptyCell(board: Board): Pair<Int, Int>? {
        for (row in 0 until MATRIX_SIZE) {
            for (column in 0 until MATRIX_SIZE) {
                if (board.isCellEmpty(row, column)) return Pair(row, column)
            }
        }

        return null
    }

    fun solve(board: Board): Boolean {
        val (row, column) = findEmptyCell(board) ?: return true

        for (value in (1..MATRIX_SIZE).shuffled()) {
            if (board.canPlace(row, column, value)) {
                board.setCellValue(row, column, value)

                if (solve(board)) return true

                board.clearCell(row, column)
            }
        }

        return false
    }
}