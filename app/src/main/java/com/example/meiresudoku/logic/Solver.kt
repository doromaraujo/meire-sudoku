package com.example.meiresudoku.logic

object Solver {
    fun solve(board: Board): Boolean {
        val (row, column) = board.findEmptyCell() ?: return true

        for (value in (1..MATRIX_SIZE).shuffled()) {
            if (board.canPlace(row, column, value)) {
                board.setCellValue(row, column, value)

                if (solve(board)) return true

                board.clearCell(row, column)
            }
        }

        return false
    }

    fun countSolutions(board: Board, limit: Int = 2): Int {
        val (row, column) = board.findEmptyCell() ?: return 1

        var count = 0

        for (value in (1..MATRIX_SIZE)) {
            if (board.canPlace(row, column, value)) {
                board.setCellValue(row, column, value)
                count += countSolutions(board, limit - count)
                board.clearCell(row, column)

                if (count >= limit) break
            }
        }

        return count
    }
}