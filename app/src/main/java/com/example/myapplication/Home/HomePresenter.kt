package com.example.myapplication.Home

class HomePresenter(private val view: HomeContract.View): HomeContract.Presenter {
    lateinit var router: HomeContract.Router
    lateinit var interactor: HomeContract.Interactor

    override fun setUp(
        router: HomeContract.Router,
        interactor: HomeContract.Interactor
    ){
        this.router = router
        this.interactor = interactor
    }

    override fun showMovies(category: String) {
        router.showMovies(category)
    }
}