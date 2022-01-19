package com.example.posyanduapp.ui.kader

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.posyanduapp.R
import com.example.posyanduapp.databinding.ActivityBerandakaderBinding
import com.example.posyanduapp.ui.LoginActivity
import com.example.posyanduapp.ui.bidan.AnakActivity

class BerandakaderActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBerandakaderBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBerandakaderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //intent
        binding.imgKartuanak.setOnClickListener {
            startActivity(Intent(this, KartuAnakActivity::class.java))
        }
        binding.imgJadwalposyandu.setOnClickListener {
            startActivity(Intent(this, RiwayatJadwalPosyanduActivity::class.java))
        }
        binding.imgOrangtua.setOnClickListener {
            startActivity(Intent(this, OrangtuaActivity::class.java))
        }
        binding.imgkaderKeluar.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
        binding.imgAnak.setOnClickListener {
            startActivity(Intent(this, AnakActivity::class.java))
        }
    }
}