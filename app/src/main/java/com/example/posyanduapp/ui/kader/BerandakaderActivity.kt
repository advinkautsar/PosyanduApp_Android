package com.example.posyanduapp.ui.kader

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.posyanduapp.R
import com.example.posyanduapp.databinding.ActivityBerandakaderBinding

class BerandakaderActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBerandakaderBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBerandakaderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imgKartuanak.setOnClickListener {
            startActivity(Intent(this, KartuAnakActivity::class.java))
        }
        binding.imgJadwalposyandu.setOnClickListener {
            startActivity(Intent(this, RiwayatJadwalPosyanduActivity::class.java))
        }

    }
}