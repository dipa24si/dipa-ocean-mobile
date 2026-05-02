package com.example.dipa_ocean.Pertemuan_7.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.example.dipa_ocean.databinding.FragmentHomeBinding
import com.example.dipa_ocean.Pertemuan_6.login.LoginActivity
import com.example.dipa_ocean.Pertemuan_6.main.WebViewActivity
import com.google.android.material.snackbar.Snackbar

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnCustom1.setOnClickListener {
            val intent = Intent(requireContext(), WebViewActivity::class.java)
            startActivity(intent)
        }

        binding.btnLogout.setOnClickListener {
            showLogoutDialog()
        }
    }

    private fun showLogoutDialog() {
        val sharedPref = requireContext().getSharedPreferences("user_pref", Context.MODE_PRIVATE)
        AlertDialog.Builder(requireContext())
            .setTitle("Konfirmasi Logout")
            .setMessage("Apakah Anda yakin ingin keluar dari Bina Desa?")
            .setPositiveButton("Ya") { _, _ ->
                val editor = sharedPref.edit()
                editor.clear()
                editor.apply()

                val intent = Intent(requireContext(), LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }
            .setNegativeButton("Tidak") { _, _ ->
                Snackbar.make(binding.root, "Logout dibatalkan", Snackbar.LENGTH_SHORT).show()
            }
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}