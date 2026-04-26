package com.example.dipa_ocean.Pertemuan_4.rumus

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.dipa_ocean.databinding.ActivityRumusBinding

class RumusActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRumusBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRumusBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Setup Toolbar
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        val title = intent.getStringExtra("EXTRA_TITLE") ?: "Rumus Bangun"
        binding.tvRumusTitle.text = title

        // HITUNG KUBUS
        binding.btnHitungLuasKubus.setOnClickListener {
            val sisiStr = binding.etSisi.text.toString()
            if (sisiStr.isNotEmpty()) {
                val sisi = sisiStr.toDouble()
                val hasil = 6 * (sisi * sisi)
                binding.tvHasil.text = "Hasil Luas Kubus: $hasil"
            } else {
                Toast.makeText(this, "Masukkan sisi kubus", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnHitungVolumeKubus.setOnClickListener {
            val sisiStr = binding.etSisi.text.toString()
            if (sisiStr.isNotEmpty()) {
                val sisi = sisiStr.toDouble()
                val hasil = Math.pow(sisi, 3.0)
                binding.tvHasil.text = "Hasil Volume Kubus: $hasil"
            } else {
                Toast.makeText(this, "Masukkan sisi kubus", Toast.LENGTH_SHORT).show()
            }
        }

        // HITUNG SEGITIGA (Luas)
        binding.btnHitungLuasSegitiga.setOnClickListener {
            val alasStr = binding.etAlas.text.toString()
            val tinggiStr = binding.etTinggi.text.toString()
            
            if (alasStr.isNotEmpty() && tinggiStr.isNotEmpty()) {
                val hasil = 0.5 * alasStr.toDouble() * tinggiStr.toDouble()
                binding.tvHasil.text = "Hasil Luas Segitiga: $hasil"
            } else {
                Toast.makeText(this, "Masukkan alas dan tinggi segitiga", Toast.LENGTH_SHORT).show()
            }
        }

        binding.toolbar.setNavigationOnClickListener {
            finish()
        }
    }
}
