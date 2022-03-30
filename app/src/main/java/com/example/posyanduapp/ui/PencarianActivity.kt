package com.example.posyanduapp.ui


import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import cn.pedant.SweetAlert.SweetAlertDialog
import com.example.posyanduapp.databinding.ActivityPencarianBinding
import com.example.posyanduapp.model.ResponsePesan
import com.example.posyanduapp.retrofit.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PencarianActivity : AppCompatActivity(){
    private lateinit var binding : ActivityPencarianBinding
    lateinit var pDialog: SweetAlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_pencarian)
        binding = ActivityPencarianBinding.inflate(layoutInflater)
        setContentView(binding.root)

//
//        val btn_cari = findViewById(R.id.btn_pencarian) as Button

        binding.btnPencarian.setOnClickListener {
//            startActivity(Intent(this, PendaftaranActivity::class.java))
            try {
//

                if (binding.pencarianNik.text.toString().trim().isEmpty()
                ){

                    pDialog = SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("NIK tidak boleh kosong")
                        .setConfirmClickListener {
                            hideDialog()
                        }
                    showDialog()
                } else {
                    ceknikortu()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }

//        val btn_backtologin = findViewById(R.id.back_caritologin) as ImageView
        binding.backCaritologin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))


        }

    }

    private fun ceknikortu() {
        ApiService.endpoint.ceknikortu(

            binding.pencarianNik.text.toString()
        ).enqueue(object : Callback<ResponsePesan> {
            override fun onResponse(
                call: Call<ResponsePesan>,
                response: Response<ResponsePesan>
            ) {
                val status = response.body()?.status
                val pesanan = response.body()?.pesan

                if (status !!) {
                    if (pesanan=="NIK anda belum terdaftar di aplikasi") {

                        Toast.makeText(this@PencarianActivity, pesanan, Toast.LENGTH_LONG).show()

            //                    this@KelolajadwalimunisasiActivity.fragmentManager?.popBackStack()
            //                    finish()
                    } else if (pesanan=="Silahkan update akun anda") {
                        Toast.makeText(this@PencarianActivity,pesanan, Toast.LENGTH_LONG).show()

                        nextbutton(binding.pencarianNik.text.toString())


                    }

                }
            }

            override fun onFailure(call: Call<ResponsePesan>, t: Throwable) {
                t.printStackTrace()
                Toast.makeText(this@PencarianActivity, "Kesalahan tidak terduga!", Toast.LENGTH_LONG).show()

            }

        })
    }

    private fun nextbutton(nik: String) {
        val intent = Intent(this, PendaftaranActivity::class.java)
        intent.putExtra("nik",nik)
        startActivity(intent)
    }


private fun showDialog() {
    if (!pDialog.isShowing) pDialog.show()
}

    private fun hideDialog() {
        if (pDialog.isShowing) pDialog.dismiss()
    }
}