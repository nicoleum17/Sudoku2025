package com.example.pokedexapp.domain.usecase

import com.example.pokedexapp.domain.common.Result
import com.example.pokedexapp.domain.model.Sudoku
import com.example.pokedexapp.domain.repository.SudokuRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import kotlin.fold

class GetSudokuUseCase
    @Inject
    constructor(
        private val repository: SudokuRepository,
    ) {
        operator fun invoke(): Flow<Result<Sudoku>> =
            flow {
                emit(Result.Loading)
                val repositoryResult = repository.getSudoku()

                repositoryResult.fold(
                    onSuccess = { sudoku ->
                        emit(Result.Success(sudoku))
                    },
                    onFailure = { throwable ->
                        emit(Result.Error(exception = throwable))
                    },
                )
            }
    }
