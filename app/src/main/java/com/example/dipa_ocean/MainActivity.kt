package com.example.dipa_ocean

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    lateinit var etAlas: EditText
    lateinit var etTinggi: EditText
    lateinit var etSisi: EditText
    lateinit var btnLuas: Button
    lateinit var btnVolume: Button
    lateinit var tvHasil: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etAlas = findViewById(R.id.etAlas)
        etTinggi = findViewById(R.id.etTinggi)
        etSisi = findViewById(R.id.etSisi)
        btnLuas = findViewById(R.id.btnHitungLuas)
        btnVolume = findViewById(R.id.btnHitungVolume)
        tvHasil = findViewById(R.id.tvHasil)

        // HITUNG LUAS SEGITIGA
        btnLuas.setOnClickListener {

            val alasStr = etAlas.text.toString()
            val tinggiStr = etTinggi.text.toString()

            if (alasStr.isEmpty() || tinggiStr.isEmpty()) {
                tvHasil.text = "Masukkan alas & tinggi terlebih dahulu"
            } else {
                val alas = alasStr.toDouble()
                val tinggi = tinggiStr.toDouble()
                val luas = 0.5 * alas * tinggi
                tvHasil.text = "Luas Segitiga: $luas"
            }
        }

        // HITUNG VOLUME KUBUS
        btnVolume.setOnClickListener {

            val sisiStr = etSisi.text.toString()

            if (sisiStr.isEmpty()) {
                tvHasil.text = "Masukkan sisi terlebih dahulu"
            } else {
                val sisi = sisiStr.toDouble()
                val volume = sisi * sisi * sisi
                tvHasil.text = "Volume Kubus: $volume"
            }
        }
    }
}