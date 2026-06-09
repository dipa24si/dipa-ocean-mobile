package com.example.dipa_ocean.Pertemuan_11.news

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dipa_ocean.databinding.ItemNewsBinding

class NewsAdapter(private val listNews: List<NewsPost>) : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemNewsBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val news = listNews[position]
        holder.binding.apply {
            tvNewsTitle.text = news.title ?: "No Title"
            
            // Format tanggal: YYYY-MM-DD
            val rawDate = news.isoDate
            tvNewsDate.text = if (rawDate != null && rawDate.length >= 10) {
                rawDate.substring(0, 10)
            } else {
                "-"
            }
            
            Glide.with(ivNews.context)
                .load(news.image?.medium)
                .placeholder(android.R.drawable.ic_menu_report_image)
                .error(android.R.drawable.ic_menu_report_image)
                .centerCrop()
                .into(ivNews)
        }
    }

    override fun getItemCount(): Int = listNews.size
}
