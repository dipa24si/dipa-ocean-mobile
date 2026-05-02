package com.example.dipa_ocean.Pertemuan_7.profile

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.example.dipa_ocean.databinding.FragmentProfileBinding
import com.example.dipa_ocean.Pertemuan_6.login.LoginActivity

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Kita biarkan nama dari XML (Dipa Tranggana) atau set manual di sini
        binding.tvDevName.text = "Dipa Tranggana"

        binding.btnLogoutProfile.setOnClickListener {
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
            .setNegativeButton("Tidak") { _, _ -> }
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}