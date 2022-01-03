package com.example.posyanduapp.ui.bidan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.example.posyanduapp.R

class KesehatananakActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kesehatananak)

        val btn_backtoberandabidan = findViewById(R.id.back_toberandabidan) as ImageView
        btn_backtoberandabidan.setOnClickListener {
            startActivity(Intent(this, BerandabidanActivity::class.java))
        }
    }
}