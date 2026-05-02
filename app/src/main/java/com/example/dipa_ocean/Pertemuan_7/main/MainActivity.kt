package com.example.dipa_ocean.Pertemuan_7.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.dipa_ocean.R
import com.example.dipa_ocean.databinding.ActivityMainNavBinding
import com.example.dipa_ocean.Pertemuan_7.home.HomeFragment
import com.example.dipa_ocean.Pertemuan_7.about.AboutFragment
import com.example.dipa_ocean.Pertemuan_7.profile.ProfileFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainNavBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainNavBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Load default fragment
        loadFragment(HomeFragment())

        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    loadFragment(HomeFragment())
                    true
                }
                R.id.navigation_about -> {
                    loadFragment(AboutFragment())
                    true
                }
                R.id.navigation_profile -> {
                    loadFragment(ProfileFragment())
                    true
                }
                else -> false
            }
        }
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.nav_host_fragment, fragment)
            .commit()
    }
}