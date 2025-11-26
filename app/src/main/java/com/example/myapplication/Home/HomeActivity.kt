package com.example.myapplication.Home

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.HomeActivityBinding

class HomeActivity: HomeContract.View, AppCompatActivity() {
    lateinit var presenter: HomeContract.Presenter
    private lateinit var binding: HomeActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = HomeActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setListener()

        presenter = HomePresenter(this)
        val interactor = HomeInteractor(presenter)
        val router = HomeRouter(presenter, this)
        presenter.setUp(router, interactor)
    }

    fun setListener(){
        binding.card1.setOnClickListener {
            presenter.showMovies(MoviesCategory.POPULAR.movieCategory)
        }
        binding.card2.setOnClickListener {
            presenter.showMovies(MoviesCategory.NOW_PLAYING.movieCategory)
        }
        binding.card3.setOnClickListener {
            presenter.showMovies(MoviesCategory.TOP_RATED.movieCategory)
        }
        binding.card4.setOnClickListener {
            presenter.showMovies(MoviesCategory.UPCOMING.movieCategory)
        }
    }

}