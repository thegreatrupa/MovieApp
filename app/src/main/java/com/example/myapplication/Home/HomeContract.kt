package com.example.myapplication.Home

class HomeContract {
    interface View{

    }

    interface Presenter{
        fun setUp(router: HomeContract.Router, interactor: HomeContract.Interactor)
        fun showMovies(category: String)
    }

    interface Interactor {

    }

    interface Router{
        fun showMovies(category: String)
    }
}