package com.example.meiresudoku.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.meiresudoku.logic.BLOCK_SIZE
import com.example.meiresudoku.logic.Board
import com.example.meiresudoku.logic.EMPTY_VALUE
import com.example.meiresudoku.logic.MATRIX_SIZE

@Composable
fun SudokuGrid(board: Board, modifier: Modifier = Modifier) {
    val lineColor = MaterialTheme.colorScheme.onBackground

    Box(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(1f)   // keeps the grid square
            .drawBehind {
                val cellSize = size.width / MATRIX_SIZE
                for (i in 0..MATRIX_SIZE) {
                    val stroke = if (i % BLOCK_SIZE == 0) 3.dp.toPx() else 1.dp.toPx()
                    val position = i * cellSize
                    drawLine(lineColor, Offset(position, 0f), Offset(position, size.height), stroke)
                    drawLine(lineColor, Offset(0f, position), Offset(size.width, position), stroke)
                }
            }
    ) {
        Column(Modifier.fillMaxSize()) {
            for (row in 0 until MATRIX_SIZE) {
                Row(Modifier.weight(1f)) {
                    for (column in 0 until MATRIX_SIZE) {
                        Box(
                            modifier = Modifier.weight(1f).fillMaxHeight(),
                            contentAlignment = Alignment.Center
                        ) {
                            val value = board.getCellValue(row, column)
                            if (value != EMPTY_VALUE) {
                                Text(value.toString(), fontSize = 20.sp)
                            }
                        }
                    }
                }
            }
        }
    }
}