package com.example.pokedexapp.presentation.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Suppress("ktlint:standard:function-naming")
@Composable
fun SudokuGrid(
    board: List<List<Int>>,
    onCellChange: (row: Int, col: Int, value: String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier =
            modifier
                .fillMaxWidth(0.9f)
                .aspectRatio(1f)
                .padding(8.dp)
                .border(3.dp, Color.Black),
    ) {
        (0 until 3).forEach { row ->
            Row(
                Modifier.weight(1f),
            ) {
                (0 until 3).forEach { col ->
                    val cellValue = board[row][col].let { if (it == 0) "" else it.toString() }
                    val isInitial = board[row][col] != 0

                    Box(
                        modifier =
                            Modifier
                                .weight(1f)
                                .fillMaxHeight()
                                .padding(end = 2.dp, bottom = 2.dp),
                    ) {
                        SudokuCellInput(
                            value = cellValue,
                            onValueChange = { newValue -> onCellChange(row, col, newValue) },
                            isInitial = isInitial,
                            isError = false,
                        )
                    }
                }
            }
        }
    }
}
