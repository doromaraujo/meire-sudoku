package com.example.meiresudoku

import com.example.meiresudoku.logic.Board
import com.example.meiresudoku.logic.Solver
import org.junit.Test

import org.junit.Assert.*

class SolverTest {
    @Test
    fun solve_fillsEmptyBoard() {
        val board = Board()
        assertTrue(Solver.solve(board))

        println(board)
    }
}