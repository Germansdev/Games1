package com.example.games1.data

import com.example.games1.model.Game
import com.example.games1.network.GameApiService


class DefaultGameRepository(
    private val gameApiService: GameApiService
): GameRepository {
    override suspend fun getGames(): List<Game> {
        return gameApiService.getGames()
    }
}