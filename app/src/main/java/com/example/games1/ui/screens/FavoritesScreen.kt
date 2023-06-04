package com.example.games1.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.games1.R
import com.example.games1.ui.GameUiState
import com.example.games1.ui.GameViewModel


@Composable
fun FavoritesScreen(
    viewModel: GameViewModel,
    viewModels: FavoriteViewModel,
    gameUiState: GameUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier
) {
    //favorites:
 //  val gameUiState = viewModel.gameUiState.collectAsState().value



    Column {
          if (!viewModels.favoriteGames.isEmpty()) {
            when (gameUiState) {
                is GameUiState.Loading -> LoadingScreen(modifier)
                is GameUiState.Success -> GameListScreen(
                    viewModel = viewModel,
                    //favoriteViewModel = FavoriteViewModel(),
                    shareViewModel = ShareViewModel(),
                    playViewModel = PlayViewModel(),
                    ratedViewModel = RatedViewModel(),
                    games = gameUiState.games,
                    gameUiState = gameUiState

                )
                else -> ErrorScreen(retryAction, modifier)
            }
        } else {
            Box(modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(stringResource( R.string.no_favorite_games))
            }
        }
    }
}


