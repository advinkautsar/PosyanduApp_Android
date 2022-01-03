package com.example.posyanduapp.ui.bidan

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.ImageView
import com.example.posyanduapp.R
import com.example.posyanduapp.databinding.ActivityRujukanBinding
import java.util.*

class RujukanActivity : AppCompatActivity() {

    private lateinit var binding : ActivityRujukanBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRujukanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //datepicker
        val calendar = Calendar.getInstance()
        val year = calendar[Calendar.YEAR]
        val month = calendar[Calendar.MONTH]
        val day = calendar[Calendar.DAY_OF_MONTH]

        //intent back
        val btn_backtoberandabidan = findViewById(R.id.back_toberandabidan) as ImageView
        btn_backtoberandabidan.setOnClickListener {
            startActivity(Intent(this, BerandabidanActivity::class.java))
        }

        //dropdown nama anak
        val nama_anak = resources.getStringArray(R.array.daftar_namaanak)
        val adapter = ArrayAdapter(
            this,
            R.layout.dropdown_listanak,
            nama_anak
        )
        with(binding.rujukanNamaanak){
            setAdapter(adapter)
        }

        //dropdown bidan
        val nama_bidan = resources.getStringArray(R.array.nama_bidan)
        val adapterbidan = ArrayAdapter(
            this,
            R.layout.dropdown_listanak,
            nama_bidan
        )
        with(binding.rujukanNamabidan){
            setAdapter(adapterbidan)
        }

        //dropdown nama posyandu
        val nama_posyandu = resources.getStringArray(R.array.nama_posyandu)
        val adapterposyandu = ArrayAdapter(
            this,
            R.layout.dropdown_listanak,
            nama_posyandu
        )
        with(binding.rujukanNamaposyandu){
            setAdapter(adapterposyandu)
        }

        //datepicker show
        binding.rujukanTanggalrujuk.setOnClickListener {
            val datePickerDialog = DatePickerDialog(
                this, { view, year, month, day ->
                    var month = month
                    month = month + 1
                    val urutan = "$day/$month/$year"
                    binding.rujukanTanggalrujuk.setText(urutan)
                }, year, month, day
            )
            datePickerDialog.show()
        }
    }
}