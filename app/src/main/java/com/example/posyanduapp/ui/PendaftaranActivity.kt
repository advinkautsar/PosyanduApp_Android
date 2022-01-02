package com.example.posyanduapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import com.example.posyanduapp.R

class PendaftaranActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pendaftaran)

        val btn_daftar = findViewById(R.id.btn_pendaftaran) as Button
        btn_daftar.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
        val btn_backtopencarian = findViewById(R.id.back_daftartocari) as ImageView
        btn_backtopencarian.setOnClickListener {
            startActivity(Intent(this, PencarianActivity::class.java))
        }
    }
}