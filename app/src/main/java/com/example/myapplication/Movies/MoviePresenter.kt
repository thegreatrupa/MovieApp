package com.example.myapplication.Movies

import MovieResponse
import android.util.Log

class MoviePresenter(private val view: MovieContract.View): MovieContract.Presenter {

    private lateinit var router: MovieContract.Router
    private lateinit var interactor: MovieContract.Interactor

    override fun setUp(
        router: MovieContract.Router,
        interactor: MovieContract.Interactor
    ) {
        this.router = router
        this.interactor = interactor
    }


    override fun onSuccess(responses: MovieResponse) {
        Log.d("", "")
        view.onSuccess(responses)
    }

    override fun onError() {
        view.onError()
    }

    override fun onItemClick() {
        TODO("Not yet implemented")
    }

    override fun fetchData(page: Int, movieCategory: String) {
        interactor.fetchData(page, movieCategory)
    }

    override fun onDestroy(){
        interactor.clear()
    }
}