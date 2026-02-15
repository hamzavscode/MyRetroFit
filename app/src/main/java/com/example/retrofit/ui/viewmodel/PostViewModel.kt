package com.example.retrofit.ui.viewmodel

import androidx.lifecycle.*
import com.example.retrofit.data.model.CommentPost
import com.example.retrofit.data.model.Post
import com.example.retrofit.repository.PostRepository
import com.example.retrofit.service.RetrofitClient
import kotlinx.coroutines.launch
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.retrofit.repository.CreateRepo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostViewModel : ViewModel() {

    private val repository = PostRepository()
    private val repo= CreateRepo()

    private val _posts = MutableLiveData<List<Post>>()
    val posts: LiveData<List<Post>> = _posts

    private val _post = MutableLiveData<Post?>()
    val post: LiveData<Post?> = _post
    private val _comments = MutableLiveData<List<CommentPost>>()
    val comments: LiveData<List<CommentPost>> = _comments
    private val _createdPost = MutableLiveData<Post?>()
    val createdPost: LiveData<Post?> = _createdPost



    fun createPost(post: Post) {
        repo.createPost(post).enqueue(object : Callback<Post> {

            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                if (response.isSuccessful) {
                    _createdPost.value = response.body()
                }
            }

            override fun onFailure(call: Call<Post>, t: Throwable) {
                _createdPost.value = null
            }
        })
    }


    fun fetchCommentsByPostId(postId: Int) {
        viewModelScope.launch {
            _comments.value = repository.getCommentsByPostId(postId) ?: emptyList()
        }
    }



    fun fetchPosts() {
        viewModelScope.launch {
            _posts.value = repository.getPosts() ?: emptyList()
        }
    }

    fun fetchPostById(postId: Int) {
        viewModelScope.launch {
            _post.value = repository.getPostById(postId)
        }
    }
}
