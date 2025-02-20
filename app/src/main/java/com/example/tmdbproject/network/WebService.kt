package com.example.tmdbproject.network

import com.example.tmdbproject.network.response.MoviesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WebService {
    @GET("now_playing")
    suspend fun getCurrentPlayingMovies(
        @Query("api_key") apiKey: String
    ): Response<MoviesResponse>

    @GET("popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String
    ): Response<MoviesResponse>

}