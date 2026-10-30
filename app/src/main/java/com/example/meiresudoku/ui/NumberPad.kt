package com.example.meiresudoku.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.meiresudoku.logic.MATRIX_SIZE

@Composable
fun NumberPad(
    onNumberClick: (Int) -> Unit,
    onEraseClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            for (number in 1..MATRIX_SIZE) {
                FilledTonalButton(
                    onClick = { onNumberClick(number) },
                    modifier = Modifier.weight(1f),
                    contentPadding = PaddingValues(0.dp)
                ) {
                    Text(number.toString(), fontSize = 20.sp)
                }
            }
        }
        OutlinedButton(onClick = onEraseClick) {
            Text("Erase")
        }
    }
}