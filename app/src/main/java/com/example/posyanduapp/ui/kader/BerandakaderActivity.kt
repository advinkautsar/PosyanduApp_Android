package com.example.posyanduapp.ui.kader

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.posyanduapp.Helper.SharedPref
import com.example.posyanduapp.R
import com.example.posyanduapp.databinding.ActivityBerandakaderBinding
import com.example.posyanduapp.model.ResponsePesan
import com.example.posyanduapp.retrofit.ApiService
import com.example.posyanduapp.ui.LoginActivity
import com.example.posyanduapp.ui.bidan.AnakActivity
import kotlinx.android.synthetic.main.activity_beranda_ortu.*
import kotlinx.android.synthetic.main.activity_berandakader.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
            startActivity(Intent(this, JadwalPosyanduActivity::class.java))
        }
        binding.imgOrangtua.setOnClickListener {
            startActivity(Intent(this, OrangtuaActivity::class.java))
        }
        binding.imgkaderKeluar.setOnClickListener {
            val alertDialog : AlertDialog = AlertDialog.Builder(this).create()
            alertDialog.setTitle("Logout Akun")
            alertDialog.setMessage("Apakah anda yakin ingin keluar aplikasi ?")

            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE,"Ya"){
                    dialog: DialogInterface?,
                    which: Int -> val user = s.getUser()!!
                                    getdelete(user.id)
                dialog?.dismiss()
            }

            alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE,"Tidak"){
                    dialog: DialogInterface?,
                    which: Int -> dialog?.dismiss()
            }
            alertDialog.show()

        }
        binding.imgAnak.setOnClickListener {
            startActivity(Intent(this, FiturAnakKaderActivity::class.java))
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

    private fun getdelete(id: Int) {

        ApiService.endpoint.LOGOUT(

            id
        ).enqueue(object : Callback<ResponsePesan> {
            override fun onResponse(
                call: Call<ResponsePesan>,
                response: Response<ResponsePesan>
            ) {
                val status = response.body()?.status
                val pesanan = response.body()?.pesan

                if (status !!) {
                    if (pesanan=="Logout") {

                        Toast.makeText(this@BerandakaderActivity, pesanan, Toast.LENGTH_LONG).show()

                        nextbutton()

                    } else if (pesanan=="Gagal") {
                        Toast.makeText(this@BerandakaderActivity,pesanan, Toast.LENGTH_LONG).show()

                    }

                }
            }

            override fun onFailure(call: Call<ResponsePesan>, t: Throwable) {
                t.printStackTrace()
                Toast.makeText(this@BerandakaderActivity, "Kesalahan tidak terduga!", Toast.LENGTH_LONG).show()

            }

        })

    }

    private fun nextbutton() {
        s.setStatusLogin(false)
        val intent = Intent(this, LoginActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
        finish()

    }
}