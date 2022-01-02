package com.example.posyanduapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.posyanduapp.R
import com.example.posyanduapp.ui.bidan.BerandabidanActivity

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val tv_daftar = findViewById(R.id.tv_daftar) as TextView
        val btn_login = findViewById(R.id.btn_login) as Button

        btn_login.setOnClickListener {
            startActivity(Intent(this, BerandabidanActivity::class.java))
        }

        tv_daftar.setOnClickListener {
            startActivity(Intent(this, PencarianActivity::class.java ))
        }




    }
}