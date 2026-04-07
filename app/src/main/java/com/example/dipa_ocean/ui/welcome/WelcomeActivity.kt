package com.example.dipa_ocean.ui.welcome

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.dipa_ocean.databinding.ActivityWelcomeBinding

class WelcomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWelcomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val name = intent.getStringExtra("EXTRA_NAME") ?: "User"
        binding.tvWelcomeName.text = name
    }
}