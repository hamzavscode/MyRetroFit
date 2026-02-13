package com.example.retrofit.ui.view

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.retrofit.R
import com.example.retrofit.ui.viewmodel.PostViewModel

class detailPost : AppCompatActivity() {

    private val viewModel: PostViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detail_post)

        val tvDetails = findViewById<TextView>(R.id.tvDetails)

        val postId = intent.getIntExtra("id", 0)

       tvDetails.text = "ID: $postId\n\nCommentaires:\n"

        viewModel.fetchCommentsByPostId(postId)

        viewModel.comments.observe(this) { comments ->
            if (comments.isNotEmpty()) {
                for (c in comments) {
                    val commentText = "Nom: ${c.name}\nEmail: ${c.email}\nComment: ${c.body}\n\n"
                    tvDetails.append(commentText)
                }
            } else {
                tvDetails.append("Aucun commentaire trouvé.")
            }
        }
    }
}


