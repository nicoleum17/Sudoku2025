package com.example.pokedexapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.pokedexapp.presentation.screens.sudoku.SudokuScreen

sealed class Screen(
    val route: String,
) {
    object Sudoku : Screen("sudokugenerate")
}

@Suppress("ktlint:standard:function-naming")
@Composable
fun PokemonNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navController, // El controlador que maneja la navegación
        startDestination = Screen.Sudoku.route, // Indica qué pantalla se muestra primero
        modifier = modifier, // Para personalizar el aspecto si es necesario
    ) {
        // Primera pantalla: Home
        composable(route = Screen.Sudoku.route) {
            // "route" es como la dirección de la pantalla
            SudokuScreen(
                viewModel = hiltViewModel(),
            )
        }
    }
}
