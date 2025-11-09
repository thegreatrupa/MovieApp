package com.example.myapplication.home

import android.annotation.SuppressLint
import android.util.Log
import com.example.myapplication.BuildConfig.API_KEY
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MovieInteractor(private val presenter: MovieContract.Presenter): MovieContract.Interactor {
    private val disposable= CompositeDisposable()
    private val apiKey = API_KEY

    @SuppressLint("CheckResult")
    override fun fetchData(page: Int) {
        val a = ApiClient.apiService.getMovies(auth = apiKey, page)
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