package com.example.retrofit.ui.viewmodel

import androidx.lifecycle.*
import com.example.retrofit.data.model.CommentPost
import com.example.retrofit.data.model.Post
import com.example.retrofit.repository.PostRepository
import com.example.retrofit.service.RetrofitClient
import kotlinx.coroutines.launch

class PostViewModel : ViewModel() {

    private val repository = PostRepository()

    private val _posts = MutableLiveData<List<Post>>()
    val posts: LiveData<List<Post>> = _posts

    private val _post = MutableLiveData<Post?>()
    val post: LiveData<Post?> = _post


    private val _createdPost = MutableLiveData<Post?>()
    val createdPost: LiveData<Post?> = _createdPost

    fun createPost(post: Post) {
        viewModelScope.launch {
            try {
                val response = RetrofitClient.instance.createPost(post)
                _createdPost.value = response

            } catch (e: Exception) {
                _createdPost.value = null
            }
        }
    }


    private val _comments = MutableLiveData<List<CommentPost>>()
    val comments: LiveData<List<CommentPost>> = _comments

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
