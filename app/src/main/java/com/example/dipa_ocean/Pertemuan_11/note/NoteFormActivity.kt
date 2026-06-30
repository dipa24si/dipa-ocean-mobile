package com.example.dipa_ocean.Pertemuan_11.note

import android.Manifest
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.example.dipa_ocean.Pertemuan_11.data.AppDatabase
import com.example.dipa_ocean.Pertemuan_11.data.entity.NoteEntity
import com.example.dipa_ocean.databinding.ActivityNoteFormBinding
import kotlinx.coroutines.launch

class NoteFormActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNoteFormBinding
    private lateinit var db: AppDatabase

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            Toast.makeText(this, "Izin notifikasi diberikan", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Izin notifikasi ditolak", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNoteFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = AppDatabase.getInstance(this)

        checkNotificationPermission()

        binding.btnSaveNote.setOnClickListener {
            saveNote()
        }

        // Check if opened from notification
        val noteId = intent.getIntExtra("noteId", -1)
        if (noteId != -1) {
            loadNote(noteId)
        }
    }

    private fun checkNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
        }
    }

    private fun saveNote() {
        val title = binding.etTitle.text.toString()
        val content = binding.etContent.text.toString()
        val minutesStr = binding.etReminderMinutes.text.toString()

        if (title.isNotBlank() && content.isNotBlank()) {
            val reminderMinutes = minutesStr.toLongOrNull()
            val reminderTimestamp = if (reminderMinutes != null && reminderMinutes > 0) {
                System.currentTimeMillis() + (reminderMinutes * 60 * 1000)
            } else null

            lifecycleScope.launch {
                val note = NoteEntity(
                    title = title,
                    content = content,
                    createdAt = System.currentTimeMillis(),
                    reminderTimestamp = reminderTimestamp
                )
                val id = db.noteDao().insert(note).toInt()
                
                if (reminderTimestamp != null) {
                    scheduleNotification(id, title, "Agenda Bina Desa: $title", reminderTimestamp)
                    Toast.makeText(this@NoteFormActivity, "Agenda disimpan dengan pengingat $reminderMinutes menit", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this@NoteFormActivity, "Agenda disimpan", Toast.LENGTH_SHORT).show()
                }
                finish()
            }
        } else {
            Toast.makeText(this, "Isi semua kolom!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun scheduleNotification(noteId: Int, title: String, content: String, timestamp: Long) {
        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        
        // Check for exact alarm permission on Android 12+
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            if (!alarmManager.canScheduleExactAlarms()) {
                val intent = Intent(Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM)
                startContext(intent)
                return
            }
        }

        val intent = Intent(this, NotificationReceiver::class.java).apply {
            putExtra("title", title)
            putExtra("content", content)
            putExtra("noteId", noteId)
        }

        val pendingIntent = PendingIntent.getBroadcast(
            this,
            noteId,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            timestamp,
            pendingIntent
        )
    }

    private fun loadNote(id: Int) {
        lifecycleScope.launch {
            val note = db.noteDao().getNoteById(id)
            note?.let {
                binding.etTitle.setText(it.title)
                binding.etContent.setText(it.content)
                binding.btnSaveNote.text = "Update Agenda"
                // Disable reminder input for simplicity in this demo when viewing
                binding.etReminderMinutes.isEnabled = false
            }
        }
    }
    
    private fun startContext(intent: Intent) {
        startActivity(intent)
    }
}
