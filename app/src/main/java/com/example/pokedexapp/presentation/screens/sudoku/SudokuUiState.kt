package com.example.pokedexapp.presentation.screens.sudoku

import com.example.pokedexapp.domain.model.Sudoku

data class SudokuUiState(
    val puzzle: List<List<Int>> = emptyList(),
    val solution: List<List<Int>> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
)
