package com.example.dipa_ocean.Pertemuan_6.splash

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.dipa_ocean.databinding.ActivitySplashScreenBinding
import com.example.dipa_ocean.Pertemuan_6.login.LoginActivity
import com.example.dipa_ocean.Pertemuan_7.main.MainActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPref = getSharedPreferences("user_pref", Context.MODE_PRIVATE)
        val isLogin = sharedPref.getBoolean("isLogin", false)

        lifecycleScope.launch {
            delay(2000)
            if (isLogin) {
                startActivity(Intent(this@SplashScreenActivity, MainActivity::class.java))
            } else {
                startActivity(Intent(this@SplashScreenActivity, LoginActivity::class.java))
            }
            finish()
        }
    }
}