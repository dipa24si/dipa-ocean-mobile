package com.example.dipa_ocean.Pertemuan_11.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "umkm")
data class UmkmEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val category: String,
    val description: String,
    val imageUrl: String? = null
)
