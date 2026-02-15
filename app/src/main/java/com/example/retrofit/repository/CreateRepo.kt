package com.example.retrofit.repository

import com.example.retrofit.data.model.Post
import com.example.retrofit.service.RetrofitClient
import com.example.retrofit.service.RetrofitCreate
import retrofit2.Call

class CreateRepo {

    fun createPost(post: Post): Call<Post> {
        return RetrofitCreate.instance.createPost(post)
    }

}