package com.example.retrofit.service

import com.example.retrofit.data.model.Post
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiServiceCreate {

    @POST("posts")
    fun createPost(@Body newPost: Post): Call<Post>

}