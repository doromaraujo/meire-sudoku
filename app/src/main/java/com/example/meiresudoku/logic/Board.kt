package com.example.meiresudoku.logic

class Board {
    companion object {
        const val MATRIX_SIZE = 9
        const val EMPTY_VALUE = 0
        const val BLOCK_SIZE = 3
    }

    private val cells = Array(MATRIX_SIZE) { IntArray(MATRIX_SIZE) }

    private fun checkIndex(index: Int) {
        require(index in 0 until MATRIX_SIZE) { "Index out of bounds: $index" }
    }

    fun setCellValue(row: Int, column: Int, value: Int) {
        checkIndex(row)
        checkIndex(column)

        require(value in 1..MATRIX_SIZE) { "Forbidden value: $value" }

        cells[row][column] = value
    }

    fun clearCell(row: Int, column: Int) {
        checkIndex(row)
        checkIndex(column)

        cells[row][column] = EMPTY_VALUE;
    }

    fun getCellValue(row: Int, column: Int): Int {
        checkIndex(row)
        checkIndex(column)

        return cells[row][column]
    }

    fun isEmpty(row: Int, column: Int): Boolean = getCellValue(row, column) == EMPTY_VALUE

    fun canPlace(row: Int, column: Int, value: Int): Boolean {
        checkIndex(row)
        checkIndex(column)

        require(value in 1..MATRIX_SIZE) { "Forbidden value: $value" }

        // Checking rows and columns:
        for (i in 0 until MATRIX_SIZE) {
            if (cells[row][i] == value || cells[i][column] == value) return false
        }

        // Checking block:
        val startingRow = (row / BLOCK_SIZE) * BLOCK_SIZE
        val startingColumn = (column / BLOCK_SIZE) * BLOCK_SIZE

        for (i in startingRow until startingRow + BLOCK_SIZE) {
            for (j in startingColumn until startingColumn + BLOCK_SIZE) {
                if (cells[i][j] == value) return false
            }
        }

        return true
    }
}