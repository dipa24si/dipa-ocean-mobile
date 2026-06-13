package com.example.dipa_ocean.Pertemuan_11.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.dipa_ocean.Pertemuan_11.data.entity.UmkmEntity

@Dao
interface UmkmDao {
    @Query("SELECT * FROM umkm")
    suspend fun getAll(): List<UmkmEntity>

    @Query("SELECT * FROM umkm WHERE category = :category")
    suspend fun getByCategory(category: String): List<UmkmEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(umkmList: List<UmkmEntity>)
}
