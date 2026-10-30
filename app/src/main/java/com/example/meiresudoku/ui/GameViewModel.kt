package com.example.meiresudoku.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.meiresudoku.logic.Board
import com.example.meiresudoku.logic.Difficulty
import com.example.meiresudoku.logic.Generator
import com.example.meiresudoku.logic.Puzzle
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class GameViewModel : ViewModel() {
    var puzzle by mutableStateOf<Puzzle?>(null)
        private set
    var board by mutableStateOf<Board?>(null)
        private set
    var selectedCell by mutableStateOf<Pair<Int, Int>?>(null)
        private set

    init {
        newGame(Difficulty.MEDIUM)
    }

    fun newGame(difficulty: Difficulty) {
        viewModelScope.launch {
            val generated = withContext(Dispatchers.Default) {
                Generator.generate(difficulty)
            }

            puzzle = generated
            board = generated.board.copy()
            selectedCell = null
        }
    }

    fun selectCell(row: Int, column: Int) {
        selectedCell = Pair(row, column)
    }

    fun isClue(row: Int, column: Int): Boolean = puzzle?.board?.isCellEmpty(row, column) == false

    fun enterValue(value: Int) {
        val (row, column) = selectedCell ?: return
        val current = board ?: return
        if (isClue(row, column)) return

        board = current.copy().apply { setCellValue(row, column, value) }
    }

    fun eraseValue() {
        val (row, column) = selectedCell ?: return
        val current = board ?: return
        if (isClue(row, column)) return

        board = current.copy().apply { clearCell(row, column) }
    }
}