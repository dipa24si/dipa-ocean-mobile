package com.example.dipa_ocean.Pertemuan_7.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.dipa_ocean.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Data untuk ListView (Memenuhi syarat ArrayAdapter)
        val settingsItems = arrayOf(
            "Kebijakan Privasi",
            "Syarat dan Ketentuan",
            "Pusat Bantuan",
            "Rating Aplikasi",
            "Versi Aplikasi v1.0.0"
        )

        // Menggunakan ArrayAdapter sederhana (Memenuhi syarat tugas)
        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_list_item_1,
            settingsItems
        )

        binding.lvSettings.adapter = adapter

        // Listener Klik ListView
        binding.lvSettings.setOnItemClickListener { _, _, position, _ ->
            val item = settingsItems[position]
            Toast.makeText(requireContext(), "Membuka: $item", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}