package com.example.myapplication.home

import Movie
import MovieResponse

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
        fun fetchData(page: Int)
        fun onDestroy()

    }

    interface Router {
        fun showDetailPage()
    }

    interface Interactor {
        fun fetchData(page: Int)
        fun clear()
    }
}