package com.example.meiresudoku.logic

data class Puzzle(val board: Board, val solution: Board)

object Generator {
    /**
     * Generates a puzzle with a unique solution.
     *
     * Fills an empty board, then removes values in random order, keeping only
     * removals that leave exactly one solution. Stops at the difficulty's clue
     * target, or earlier if no more values can be removed. In that case the
     * puzzle may have more clues than the target.
     */
    fun generate(difficulty: Difficulty): Puzzle {
        val board = Board()
        Solver.solve(board)

        val solution = board.copy()

        // Visit every cell once, in random order.
        val cells = (0 until MATRIX_SIZE * MATRIX_SIZE).shuffled()

        // Number of filled cells left; the board starts full.
        var clues = MATRIX_SIZE * MATRIX_SIZE

        for (index in cells) {
            if (clues <= difficulty.clues) break

            val row = index / MATRIX_SIZE
            val column = index % MATRIX_SIZE
            val backup = board.getCellValue(row, column)

            board.clearCell(row, column)

            // A removal that creates a second solution makes the puzzle ambiguous, so undo it.
            if (Solver.countSolutions(board) != 1) {
                board.setCellValue(row, column, backup)
            } else {
                clues--
            }
        }

        return Puzzle(board, solution)
    }
}