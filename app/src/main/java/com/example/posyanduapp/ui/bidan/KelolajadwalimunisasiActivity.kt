package com.example.posyanduapp.ui.bidan

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ImageView
import com.example.posyanduapp.R
import com.example.posyanduapp.databinding.ActivityKelolajadwalimunisasiBinding
import com.example.posyanduapp.databinding.ActivityRujukanBinding
import java.util.*

class KelolajadwalimunisasiActivity : AppCompatActivity() {

    private lateinit var binding : ActivityKelolajadwalimunisasiBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKelolajadwalimunisasiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //datepicker
        val calendar = Calendar.getInstance()
        val year = calendar[Calendar.YEAR]
        val month = calendar[Calendar.MONTH]
        val day = calendar[Calendar.DAY_OF_MONTH]

        val btn_kembali = findViewById(R.id.btn_kembalikelolajadwal) as ImageView

        //dropdown nama anak
        val nama_anak = resources.getStringArray(R.array.daftar_namaanak)
        val adapter = ArrayAdapter(
            this,
            R.layout.dropdown_listanak,
            nama_anak
        )
        with(binding.kelolaimunisasiNamaanak){
            setAdapter(adapter)
        }

        val nama_vaksin = resources.getStringArray(R.array.imunisasi)
        val adaptervaksin = ArrayAdapter(
            this,
            R.layout.dropdown_listanak,
            nama_vaksin
        )
        with(binding.kelolaimunisasiNamavaksin){
            setAdapter(adaptervaksin)
        }


        //datepicker show
        binding.kelolaimunisasiTanggal.setOnClickListener {
            val datePickerDialog = DatePickerDialog(
                this, { view, year, month, day ->
                    var month = month
                    month = month + 1
                    val urutan = "$day/$month/$year"
                    binding.kelolaimunisasiTanggal.setText(urutan)
                }, year, month, day
            )
            datePickerDialog.show()
        }


        btn_kembali.setOnClickListener {
            startActivity(Intent(this, KesehatananakActivity::class.java))
        }
    }
}