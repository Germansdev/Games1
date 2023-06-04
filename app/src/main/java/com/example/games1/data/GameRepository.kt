package com.example.games1.data

import com.example.games1.model.Game


interface GameRepository {
    suspend fun getGames(): List<Game>
}