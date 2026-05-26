package com.example.dipa_ocean.Pertemuan_7.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view. View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dipa_ocean.databinding.FragmentHomeBinding
import com.example.dipa_ocean.Pertemuan_6.main.WebViewActivity
import com.google.android.material.tabs.TabLayout

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    // Menggunakan URL gambar dari picsum.photos agar lebih stabil
    private val allUmkm = listOf(
        Umkm(1, "Kripik Singkong Renyah", "Kuliner", 
            "Kripik singkong asli desa dengan bumbu rempah pilihan.",
            "https://picsum.photos/id/429/400/400"),
        Umkm(2, "Anyaman Bambu Estetik", "Kerajinan", 
            "Tas dan dekorasi rumah dari bambu berkualitas.",
            "https://picsum.photos/id/102/400/400"),
        Umkm(3, "Sambal Bawang Pedas", "Kuliner", 
            "Sambal rumahan tanpa pengawet, pedasnya nampol!",
            "https://picsum.photos/id/292/400/400"),
        Umkm(4, "Gantungan Kunci Kayu", "Kerajinan", 
            "Souvenir khas desa dari kayu mahoni ukir.",
            "https://picsum.photos/id/1060/400/400"),
        Umkm(5, "Kopi Arabika Desa", "Kuliner", 
            "Biji kopi pilihan yang diproses secara tradisional.",
            "https://picsum.photos/id/425/400/400")
    )

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

        setupRecyclerView(allUmkm)
        setupTabLayout()

        binding.btnWeb.setOnClickListener {
            val intent = Intent(requireContext(), WebViewActivity::class.java)
            startActivity(intent)
        }

        binding.btnKatalog.setOnClickListener {
            Toast.makeText(requireContext(), "Membuka Katalog UMKM", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupRecyclerView(list: List<Umkm>) {
        binding.rvUmkm.layoutManager = LinearLayoutManager(requireContext())
        binding.rvUmkm.adapter = UmkmAdapter(list)
    }

    private fun setupTabLayout() {
        binding.tabLayoutUmkm.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val filteredList = when (tab?.position) {
                    1 -> allUmkm.filter { it.category == "Kuliner" }
                    2 -> allUmkm.filter { it.category == "Kerajinan" }
                    else -> allUmkm
                }
                setupRecyclerView(filteredList)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
