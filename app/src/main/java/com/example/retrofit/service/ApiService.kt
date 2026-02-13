package com.example.retrofit.service

import android.telecom.Call
import com.example.retrofit.data.model.CommentPost
import com.example.retrofit.data.model.Post
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    @GET("posts")
    suspend fun getPosts(): List<Post>

    @GET("posts/{id}")
    suspend fun getPostById(@Path("id") postId: Int): Post

    @POST("posts")
    suspend fun createPost(@Body newPost: Post): Post



    @GET("posts/{id}/comments")
    suspend fun getCommentsByPostId(@Path("id") postId: Int): List<CommentPost>
}
