package com.example.pokedexapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

sealed class Screen(
    val route: String,
) {
    /*object Home : Screen("home")

    object Detail : Screen("pokemon/{pokemonId}") {
        fun createRoute(pokemonId: String) = "pokemon/$pokemonId"
    }*/
}

@Suppress("ktlint:standard:function-naming")
@Composable
fun PokemonNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    /*NavHost(
        navController = navController, // El controlador que maneja la navegación
        startDestination = Screen.Home.route, // Indica qué pantalla se muestra primero
        modifier = modifier, // Para personalizar el aspecto si es necesario
    ) {
        // Primera pantalla: Home
        composable(route = Screen.Home.route) {
            // "route" es como la dirección de la pantalla
            HomeScreen(
                onPokemonClick = { pokemonId ->
                    // Qué hacer cuando clickean un Pokémon
                    // Navega a la pantalla de detalle con el ID del Pokémon
                    navController.navigate(Screen.Detail.createRoute(pokemonId))
                },
            )
        }

    }*/
}
