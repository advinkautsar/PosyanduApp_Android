package com.example.posyanduapp.ui.kader

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.posyanduapp.R
import com.example.posyanduapp.databinding.ActivityDetailortuBinding

class DetailortuActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailortuBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailortuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnKembalidataorangtua.setOnClickListener {
            startActivity(Intent(this, OrangtuaActivity::class.java))
        }
    }
}