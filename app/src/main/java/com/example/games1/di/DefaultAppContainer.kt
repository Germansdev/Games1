package com.example.games1.di



import com.example.games1.data.GameRepository
import com.example.games1.data.DefaultGameRepository

import com.example.games1.network.GameApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class DefaultAppContainer: AppContainer {
    override val gameApiService: GameApiService by lazy {

        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(GameApiService.BASE_URL)
            .build()
            .create()
    }

    override val gameRepository: GameRepository by lazy {
       DefaultGameRepository (gameApiService)
    }

}