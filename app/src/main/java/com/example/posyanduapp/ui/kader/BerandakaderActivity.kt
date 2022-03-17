package com.example.posyanduapp.ui.kader

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.posyanduapp.Helper.SharedPref
import com.example.posyanduapp.R
import com.example.posyanduapp.databinding.ActivityBerandakaderBinding
import com.example.posyanduapp.ui.LoginActivity
import com.example.posyanduapp.ui.bidan.AnakActivity
import kotlinx.android.synthetic.main.activity_beranda_ortu.*
import kotlinx.android.synthetic.main.activity_berandakader.*

class BerandakaderActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBerandakaderBinding
    private lateinit var s: SharedPref

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBerandakaderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        s = SharedPref(this)
        setData()

        //intent
        binding.imgKartuanak.setOnClickListener {
            startActivity(Intent(this, KartuAnakActivity::class.java))
        }
        binding.imgJadwalposyandu.setOnClickListener {
            startActivity(Intent(this, RiwayatJadwalPosyanduActivity::class.java))
        }
        binding.imgOrangtua.setOnClickListener {
            startActivity(Intent(this, OrangtuaActivity::class.java))
        }
        binding.imgkaderKeluar.setOnClickListener {
            s.setStatusLogin(false)
            val intent = Intent(this, LoginActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            finish()
        }
        binding.imgAnak.setOnClickListener {
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

        namakader_posyandu.text = user.nama_pengguna
    }
}