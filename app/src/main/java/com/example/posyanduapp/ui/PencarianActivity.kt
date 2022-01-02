package com.example.posyanduapp.ui


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import com.example.posyanduapp.R

class PencarianActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pencarian)

        val btn_cari = findViewById(R.id.btn_pencarian) as Button
        btn_cari.setOnClickListener {
            startActivity(Intent(this, PendaftaranActivity::class.java))
        }

        val btn_backtologin = findViewById(R.id.back_caritologin) as ImageView
        btn_backtologin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

    }
}