package com.example.myapplication.Movies

import android.annotation.SuppressLint
import android.util.Log
import com.example.myapplication.BuildConfig.API_KEY
import com.example.myapplication.Home.MoviesCategory
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MovieInteractor(private val presenter: MovieContract.Presenter): MovieContract.Interactor {
    private val disposable= CompositeDisposable()
    private val apiKey = API_KEY

    @SuppressLint("CheckResult")
    override fun fetchData(page: Int, movieCategory: String) {
        when (movieCategory) {
            MoviesCategory.POPULAR.movieCategory -> fetchPopularMovies(page)
            MoviesCategory.NOW_PLAYING.movieCategory -> fetchNowPlayingMovies(page)
            MoviesCategory.UPCOMING.movieCategory -> fetchUpcomingMovies(page)
            MoviesCategory.TOP_RATED.movieCategory -> fetchTopRatedMovies(page)
            else -> {
                Log.d("rupa", "Invalid movie category: $movieCategory")
                presenter.onError()
            }
        }
    }

    fun fetchPopularMovies(page: Int){
        val a = ApiClient.apiService.getPopularMovies(auth = apiKey, page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { responses ->
                    presenter.onSuccess(responses)
                    Log.d("rupa","rupa got result $responses")
                },
                {error ->
                    presenter.onError()
                    Log.d("rupa","rupa got error $error")
                }
            )

        disposable.add(a)
    }

    fun fetchNowPlayingMovies(page: Int){
        val a = ApiClient.apiService.getNowPlayingMovies(auth = apiKey, page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { responses ->
                    presenter.onSuccess(responses)
                    Log.d("rupa","rupa got result $responses")
                },
                {error ->
                    presenter.onError()
                    Log.d("rupa","rupa got error $error")
                }
            )

        disposable.add(a)
    }

    fun fetchUpcomingMovies(page: Int){
        val a = ApiClient.apiService.getUpcomingMovies(auth = apiKey, page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { responses ->
                    presenter.onSuccess(responses)
                    Log.d("rupa","rupa got result $responses")
                },
                {error ->
                    presenter.onError()
                    Log.d("rupa","rupa got error $error")
                }
            )

        disposable.add(a)
    }

    fun fetchTopRatedMovies(page: Int){
        val a = ApiClient.apiService.getTopRatedMovies(auth = apiKey, page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { responses ->
                    presenter.onSuccess(responses)
                    Log.d("rupa","rupa got result $responses")
                },
                {error ->
                    presenter.onError()
                    Log.d("rupa","rupa got error $error")
                }
            )

        disposable.add(a)
    }

    override fun clear() {
        disposable.clear()
    }
}