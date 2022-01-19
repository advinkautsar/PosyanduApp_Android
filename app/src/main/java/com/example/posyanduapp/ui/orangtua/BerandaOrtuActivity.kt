package com.example.posyanduapp.ui.orangtua

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.posyanduapp.R
import com.example.posyanduapp.databinding.ActivityBerandaOrtuBinding
import com.example.posyanduapp.ui.LoginActivity
import com.example.posyanduapp.ui.bidan.AnakActivity

class BerandaOrtuActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBerandaOrtuBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBerandaOrtuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imgortuKeluar.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
        binding.imgProfilortu.setOnClickListener {
            startActivity(Intent(this, ProfilOrtuActivity::class.java))
        }
        binding.imgAnak.setOnClickListener {
            startActivity(Intent(this, AnakActivity::class.java))
        }

    }
}