package com.example.myapplication.Home

import android.content.Context
import android.content.Intent
import com.example.myapplication.Movies.MovieActivity

class HomeRouter(private val presenter: HomeContract.Presenter, private val context: Context): HomeContract.Router {
    override fun showMovies(category: String) {
        val intent = Intent(context, MovieActivity::class.java)
        intent.putExtra("movieCategory", category)
        context.startActivity(intent)
    }
}