package com.example.retrofit.ui.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofit.R
import com.example.retrofit.data.adapter.PostAdapter
import com.example.retrofit.data.model.Post
import com.example.retrofit.service.RetrofitCreate
import com.example.retrofit.ui.viewmodel.PostViewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: PostViewModel by viewModels()
    private lateinit var adapter: PostAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val btnFetch = findViewById<Button>(R.id.btnFetch)
        val btnSearch = findViewById<Button>(R.id.btnSearch)
        val tvPostDetails = findViewById<TextView>(R.id.tvPostDetails)
        val etPostId = findViewById<EditText>(R.id.etPostId)

        val etTitle = findViewById<EditText>(R.id.etTitle)
        val etBody = findViewById<EditText>(R.id.etBody)
        val btnCreate = findViewById<Button>(R.id.btnCreate)

        btnCreate.setOnClickListener {

            val title = etTitle.text.toString()
            val body = etBody.text.toString()

            if (title.isEmpty() || body.isEmpty()) {
                Toast.makeText(
                    this,
                    "Veuillez remplir tous les champs",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }


            val newPost = Post(
                userId = 1,
                id = 0,
                title = title,
                body = body
            )

            viewModel.createPost(newPost)
        }

        viewModel.createdPost.observe(this) { post ->
            if (post != null) {
                Toast.makeText(this, "Post créé avec succès", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "Erreur lors de la création", Toast.LENGTH_LONG).show()
            }
        }

        adapter = PostAdapter(emptyList())
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        btnFetch.setOnClickListener {
            viewModel.fetchPosts()
        }

        viewModel.posts.observe(this) { posts ->
            adapter.updateData(posts)
        }

        btnSearch.setOnClickListener {
            val postId = etPostId.text.toString().toIntOrNull()
            if (postId != null) {
                viewModel.fetchPostById(postId)
            } else {
                tvPostDetails.text = "Veuillez entrer un IDvalide."
            }
        }

        viewModel.post.observe(this) { post ->
            if (post != null) {
                tvPostDetails.text =
                    "ID: ${post.id}\nTitre: ${post.title}\nContenu: ${post.body}\n"
            } else {
                tvPostDetails.text = "Aucun post trouvé."
            }
        }

        tvPostDetails.setOnClickListener {

            val post = viewModel.post.value

            if (post != null) {
                val intent = Intent(this, detailPost::class.java)
                intent.putExtra("id", post.id)
                startActivity(intent)
            }
        }

    }
}
