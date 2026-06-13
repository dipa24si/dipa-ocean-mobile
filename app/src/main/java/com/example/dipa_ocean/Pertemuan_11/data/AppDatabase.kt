package com.example.dipa_ocean.Pertemuan_11.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.dipa_ocean.Pertemuan_11.data.dao.NoteDao
import com.example.dipa_ocean.Pertemuan_11.data.dao.UmkmDao
import com.example.dipa_ocean.Pertemuan_11.data.entity.NoteEntity
import com.example.dipa_ocean.Pertemuan_11.data.entity.UmkmEntity

@Database(
    entities = [NoteEntity::class, UmkmEntity::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun noteDao(): NoteDao
    abstract fun umkmDao(): UmkmDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                )
                    .fallbackToDestructiveMigration()
                    .build().also { INSTANCE = it }
            }
        }
    }
}
