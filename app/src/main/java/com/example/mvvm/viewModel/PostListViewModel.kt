package com.example.mvvm.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvm.dataApi.PostInstance
import com.example.mvvm.model.PostItem
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class PostListViewModel : ViewModel() {
    var posts = MutableLiveData<List<PostItem>>()

    private val postService: PostInstance = PostInstance()
    private val disposable: CompositeDisposable = CompositeDisposable()

    fun refresh() {
        fetchDataFromApi()
    }

    private fun fetchDataFromApi() {
        disposable.add(
            postService.getPosts()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<PostItem>>() {
                    override fun onSuccess(postList: List<PostItem>) {
                        posts.value= postList
                        Log.d("VM", "onSuccess: $postList")
                    }

                    override fun onError(e: Throwable) {
                      e.printStackTrace()
                    }

                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}