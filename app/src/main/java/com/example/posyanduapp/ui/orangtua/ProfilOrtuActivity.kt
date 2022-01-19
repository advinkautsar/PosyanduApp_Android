package com.example.posyanduapp.ui.orangtua

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.example.posyanduapp.R
import com.example.posyanduapp.databinding.ActivityProfilOrtuBinding

class ProfilOrtuActivity : AppCompatActivity() {

    private lateinit var binding : ActivityProfilOrtuBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfilOrtuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnKembaliproflortu.setOnClickListener {
            startActivity(Intent(this, BerandaOrtuActivity::class.java))
        }
    }
}