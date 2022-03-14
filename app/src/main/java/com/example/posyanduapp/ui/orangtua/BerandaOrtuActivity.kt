package com.example.posyanduapp.ui.orangtua

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.posyanduapp.Helper.SharedPref
import com.example.posyanduapp.R
import com.example.posyanduapp.databinding.ActivityBerandaOrtuBinding
import com.example.posyanduapp.ui.LoginActivity
import com.example.posyanduapp.ui.bidan.AnakActivity
import com.example.posyanduapp.ui.bidan.BerandabidanActivity
import kotlinx.android.synthetic.main.activity_beranda_ortu.*

class BerandaOrtuActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBerandaOrtuBinding
    private lateinit var s:SharedPref

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBerandaOrtuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        s = SharedPref(this)
        setData()


        binding.imgortuKeluar.setOnClickListener {
            s.setStatusLogin(false)
            val intent = Intent(this, LoginActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            finish()
        }
        binding.imgProfilortu.setOnClickListener {
            startActivity(Intent(this, ProfilOrtuActivity::class.java))
        }
        binding.imgAnak.setOnClickListener {
            startActivity(Intent(this, AnakActivity::class.java))
        }
        binding.imgNotifikasi.setOnClickListener {
            startActivity(Intent(this, NotifikasiActivity::class.java))
        }
        binding.imgTipkes.setOnClickListener {
            startActivity(Intent(this, TipsKesehatanActivity::class.java))
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

        namaortu_posyandu.text = user.nama_pengguna
    }
}