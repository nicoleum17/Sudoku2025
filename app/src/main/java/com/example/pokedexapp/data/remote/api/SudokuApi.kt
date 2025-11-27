package com.example.pokedexapp.data.remote.api

import com.example.pokedexapp.data.remote.dto.SudokuDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SudokuApi {
    @GET("/sudokugenerate")
    suspend fun getSudoku(
        @Query("width") amount: Int = 3,
        @Query("height") width: Int = 3,
        @Query("difficulty") difficulty: String = "medium",
    ): SudokuDto
}
