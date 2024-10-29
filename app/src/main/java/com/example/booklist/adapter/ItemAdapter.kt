package com.example.booklist.adapter

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.booklist.BookDetailActivity
import com.example.booklist.R
import com.example.booklist.model.Book

class ItemAdapter(private val dataset: List<Book>) :
    RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleView: TextView = view.findViewById(R.id.titleView)
        val authorView: TextView = view.findViewById(R.id.authorView)
        val favoriteIcon: ImageView = view.findViewById(R.id.favoriteIcon)

        private lateinit var book: Book
        private val sharedPreferences =
            view.context.getSharedPreferences("FavoriteBooksPrefs", Context.MODE_PRIVATE)

        init {
            view.setOnClickListener {
                val context = it.context
                val intent = Intent(context, BookDetailActivity::class.java)
                intent.putExtra("BOOK_KEY", book)
                context.startActivity(intent)
            }
        }

        fun animateFavoriteIcon(favoriteIcon: ImageView) {
            val scaleUpX = ObjectAnimator.ofFloat(favoriteIcon, View.SCALE_X, 2f)
            val scaleUpY = ObjectAnimator.ofFloat(favoriteIcon, View.SCALE_Y, 2f)
            val scaleDownX = ObjectAnimator.ofFloat(favoriteIcon, View.SCALE_X, 1f)
            val scaleDownY = ObjectAnimator.ofFloat(favoriteIcon, View.SCALE_Y, 1f)

            scaleUpX.duration = 200
            scaleUpY.duration = 200
            scaleDownX.duration = 200
            scaleDownY.duration = 200

            val animatorSet = AnimatorSet()
            animatorSet.play(scaleUpX).with(scaleUpY)
            animatorSet.play(scaleDownX).with(scaleDownY).after(scaleUpX)

            animatorSet.start()
        }


        fun bind(book: Book) {
            this.book = book
            titleView.text = book.title
            authorView.text = book.author

            updateFavoriteIcon(isBookFavorite())

            favoriteIcon.setOnClickListener {
                val newFavoriteStatus = !isBookFavorite()
                with(sharedPreferences.edit()) {
                    putBoolean(book.title, newFavoriteStatus)
                    apply()
                }
                updateFavoriteIcon(newFavoriteStatus)
                animateFavoriteIcon(favoriteIcon)

            }
        }

        private fun isBookFavorite(): Boolean {
            return sharedPreferences.getBoolean(book.title, false)
        }

        private fun updateFavoriteIcon(isFavorite: Boolean) {
            if (isFavorite) {
                favoriteIcon.setImageResource(R.drawable.baseline_favorite_border_red)
            } else {
                favoriteIcon.setImageResource(R.drawable.baseline_favorite_border_gray)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return ItemViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(dataset[position])
    }

    override fun getItemCount() = dataset.size
}
