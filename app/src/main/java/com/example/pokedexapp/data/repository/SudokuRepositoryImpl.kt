package com.example.pokedexapp.data.repository

import com.example.pokedexapp.data.remote.api.SudokuApi
import com.example.pokedexapp.data.remote.dto.SudokuDto
import com.example.pokedexapp.domain.model.Sudoku
import com.example.pokedexapp.domain.repository.SudokuRepository
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.collections.map
import kotlin.text.toIntOrNull

@Singleton
class SudokuRepositoryImpl
    @Inject
    constructor(
        private val api: SudokuApi,
    ) : SudokuRepository {
        private fun SudokuDto.toDomain(): Sudoku {
            fun mapBoard(boardDto: List<List<String?>>): List<List<Int>> =
                boardDto.map { row ->
                    row.map { cell ->
                        cell?.toIntOrNull() ?: 0
                    }
                }

            return Sudoku(
                puzzle = mapBoard(this.puzzle),
                solution = mapBoard(this.solution),
            )
        }

        override suspend fun getSudoku(): Result<Sudoku> =
            try {
                val reponse: SudokuDto = api.getSudoku()
                val sudokuDomain: Sudoku = reponse.toDomain()

                Result.success(sudokuDomain)
            } catch (e: Exception) {
                Result.failure(e)
            }
    }
