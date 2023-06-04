package com.example.games1.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.games1.Games1Application
import com.example.games1.data.GameRepository
import com.example.games1.model.Game
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed interface GameUiState {
    data class Success(val games: List<Game>) : GameUiState
    object Error : GameUiState
    object Loading : GameUiState
}

class GameViewModel(
    private val gameRepository: GameRepository

) : ViewModel() {


   var gameUiState: GameUiState by mutableStateOf(GameUiState.Loading)
      private set

    //init block:
    init {
        getGames()
    }

    //GAMES FROM
    fun getGames() {
        viewModelScope.launch {
            gameUiState = GameUiState.Loading
            gameUiState = try {
                GameUiState.Success(gameRepository.getGames())
            } catch (e: IOException) {
                GameUiState.Error
            } catch (e: HttpException) {
                GameUiState.Error
            }
        }
    }


    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as Games1Application)
                val gameRepository = application.container.gameRepository
                GameViewModel(gameRepository = gameRepository)
            }
        }
    }
}