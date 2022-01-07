package com.example.posyanduapp.ui.bidan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.example.posyanduapp.R

class KesehatananakActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kesehatananak)

        val btn_backtoberandabidan = findViewById(R.id.back_toberandabidan) as ImageView
        val btn_kelolajadwalimunisasi = findViewById(R.id.kelola_jadwalimunisasi) as ImageView
        val btn_kelolapemeriksaan = findViewById(R.id.img_kelolapemeriksaan) as ImageView

        btn_kelolapemeriksaan.setOnClickListener {
            startActivity(Intent(this, PemeriksaankesehatanActivity::class.java))
        }

        btn_kelolajadwalimunisasi.setOnClickListener {
            startActivity(Intent(this, KelolajadwalimunisasiActivity::class.java))
        }
        btn_backtoberandabidan.setOnClickListener {
            startActivity(Intent(this, BerandabidanActivity::class.java))
        }
    }
}