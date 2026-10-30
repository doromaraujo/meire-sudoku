package com.example.meiresudoku

import com.example.meiresudoku.logic.Board
import org.junit.Assert.*
import org.junit.Test

class BoardTest {
    @Test
    fun canPlace_detectsConflicts() {
        val board = Board()

        board.setCellValue(0, 0, 5)

        assertFalse(board.canPlace(0, 8, 5))
        assertFalse(board.canPlace(8, 0, 5))
        assertFalse(board.canPlace(2, 2, 5))
        assertTrue(board.canPlace(3, 2, 5))
    }

    @Test
    fun copy_isDifferentFromSource() {
        val original = Board()
        original.setCellValue(0, 0, 5)

        val copy = original.copy()
        original.clearCell(0, 0)

        assertEquals(5, copy.getCellValue(0, 0))   // the copy wasn't affected
    }
}