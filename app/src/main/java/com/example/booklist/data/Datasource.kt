package com.example.booklist.data

import android.content.Context
import com.example.booklist.R
import com.example.booklist.model.Book

class Datasource(val context: Context) {

    fun getBookTitleList(): Array<String> {
        return context.resources.getStringArray(R.array.book_title_array)
    }

    fun getAuthorList(): Array<String> {
        return context.resources.getStringArray(R.array.author_array)
    }

    fun getPageCountList(): Array<String> {
        return context.resources.getStringArray(R.array.page_count_array)
    }

    fun getGenreList(): Array<String> {
        return context.resources.getStringArray(R.array.genre_array)
    }

    fun getSummaryList(): Array<String> {
        return context.resources.getStringArray(R.array.summary_array)
    }

    fun loadBooks(): List<Book> {
        val titleList = getBookTitleList()
        val authorList = getAuthorList()
        val pageCountList = getPageCountList().map { it.toInt() }
        val genreList = getGenreList()
        val summaryList = getSummaryList()

        val books = mutableListOf<Book>()
        for (i in titleList.indices) {
            val book = Book(titleList[i], authorList[i], pageCountList[i], genreList[i], summaryList[i])
            books.add(book)
        }

        return books
    }
}
