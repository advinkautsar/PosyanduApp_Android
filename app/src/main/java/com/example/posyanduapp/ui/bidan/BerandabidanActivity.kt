package com.example.posyanduapp.ui.bidan

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.posyanduapp.Helper.SharedPref
import com.example.posyanduapp.R
import com.example.posyanduapp.model.ResponsePesan
import com.example.posyanduapp.retrofit.ApiService
import com.example.posyanduapp.ui.LoginActivity
import com.example.posyanduapp.ui.PendaftaranActivity
import kotlinx.android.synthetic.main.activity_berandabidan.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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

                        Toast.makeText(this@BerandabidanActivity, pesanan, Toast.LENGTH_LONG).show()

                        nextbutton()

                    } else if (pesanan=="Gagal") {
                        Toast.makeText(this@BerandabidanActivity,pesanan, Toast.LENGTH_LONG).show()

                    }

                }
            }

            override fun onFailure(call: Call<ResponsePesan>, t: Throwable) {
                t.printStackTrace()
                Toast.makeText(this@BerandabidanActivity, "Kesalahan tidak terduga!", Toast.LENGTH_LONG).show()

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