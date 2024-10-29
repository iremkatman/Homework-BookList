package com.example.booklist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.booklist.model.Book
import com.example.booklist.databinding.ActivityBookDetailBinding
import kotlin.random.Random

class BookDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityBookDetailBinding = DataBindingUtil.setContentView(this, R.layout.activity_book_detail)

        val selectedBook = intent.getSerializableExtra("BOOK_KEY") as Book

        binding.book = selectedBook
        val randomRating = Random.nextFloat() * 5
        binding.ratingBar.rating = randomRating
    }
}
