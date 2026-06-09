package com.example.dipa_ocean.Pertemuan_11.onboarding

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.dipa_ocean.Pertemuan_6.login.LoginActivity
import com.example.dipa_ocean.databinding.ActivityOnBoardingBinding
import com.google.android.material.tabs.TabLayoutMediator

class OnBoardingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOnBoardingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnBoardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val onboardingItems = listOf(
            OnBoardingItem(
                "https://img.freepik.com/free-vector/gradient-liquid-abstract-background_23-2148897334.jpg",
                "Selamat Datang",
                "Dipa Ocean hadir untuk membantu memajukan UMKM di desa Anda dengan teknologi modern."
            ),
            OnBoardingItem(
                "https://img.freepik.com/free-vector/business-team-working-marketing-strategy_74855-6674.jpg",
                "Katalog UMKM",
                "Temukan berbagai produk unggulan mulai dari kuliner hingga kerajinan tangan khas desa."
            ),
            OnBoardingItem(
                "https://img.freepik.com/free-vector/global-data-concept-illustration_114360-3961.jpg",
                "Berita Terkini",
                "Dapatkan informasi dan berita terbaru seputar perkembangan ekonomi kreatif desa."
            )
        )

        val adapter = OnBoardingAdapter(onboardingItems)
        binding.viewPagerOnboarding.adapter = adapter

        // Menghubungkan TabLayout dengan ViewPager2 (Indikator Titik)
        TabLayoutMediator(binding.tabIndicator, binding.viewPagerOnboarding) { _, _ -> }.attach()

        binding.viewPagerOnboarding.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                // Jika posisi terakhir (indeks 2), ubah teks tombol
                if (position == onboardingItems.size - 1) {
                    binding.btnNext.text = "Ayo Mulai"
                } else {
                    binding.btnNext.text = "Selanjutnya"
                }
            }
        })

        binding.btnNext.setOnClickListener {
            if (binding.viewPagerOnboarding.currentItem < onboardingItems.size - 1) {
                binding.viewPagerOnboarding.currentItem += 1
            } else {
                markOnboardingFinished()
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
        }
    }

    private fun markOnboardingFinished() {
        val sharedPref = getSharedPreferences("onboarding_pref", Context.MODE_PRIVATE)
        sharedPref.edit().putBoolean("isFinished", true).apply()
    }
}
