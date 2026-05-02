package com.example.dipa_ocean.Pertemuan_4.welcome

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.dipa_ocean.databinding.ActivityWelcomeBinding
import com.example.dipa_ocean.Pertemuan_7.main.MainActivity

class WelcomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWelcomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val name = intent.getStringExtra("EXTRA_NAME") ?: "User"
        binding.tvWelcomeName.text = name

        binding.root.postDelayed({
            // Berpindah ke MainActivity Pertemuan 7 (yang ada Bottom Navigation)
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("EXTRA_NAME", name)
            startActivity(intent)
            finish()
        }, 2000)
    }
}