package com.example.dipa_ocean.Pertemuan_6.login

import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.dipa_ocean.databinding.ActivityRegisterBinding
import java.util.Calendar

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupDatePicker()
        setupDropdownAgama()

        binding.btnRegister.setOnClickListener {
            if (validateForm()) {
                saveToSharedPref()
                finish()
            }
        }
    }

    private fun setupDatePicker() {
        binding.etTanggalLahir.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDay ->
                val date = "$selectedDay/${selectedMonth + 1}/$selectedYear"
                binding.etTanggalLahir.setText(date)
            }, year, month, day).show()
        }
    }

    private fun setupDropdownAgama() {
        val agamaList = arrayOf("Islam", "Kristen", "Katolik", "Hindu", "Budha", "Konghucu")
        val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, agamaList)
        binding.actvAgama.setAdapter(adapter)
    }

    private fun validateForm(): Boolean {
        var isValid = true

        val nama = binding.etNama.text.toString()
        val tglLahir = binding.etTanggalLahir.text.toString()
        val agama = binding.actvAgama.text.toString()
        val username = binding.etUsername.text.toString()
        val password = binding.etPassword.text.toString()
        val confirmPassword = binding.etConfirmPassword.text.toString()
        val genderId = binding.rgGender.checkedRadioButtonId

        if (nama.isEmpty()) {
            binding.tilNama.error = "Nama tidak boleh kosong"
            isValid = false
        } else {
            binding.tilNama.error = null
        }

        if (tglLahir.isEmpty()) {
            binding.tilTanggalLahir.error = "Tanggal lahir tidak boleh kosong"
            isValid = false
        } else {
            binding.tilTanggalLahir.error = null
        }

        if (genderId == -1) {
            // Error handling for RadioButton since it doesn't have TIL error
            isValid = false
        }

        if (agama.isEmpty()) {
            binding.tilAgama.error = "Agama tidak boleh kosong"
            isValid = false
        } else {
            binding.tilAgama.error = null
        }

        if (username.isEmpty()) {
            binding.tilUsername.error = "Username tidak boleh kosong"
            isValid = false
        } else {
            binding.tilUsername.error = null
        }

        if (password.isEmpty()) {
            binding.tilPassword.error = "Password tidak boleh kosong"
            isValid = false
        } else {
            binding.tilPassword.error = null
        }

        if (confirmPassword.isEmpty()) {
            binding.tilConfirmPassword.error = "Konfirmasi password tidak boleh kosong"
            isValid = false
        } else if (password != confirmPassword) {
            binding.tilConfirmPassword.error = "Password tidak sama"
            isValid = false
        } else {
            binding.tilConfirmPassword.error = null
        }

        return isValid
    }

    private fun saveToSharedPref() {
        val sharedPref = getSharedPreferences("user_pref", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putString("reg_nama", binding.etNama.text.toString())
        editor.putString("reg_tgl_lahir", binding.etTanggalLahir.text.toString())
        editor.putString("reg_agama", binding.actvAgama.text.toString())
        editor.putString("reg_username", binding.etUsername.text.toString())
        editor.putString("reg_password", binding.etPassword.text.toString())

        val gender = if (binding.rbLaki.isChecked) "Laki-laki" else "Perempuan"
        editor.putString("reg_gender", gender)

        editor.apply()
    }
}
