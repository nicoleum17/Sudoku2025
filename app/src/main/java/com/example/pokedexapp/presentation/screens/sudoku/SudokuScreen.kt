package com.example.pokedexapp.presentation.screens.sudoku

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.pokedexapp.presentation.ui.components.SudokuGrid

/*
val initialSudokuBoard =
    List(3) { row ->
        List(3) { col ->
            when {
                row == 0 && col == 0 -> 5
                row == 1 && col == 2 -> 8
                row == 2 && col == 2 -> 9
                else -> 0
            }
        }
    }
 */

@Suppress("ktlint:standard:function-naming")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SudokuScreen(
    viewModel: SudokuViewModel = hiltViewModel(),
    onSaveSuccess: () -> Unit = {},
    onCancel: () -> Unit = {},
) {
    val uiState by viewModel.uiState.collectAsState()
    var boardState by remember { mutableStateOf(uiState.puzzle) }
    val isLoading = false

    val onCellChange: (row: Int, col: Int, value: String) -> Unit = { row, col, value ->
        val newValue = value.toIntOrNull() ?: 0

        boardState =
            boardState.toMutableList().apply {
                this[row] =
                    this[row].toMutableList().apply {
                        this[col] = newValue
                    }
            }
    }

    Scaffold(
        containerColor = Color.DarkGray,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Sudoku",
                        color = Color.DarkGray,
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                    )
                },
            )
        },
    ) { padding ->
        Column(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(paddingValues = padding)
                    .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(Modifier.height(32.dp))

            SudokuGrid(
                board = boardState,
                onCellChange = onCellChange,
                modifier = Modifier.padding(horizontal = 16.dp),
            )

            Spacer(Modifier.height(32.dp))

            Row(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                if (isLoading) {
                    CircularProgressIndicator()
                } else {
                    Box(
                        modifier =
                            Modifier
                                .size(72.dp)
                                .background(color = Color.DarkGray)
                                .clickable(onClick = { }),
                        contentAlignment = Alignment.Center,
                    ) {
                        Text(
                            text = "Nueva\nPartida",
                            style = MaterialTheme.typography.titleLarge.copy(fontSize = 12.sp),
                            color = Color.Blue,
                            textAlign = TextAlign.Center,
                        )
                    }

                    Text(text = "Comprobar", color = Color.White)
                }
            }

            Spacer(Modifier.height(32.dp))
        }
    }
}

@Suppress("ktlint:standard:function-naming")
@Preview(showBackground = true)
@Composable
fun SudokuScreenPreview() {
    MaterialTheme {
        SudokuScreen()
    }
}
