package com.example.myapplication.Movies

import MovieResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ApiService {

    @GET("movie/popular?language=en-US")
    fun getPopularMovies(@Header("Authorization") auth: String, @Query("page") page: Int): Single<MovieResponse>

    @GET("movie/now_playing?language=en-US")
    fun getNowPlayingMovies(@Header("Authorization") auth: String, @Query("page") page: Int): Single<MovieResponse>

    @GET("movie/top_rated?language=en-US")
    fun getTopRatedMovies(@Header("Authorization") auth: String, @Query("page") page: Int): Single<MovieResponse>

    @GET("movie/upcoming?language=en-US")
    fun getUpcomingMovies(@Header("Authorization") auth: String, @Query("page") page: Int): Single<MovieResponse>


}