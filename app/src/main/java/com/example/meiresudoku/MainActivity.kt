package com.example.meiresudoku

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.meiresudoku.logic.Difficulty
import com.example.meiresudoku.logic.Generator
import com.example.meiresudoku.ui.SudokuGrid
import com.example.meiresudoku.ui.theme.MeireSudokuTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MeireSudokuTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val puzzle = remember { Generator.generate(Difficulty.MEDIUM) }
                    SudokuGrid(
                        board = puzzle.board,
                        modifier = Modifier.padding(innerPadding).padding(16.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MeireSudokuTheme {
        Greeting("Android")
    }
}