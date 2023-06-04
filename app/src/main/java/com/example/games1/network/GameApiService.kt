package com.example.games1.network

import com.example.games1.model.Game
import retrofit2.http.GET


/**
 * A public interface that exposes the [getGames] method
 */
interface GameApiService {

    companion object {
       const val BASE_URL = "https://www.freetogame.com/api/"
    }
    /**
     * Returns a [List] of [Game] and this method can be called from a Coroutine.
     * The @GET annotation indicates that the "volumes" endpoint will be requested with the GET
     * HTTP method
     */
@GET("games")
suspend fun getGames (): List<Game>


}