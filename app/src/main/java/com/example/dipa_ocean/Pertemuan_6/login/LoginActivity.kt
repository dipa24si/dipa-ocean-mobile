package com.example.dipa_ocean.Pertemuan_6.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.dipa_ocean.databinding.ActivityLoginBinding
import com.example.dipa_ocean.Pertemuan_7.main.MainActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPref = getSharedPreferences("user_pref", Context.MODE_PRIVATE)

        binding.btnLogin.setOnClickListener {
            val username = binding.etUsername.text.toString()
            val password = binding.etPassword.text.toString()

            when {
                username.isEmpty() || password.isEmpty() -> {
                    Toast.makeText(this, "Harap isi username dan password", Toast.LENGTH_SHORT).show()
                }
                username == password -> {
                    // Simpan status login di SharedPreferences
                    val editor = sharedPref.edit()
                    editor.putBoolean("isLogin", true)
                    editor.putString("username", username)
                    editor.apply()

                    val intent = Intent(this, MainActivity::class.java)
                    intent.putExtra("EXTRA_NAME", username)
                    startActivity(intent)
                    finish()
                }
                else -> {
                    showErrorDialog()
                }
            }
        }
    }

    private fun showErrorDialog() {
        AlertDialog.Builder(this)
            .setTitle("Login Gagal")
            .setMessage("Username dan password harus sama!")
            .setPositiveButton("Coba Lagi", null)
            .show()
    }
}