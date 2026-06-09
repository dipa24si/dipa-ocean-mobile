package com.example.dipa_ocean.Pertemuan_11.news

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    // Alamat API yang valid untuk berita ekonomi
    @GET("api/antara-news/ekonomi")
    fun getNews(): Call<NewsResponse>
}
