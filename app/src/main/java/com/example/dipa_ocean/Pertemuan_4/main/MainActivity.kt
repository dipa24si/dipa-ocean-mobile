package com.example.dipa_ocean.Pertemuan_4.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.dipa_ocean.databinding.ActivityMainBinding
import com.example.dipa_ocean.Pertemuan_4.login.LoginActivity
import com.example.dipa_ocean.Pertemuan_4.common.DetailActivity
import com.example.dipa_ocean.Pertemuan_4.rumus.RumusActivity
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val username = intent.getStringExtra("EXTRA_NAME") ?: "User"
        binding.tvDashboardUser.text = "Halo, $username!"

        binding.btnRumus.setOnClickListener {
            val intent = Intent(this, RumusActivity::class.java)
            intent.putExtra("EXTRA_TITLE", "Rumus Bangun Ruang (Kubus)")
            intent.putExtra("EXTRA_DESC", "Hitung Luas Permukaan dan Volume Kubus dengan memasukkan panjang sisi.")
            startActivity(intent)
        }

        binding.btnCustom1.setOnClickListener {
            moveToPage("Dipa Ocean", "Halo 1")
        }

        binding.btnCustom2.setOnClickListener {
            moveToPage("Dipa Ocean", "Halo 2")
        }

        binding.btnLogout.setOnClickListener {
            showLogoutDialog()
        }
    }

    private fun moveToPage(title: String, desc: String) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("EXTRA_TITLE", title)
        intent.putExtra("EXTRA_DESC", desc)
        startActivity(intent)
    }

    private fun showLogoutDialog() {
        AlertDialog.Builder(this)
            .setTitle("Konfirmasi Logout")
            .setMessage("Apakah Anda yakin ingin keluar?")
            .setPositiveButton("Ya") { _, _ ->
                val intent = Intent(this, LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }
            .setNegativeButton("Tidak") { _, _ ->
                Snackbar.make(binding.root, "Logout dibatalkan", Snackbar.LENGTH_SHORT).show()
            }
            .show()
    }
}