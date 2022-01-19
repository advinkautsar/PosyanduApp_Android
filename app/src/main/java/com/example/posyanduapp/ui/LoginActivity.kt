package com.example.posyanduapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.posyanduapp.R
import com.example.posyanduapp.ui.bidan.BerandabidanActivity
import com.example.posyanduapp.ui.kader.BerandakaderActivity
import com.example.posyanduapp.ui.orangtua.BerandaOrtuActivity

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val tv_daftar = findViewById(R.id.tv_daftar) as TextView
        val btn_login = findViewById(R.id.btn_login) as Button
        val btn_kader = findViewById(R.id.btn_kader) as Button
        val btn_ortu = findViewById(R.id.btn_ortu) as Button

        btn_login.setOnClickListener {
            startActivity(Intent(this, BerandabidanActivity::class.java))
        }
        btn_kader.setOnClickListener {
            startActivity(Intent(this, BerandakaderActivity::class.java))
        }
        tv_daftar.setOnClickListener {
            startActivity(Intent(this, PencarianActivity::class.java ))
        }
        btn_ortu.setOnClickListener {
            startActivity(Intent(this, BerandaOrtuActivity::class.java))
        }




    }
}