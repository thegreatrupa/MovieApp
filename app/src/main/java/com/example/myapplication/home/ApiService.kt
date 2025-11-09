package com.example.myapplication.home

import MovieResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ApiService {

    @GET("movie/now_playing?language=en-US")
    fun getMovies(@Header("Authorization") auth: String, @Query("page") page: Int): Single<MovieResponse>


}