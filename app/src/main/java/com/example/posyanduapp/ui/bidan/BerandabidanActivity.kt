package com.example.posyanduapp.ui.bidan

import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.example.posyanduapp.Helper.SharedPref
import com.example.posyanduapp.R
import com.example.posyanduapp.ui.LoginActivity
import kotlinx.android.synthetic.main.activity_beranda_ortu.*
import kotlinx.android.synthetic.main.activity_berandabidan.*

class BerandabidanActivity : AppCompatActivity() {

    private lateinit var s: SharedPref


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_berandabidan)

        s = SharedPref(this)
        setData()


        val btn_logout = findViewById(R.id.img_keluar) as ImageView
        val btn_kesehatananak = findViewById(R.id.img_kesehatananak) as ImageView
        val btn_rujukananak = findViewById(R.id.img_rujukan) as ImageView
        val btn_rekapimunisasi = findViewById(R.id.img_datarekap) as ImageView
        val btn_anak = findViewById(R.id.img_anak) as ImageView


        btn_rekapimunisasi.setOnClickListener {
            startActivity(Intent(this, RekapimunisasiActivity::class.java))
        }
        btn_logout.setOnClickListener {
            s.setStatusLogin(false)
            val intent = Intent(this, LoginActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            finish()
        }
        btn_kesehatananak.setOnClickListener {
            startActivity(Intent(this, KesehatananakActivity::class.java))
        }
        btn_rujukananak.setOnClickListener {
            startActivity(Intent(this, RiwayatrujukanActivity::class.java))
        }
        btn_anak.setOnClickListener {
            startActivity(Intent(this, AnakActivity::class.java))
        }

    }

    private fun setData() {
        if (s.getUser() == null ){
            val intent = Intent(this, LoginActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            finish()
        }
        val user = s.getUser()!!

        tv_namabidan.text = user.nama_pengguna
    }
}