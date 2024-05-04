package com.example.googlescholar

class Model {
    data class ArticleData(
        val title: String,
        val authors: String,
        val year: String,
        val citiations: Int,
        val link: String = "https://scholar.google.com"
    )
}