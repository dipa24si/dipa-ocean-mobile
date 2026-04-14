package com.example.dipa_ocean.Pertemuan_4.common

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.dipa_ocean.databinding.ActivityCommonDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCommonDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCommonDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val title = intent.getStringExtra("EXTRA_TITLE") ?: "Detail"
        val desc = intent.getStringExtra("EXTRA_DESC") ?: ""

        binding.tvDetailTitle.text = title
        binding.tvDetailDesc.text = desc

        binding.btnBack.setOnClickListener {
            finish()
        }
    }
}