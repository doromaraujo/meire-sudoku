package com.example.meiresudoku

import com.example.meiresudoku.logic.Difficulty
import com.example.meiresudoku.logic.Generator
import com.example.meiresudoku.logic.MATRIX_SIZE
import com.example.meiresudoku.logic.Solver

import org.junit.Assert.*
import org.junit.Test

class GeneratorTest {
    @Test
    fun generate_createsValidPuzzle() {
        val puzzle = Generator.generate(Difficulty.HARD)
        println(puzzle.board)

        // Has exactly one solution
        assertEquals(1, Solver.countSolutions(puzzle.board))

        // Every given clue matches the solution
        for (row in 0 until MATRIX_SIZE) {
            for (column in 0 until MATRIX_SIZE) {
                if (!puzzle.board.isCellEmpty(row, column)) {
                    assertEquals(puzzle.solution.getCellValue(row, column),
                        puzzle.board.getCellValue(row, column))
                }
            }
        }

        // The solution is complete
        assertNull(puzzle.solution.findEmptyCell())
    }
}