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

        binding.tvRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        binding.btnLogin.setOnClickListener {
            val usernameInput = binding.etUsername.text.toString()
            val passwordInput = binding.etPassword.text.toString()

            val savedUsername = sharedPref.getString("reg_username", null)
            val savedPassword = sharedPref.getString("reg_password", null)

            when {
                usernameInput.isEmpty() || passwordInput.isEmpty() -> {
                    Toast.makeText(this, "Harap isi username dan password", Toast.LENGTH_SHORT).show()
                }
                // Rule 1: username == password
                usernameInput == passwordInput -> {
                    performLogin(usernameInput)
                }
                // Rule 2: check against SharedPreferences
                usernameInput == savedUsername && passwordInput == savedPassword -> {
                    performLogin(usernameInput)
                }
                else -> {
                    showErrorDialog()
                }
            }
        }
    }

    private fun performLogin(username: String) {
        val sharedPref = getSharedPreferences("user_pref", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putBoolean("isLogin", true)
        editor.putString("username", username)
        editor.apply()

        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("EXTRA_NAME", username)
        startActivity(intent)
        finish()
    }

    private fun showErrorDialog() {
        AlertDialog.Builder(this)
            .setTitle("Login Gagal")
            .setMessage("Username atau password salah!")
            .setPositiveButton("Coba Lagi", null)
            .show()
    }
}
