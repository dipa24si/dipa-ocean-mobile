package com.example.dipa_ocean.Pertemuan_11.onboarding

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dipa_ocean.databinding.ItemOnboardingBinding

class OnBoardingAdapter(private val items: List<OnBoardingItem>) :
    RecyclerView.Adapter<OnBoardingAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemOnboardingBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemOnboardingBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.binding.apply {
            tvTitle.text = item.title
            tvDescription.text = item.description
            Glide.with(ivOnboarding.context)
                .load(item.imageUrl)
                .into(ivOnboarding)
        }
    }

    override fun getItemCount(): Int = items.size
}
