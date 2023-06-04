package com.example.games1.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.games1.Games1Application
import com.example.games1.model.Game
import com.example.games1.ui.GameUiState
import com.example.games1.ui.GameViewModel
import kotlinx.coroutines.launch

class PlayViewModel : ViewModel() {
    //play variables:

    var playedGames: MutableList<Game> by mutableStateOf(mutableListOf<Game>())
        private set

    var playedUiState: GameUiState by mutableStateOf(GameUiState.Loading)
        private set




    fun isGamePlayed(game: Game): Boolean {
        return !playedGames.filter { x -> x.id == game.id }.isEmpty()
    }


    fun addPlayedGame(game: Game) {
        if (!isGamePlayed(game)) {
            playedGames.add(game)
            playedGamesUpdated()
        }
    }

    fun removePlayedGame(game: Game) {
        playedGames.removeIf { it.id == game.id }
        playedGamesUpdated()
    }


    private fun playedGamesUpdated() {
        viewModelScope.launch {
            playedUiState = GameUiState.Loading
            playedUiState = GameUiState.Success(playedGames)

        }
    }

    //for ShareViewModel:


    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[

                        ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as Games1Application)
                val gameRepository = application.container.gameRepository
                GameViewModel(gameRepository = gameRepository)
               // PlayViewModel (gameRepository = gameRepository)
            }
        }
    }
}
