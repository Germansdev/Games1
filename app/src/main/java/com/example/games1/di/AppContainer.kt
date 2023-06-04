package com.example.games1.di

import com.example.games1.data.GameRepository
import com.example.games1.network.GameApiService


interface AppContainer {
    val gameApiService: GameApiService
    val gameRepository: GameRepository
}