package com.example.pokedexapp.presentation.screens.sudoku

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedexapp.domain.common.Result
import com.example.pokedexapp.domain.usecase.GetSudokuUseCase
import com.example.pokedexapp.presentation.screens.sudoku.SudokuUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SudokuViewModel
    @Inject
    constructor(
        private val getSudokuUseCase: GetSudokuUseCase,
    ) : ViewModel() {
        private val _uiState = MutableStateFlow(SudokuUiState())
        val uiState: StateFlow<SudokuUiState> = _uiState.asStateFlow()

        init {
            loadSudokuBoard()
        }

        private fun loadSudokuBoard() {
            viewModelScope.launch {
                getSudokuUseCase().collect { result ->
                    _uiState.update { state ->
                        when (result) {
                            is Result.Loading ->
                                state.copy(
                                    isLoading = true,
                                )
                            is Result.Success ->
                                state.copy(
                                    puzzle = result.data.puzzle,
                                    solution = result.data.solution,
                                    isLoading = false,
                                    error = null,
                                )
                            is Result.Error ->
                                state.copy(
                                    error = result.exception.message,
                                    isLoading = false,
                                )
                        }
                    }
                }
            }
        }
    }
