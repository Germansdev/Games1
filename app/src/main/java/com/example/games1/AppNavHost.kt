package com.example.games1

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.games1.AppDestinations.BottomBarScreen
import com.example.games1.ui.GameUiState
import com.example.games1.ui.GameViewModel
import com.example.games1.ui.screens.FavoriteViewModel
import com.example.games1.ui.screens.FavoritesScreen
import com.example.games1.ui.screens.GameListScreen
import com.example.games1.ui.screens.HomeScreen
import com.example.games1.ui.screens.PlayViewModel
import com.example.games1.ui.screens.RatedScreen
import com.example.games1.ui.screens.RatedViewModel
import com.example.games1.ui.screens.ShareViewModel


//NAVIGATE WITH COMPOSE COMPONENTS BUTON , ETC.

@Composable
fun GameNavHost(
    navController: NavHostController,
    //modifier: Modifier = Modifier,

) {
    val viewModel: GameViewModel = viewModel(factory = GameViewModel.Factory)

    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Pantalla3.route,
        //startDestination = BottomBarScreen.ListScreen.route,
        //modifier = modifier
    ) {

        composable(BottomBarScreen.Pantalla3.route) {
            com.example.games1.ui.screens.Played()
        }

        composable(BottomBarScreen.Pantalla2.route) {
            FavoritesScreen(
                viewModel = viewModel,
                viewModels = FavoriteViewModel(),
                gameUiState = viewModel.gameUiState,
                retryAction = { viewModel.getGames() })
        }
        /**
        composable(BottomBarScreen.Pantalla3.route) {
        com.example.games.ui.screens.Played()
        }
*/
        composable(BottomBarScreen.Pantalla4.route) {
        RatedScreen()
        }
/**
        composable(BottomBarScreen.Pantalla5.route) {
        com.example.games.ui.screens.Prueba()
        }*/

        composable(BottomBarScreen.Pantalla6.route) {

            HomeScreen(
                gameUiState = viewModel.gameUiState,
                retryAction = { viewModel.getGames() },
                modifier = Modifier
            )
        }
        composable(BottomBarScreen.Pantalla7.route) {
            GameListScreen(
                viewModel = viewModel,
                favoriteViewModel = FavoriteViewModel(),
                shareViewModel = ShareViewModel(),
                playViewModel = PlayViewModel(),
                ratedViewModel = RatedViewModel(),
                games = viewModel(),
                gameUiState = GameUiState.Loading,
            )
        }

    }
}