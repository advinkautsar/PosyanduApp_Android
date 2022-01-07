package com.example.posyanduapp.ui.bidan

import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.example.posyanduapp.R
import com.example.posyanduapp.ui.LoginActivity

class BerandabidanActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_berandabidan)

        val btn_logout = findViewById(R.id.img_keluar) as ImageView
        val btn_kesehatananak = findViewById(R.id.img_kesehatananak) as ImageView
        val btn_rujukananak = findViewById(R.id.img_rujukan) as ImageView

        btn_logout.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
        btn_kesehatananak.setOnClickListener {
            startActivity(Intent(this, KesehatananakActivity::class.java))
        }
        btn_rujukananak.setOnClickListener {
            startActivity(Intent(this, RiwayatrujukanActivity::class.java))
        }
    }
}