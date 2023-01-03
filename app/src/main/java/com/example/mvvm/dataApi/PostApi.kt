package com.example.mvvm.dataApi


import com.example.mvvm.model.PostItem
import io.reactivex.Single
import retrofit2.http.GET

interface PostApi {

    @GET("posts")
    fun getPosts() : Single<List<PostItem>>
}