package com.example.dipa_ocean.Pertemuan_7.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dipa_ocean.databinding.FragmentHomeBinding
import com.example.dipa_ocean.Pertemuan_6.main.WebViewActivity
import com.example.dipa_ocean.Pertemuan_11.news.*
import com.example.dipa_ocean.Pertemuan_11.data.AppDatabase
import com.example.dipa_ocean.Pertemuan_11.data.entity.UmkmEntity
import com.example.dipa_ocean.Pertemuan_7.home.pertemuan_13.ThirteenthActivity
import com.google.android.material.tabs.TabLayout
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var db: AppDatabase
    private val umkmList = mutableListOf<Umkm>()

    private val initialUmkm = listOf(
        UmkmEntity(name = "Kripik Singkong Renyah", category = "Kuliner", 
            description = "Kripik singkong asli desa dengan bumbu rempah pilihan.",
            imageUrl = "https://picsum.photos/id/429/400/400"),
        UmkmEntity(name = "Anyaman Bambu Estetik", category = "Kerajinan", 
            description = "Tas dan dekorasi rumah dari bambu berkualitas.",
            imageUrl = "https://picsum.photos/id/102/400/400"),
        UmkmEntity(name = "Sambal Bawang Pedas", category = "Kuliner", 
            description = "Sambal rumahan tanpa pengawet, pedasnya nampol!",
            imageUrl = "https://picsum.photos/id/292/400/400"),
        UmkmEntity(name = "Gantungan Kunci Kayu", category = "Kerajinan", 
            description = "Souvenir khas desa dari kayu mahoni ukir.",
            imageUrl = "https://picsum.photos/id/1060/400/400"),
        UmkmEntity(name = "Kopi Arabika Desa", category = "Kuliner", 
            description = "Biji kopi pilihan yang diproses secara tradisional.",
            imageUrl = "https://picsum.photos/id/425/400/400")
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

        db = AppDatabase.getInstance(requireContext())
        
        setupTabLayout()
        fetchUmkmData()
        
        showFallbackNews()
        getNews()

        binding.btnWeb.setOnClickListener {
            val intent = Intent(requireContext(), WebViewActivity::class.java)
            startActivity(intent)
        }

        binding.btnKatalog.setOnClickListener {
            Toast.makeText(requireContext(), "Membuka Katalog UMKM", Toast.LENGTH_SHORT).show()
        }

        binding.btnPertemuan13.setOnClickListener {
            val intent = Intent(requireContext(), ThirteenthActivity::class.java)
            startActivity(intent)
        }
    }

    private fun fetchUmkmData(category: String? = null) {
        lifecycleScope.launch {
            var data = if (category == null || category == "Semua") {
                db.umkmDao().getAll()
            } else {
                db.umkmDao().getByCategory(category)
            }

            if (data.isEmpty() && category == null) {
                db.umkmDao().insertAll(initialUmkm)
                data = db.umkmDao().getAll()
            }

            umkmList.clear()
            umkmList.addAll(data.map { 
                Umkm(it.id, it.name, it.category, it.description, it.imageUrl)
            })
            setupRecyclerView(umkmList)
        }
    }

    private fun getNews() {
        binding.progressBar.visibility = View.VISIBLE
        val client = ApiConfig.getApiService().getNews()
        client.enqueue(object : Callback<NewsResponse> {
            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                if (isAdded) {
                    binding.progressBar.visibility = View.GONE
                    if (response.isSuccessful) {
                        val posts = response.body()?.data
                        if (!posts.isNullOrEmpty()) {
                            setNewsData(posts)
                        }
                    }
                }
            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                if (isAdded) {
                    binding.progressBar.visibility = View.GONE
                    Log.e("HomeFragment", "API Failure: ${t.message}")
                }
            }
        })
    }

    private fun showFallbackNews() {
        val mockData = listOf(
            NewsPost("UMKM Desa Tembus Pasar Ekspor", null, null, "2024-05-20", NewsImage(null, "https://picsum.photos/id/1/400/300", null)),
            NewsPost("Pelatihan Digital Marketing Pemuda", null, null, "2024-05-19", NewsImage(null, "https://picsum.photos/id/2/400/300", null)),
            NewsPost("Inovasi Produk Olahan Singkong", null, null, "2024-05-18", NewsImage(null, "https://picsum.photos/id/3/400/300", null))
        )
        setNewsData(mockData)
    }

    private fun setNewsData(posts: List<NewsPost>) {
        val adapter = NewsAdapter(posts)
        binding.rvNews.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            this.adapter = adapter
        }
    }

    private fun setupRecyclerView(list: List<Umkm>) {
        binding.rvUmkm.layoutManager = LinearLayoutManager(requireContext())
        binding.rvUmkm.adapter = UmkmAdapter(list)
    }

    private fun setupTabLayout() {
        binding.tabLayoutUmkm.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val category = when (tab?.position) {
                    1 -> "Kuliner"
                    2 -> "Kerajinan"
                    else -> "Semua"
                }
                fetchUmkmData(category)
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
