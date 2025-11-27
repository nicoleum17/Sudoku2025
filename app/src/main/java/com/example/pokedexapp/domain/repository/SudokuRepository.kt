package com.example.pokedexapp.domain.repository

import com.example.pokedexapp.domain.model.Sudoku

interface SudokuRepository {
    suspend fun getSudoku(): Result<Sudoku>
}
