package com.example.dipa_ocean.Pertemuan_7.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.dipa_ocean.R
import com.example.dipa_ocean.databinding.ItemUmkmBinding

class UmkmAdapter(private val listUmkm: List<Umkm>) : RecyclerView.Adapter<UmkmAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemUmkmBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemUmkmBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val umkm = listUmkm[position]
        holder.binding.apply {
            tvUmkmName.text = umkm.name
            tvUmkmCategory.text = umkm.category
            tvUmkmDesc.text = umkm.description
            
            // Menggunakan Glide dengan konfigurasi lebih kuat
            Glide.with(ivUmkm.context)
                .load(umkm.imageUrl)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.ic_ocean_logo)
                .error(android.R.drawable.stat_notify_error) // Menggunakan icon sistem yang berbeda untuk penanda
                .into(ivUmkm)
            
            // Memastikan tidak ada filter warna yang menghalangi gambar
            ivUmkm.imageTintList = null
        }
    }

    override fun getItemCount(): Int = listUmkm.size
}
