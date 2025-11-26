package com.example.myapplication.Movies

import Movie
import MovieResponse
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.BuildConfig.IMAGE_BASE_URL
import com.example.myapplication.Home.MoviesCategory
import com.example.myapplication.R
import com.example.myapplication.databinding.MovieCardBinding
import com.example.myapplication.databinding.MoviesActivityBinding

class MovieActivity: MovieContract.View, AppCompatActivity() {
    private lateinit var presenter: MovieContract.Presenter
    private val movieList = mutableListOf<Movie>()
    private lateinit var binding: MoviesActivityBinding
    private var page = 1
    var movieCategory: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MoviesActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        recyclerView.adapter = MovieAdapter(movieList) {
            selectedMovie -> onItemClick(selectedMovie)
        }


        presenter = MoviePresenter(this)
        val interactor = MovieInteractor(presenter)
        val router = MovieRouter(presenter)
        presenter.setUp(router, interactor)

        movieCategory = intent.getStringExtra("movieCategory").toString() ?: "popular"
        presenter.fetchData(page, movieCategory!!)
        setListener()
    }

    private fun setListener(){
        binding.title.text = getTitleText()
        binding.recyclerView.addOnScrollListener(object: RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager as GridLayoutManager
                val totalItemCount = layoutManager.itemCount
                val lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()

                if(lastVisibleItemPosition == totalItemCount-1){
                    onRecyclerViewBottomReached()
                }
            }
        })
    }

    fun getTitleText(): String {
        return when(movieCategory){
            MoviesCategory.NOW_PLAYING.movieCategory -> "Now Playing Movies"
            MoviesCategory.POPULAR.movieCategory -> "Popular Movies"
            MoviesCategory.TOP_RATED.movieCategory -> "Top Rated Movies"
            MoviesCategory.UPCOMING.movieCategory -> "Upcoming Movies"
            else -> "Popular Movies"
        }
    }

    private fun onRecyclerViewBottomReached(){
        if(page < 10) presenter.fetchData(++page, movieCategory!!)
    }

    class MovieAdapter(private val movies: List<Movie>, private val onItemClick: (Movie) -> Unit): RecyclerView.Adapter<MovieAdapter.MovieViewHolder>(){
        inner class MovieViewHolder(val binding: MovieCardBinding) : RecyclerView.ViewHolder(binding.root)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
            val binding = MovieCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return MovieViewHolder(binding)
        }

        override fun onBindViewHolder(
            holder: MovieViewHolder,
            position: Int,
        ) {
            val movie = movies[position]
            holder.binding.movieTitle.text = movie.originalTitle
            Glide.with(holder.itemView.context)
                .load(IMAGE_BASE_URL + movie.posterPath)
                .into(holder.binding.moviePoster)

            holder.itemView.setOnClickListener {
                onItemClick(movie)
            }
        }

        override fun getItemCount(): Int = movies.size
    }

    override fun onSuccess(responses: MovieResponse) {
        binding.loadingView.visibility = View.GONE
        movieList.addAll(responses.results)
        val recyclerView = binding.recyclerView
        recyclerView.adapter?.notifyDataSetChanged()
    }

    override fun onError() {

    }

    override fun onItemClick(movie: Movie) {
        val bottomSheet = MovieDetailsBottomSheet.newInstance(movie.originalTitle, movie.overview,
            movie.posterPath.toString()
        )
        bottomSheet.show(supportFragmentManager, "MovieDetailsBottomSheet")
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }
}