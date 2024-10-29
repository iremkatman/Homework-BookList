package com.example.booklist.model

import java.io.Serializable

data class Book(
    val title:String,
    val author:String,
    val pageCount:Int,
    val genre:String,
    val summary:String
) :Serializable
