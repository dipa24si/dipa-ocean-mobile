package com.example.dipa_ocean.Pertemuan_11.news

data class NewsResponse(
    val status: Int?,
    val messages: String?,
    val data: List<NewsPost>?
)

data class NewsPost(
    val title: String?,
    val link: String?,
    val contentSnippet: String?,
    val isoDate: String?,
    val image: NewsImage?
)

data class NewsImage(
    val small: String?,
    val medium: String?,
    val large: String?
)
