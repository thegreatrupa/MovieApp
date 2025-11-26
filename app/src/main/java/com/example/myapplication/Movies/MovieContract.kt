package com.example.myapplication.Movies

import Movie
import MovieResponse
import android.R

interface MovieContract {

    interface View {
        fun onSuccess(responses: MovieResponse)
        fun onError()
        fun onItemClick(movie: Movie)
    }

    interface Presenter {
        fun setUp(router: MovieContract.Router, interactor: MovieContract.Interactor)
        fun onSuccess(responses: MovieResponse)
        fun onError()
        fun onItemClick()
        fun fetchData(page: Int, movieCategory: String)
        fun onDestroy()

    }

    interface Router {
        fun showDetailPage()
    }

    interface Interactor {
        fun fetchData(page: Int, movieCategory: String)
        fun clear()
    }
}