package com.example.retrofit.repository

import com.example.retrofit.data.model.CommentPost
import com.example.retrofit.data.model.Post
import com.example.retrofit.service.RetrofitClient

class PostRepository {

    private val apiService = RetrofitClient.instance

    suspend fun getPosts(): List<Post>? {
        return try {
            apiService.getPosts()
        } catch (e: Exception) {
            null
        }
    }

    suspend fun getPostById(postId: Int): Post? {
        return try {
            apiService.getPostById(postId)
        } catch (e: Exception) {
            null
        }
    }

    suspend fun createPost(post: Post): Post? {
        return try {
            apiService.createPost(post)
        } catch (e: Exception) {
            null
        }
    }

    suspend fun getCommentsByPostId(postId: Int): List<CommentPost>? {
        return try {
            apiService.getCommentsByPostId(postId)
        } catch (e: Exception) {
            null
        }
    }



}
