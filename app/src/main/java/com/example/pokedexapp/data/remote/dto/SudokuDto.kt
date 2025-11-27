package com.example.pokedexapp.data.remote.dto

import com.google.gson.annotations.SerializedName

data class SudokuDto(
    @SerializedName("puzzle") val puzzle: List<List<String?>>,
    @SerializedName("solution") val solution: List<List<String?>>,
)
