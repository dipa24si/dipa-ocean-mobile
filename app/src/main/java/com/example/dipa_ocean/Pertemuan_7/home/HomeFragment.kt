package com.example.dipa_ocean.Pertemuan_7.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.dipa_ocean.databinding.FragmentHomeBinding
import com.example.dipa_ocean.Pertemuan_6.main.WebViewActivity

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

        binding.btnWeb.setOnClickListener {
            val intent = Intent(requireContext(), WebViewActivity::class.java)
            startActivity(intent)
        }

        binding.btnKatalog.setOnClickListener {
            Toast.makeText(requireContext(), "Membuka Katalog UMKM", Toast.LENGTH_SHORT).show()
        }

        binding.btnEvent.setOnClickListener {
            Toast.makeText(requireContext(), "Membuka Jadwal Event", Toast.LENGTH_SHORT).show()
        }

        binding.btnMaps.setOnClickListener {
            Toast.makeText(requireContext(), "Membuka Lokasi Desa", Toast.LENGTH_SHORT).show()
        }

        // Chip selection listener
        binding.cgCategories.setOnCheckedChangeListener { group, checkedId ->
            if (checkedId != View.NO_ID) {
                val chipText = when (checkedId) {
                    group.getChildAt(0).id -> "Filter Kuliner"
                    group.getChildAt(1).id -> "Filter Kerajinan"
                    group.getChildAt(2).id -> "Filter Jasa"
                    group.getChildAt(3).id -> "Filter Wisata"
                    else -> "Filter"
                }
                Toast.makeText(requireContext(), chipText, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}