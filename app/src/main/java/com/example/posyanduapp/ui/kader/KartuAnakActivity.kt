package com.example.posyanduapp.ui.kader

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import cn.pedant.SweetAlert.SweetAlertDialog
import com.example.posyanduapp.R
import com.example.posyanduapp.databinding.ActivityKartuAnakBinding
import com.example.posyanduapp.model.ListAnak
import com.example.posyanduapp.model.ResponsePesan
import com.example.posyanduapp.retrofit.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.math.BigInteger
import java.util.ArrayList

class KartuAnakActivity : AppCompatActivity() {
    lateinit var pDialog: SweetAlertDialog
    var listAnak: ArrayList<ListAnak.Result> = ArrayList()
    lateinit var nik_anak: BigInteger
    private lateinit var binding : ActivityKartuAnakBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKartuAnakBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getlistanak()
        binding.kartuanakNamaanak.onItemClickListener =
            AdapterView.OnItemClickListener { arg0, arg1, position, arg3 ->
                nik_anak = listAnak.get(position).nik_anak
                Log.d("your selected item", "" + nik_anak)
            }

        binding.backToberandakader.setOnClickListener {
            startActivity(Intent(this, BerandakaderActivity::class.java))
        }
        binding.btnSimpankartuanak.setOnClickListener {

            try {
//
                if (binding.kartuanakNomoranak.text.toString().trim().isEmpty()
                ) {

                    pDialog = SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("Nomor Kartu tidak boleh kosong")
                        .setConfirmClickListener {
                            hideDialog()
                        }
                    showDialog()
                } else {
                    UpdateDataKartuAnak()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
//        dropdown()



    }

//    fun dropdown(){
//        //nama anak
//        val nama_anak = resources.getStringArray(R.array.daftar_namaanak)
//        val adapteranak = ArrayAdapter(
//            this,
//            R.layout.dropdown_listanak,
//            nama_anak
//        )
//        with(binding.kartuanakNamaanak){
//            setAdapter(adapteranak)
//        }
//    }

    private fun getlistanak() {
        ApiService.endpoint.getanak()
            .enqueue(object : Callback<ListAnak> {
                override fun onResponse(
                    call: Call<ListAnak>,
                    response: Response<ListAnak>
                ) {
                    val status = response.body()?.status
                    val data = response.body()?.data
                    Log.d("responseData", data.toString())
                    if (status == "success" && data != null) {
                        // set adapter and layout manager for rv
                        listAnak = data
                        val adapter = ArrayAdapter(applicationContext, android.R.layout.simple_list_item_1, listAnak)

                        binding.kartuanakNamaanak.setAdapter(adapter)
                    }
                }

                override fun onFailure(call: Call<ListAnak>, t: Throwable) {

                }

            })
    }
    private fun UpdateDataKartuAnak() {


        ApiService.endpoint.postnoKartuanak(
            nik_anak.toString(),
            binding.kartuanakNomoranak.text.toString()
            ,
        ).enqueue(object : Callback<ResponsePesan> {
            override fun onResponse(
                call: Call<ResponsePesan>,
                response: Response<ResponsePesan>
            ) {
                val status = response.body()?.status
                val pesanan = response.body()?.pesan

                if (status!!) {
                    Toast.makeText(this@KartuAnakActivity, pesanan, Toast.LENGTH_LONG).show()
//                    this@KelolajadwalimunisasiActivity.fragmentManager?.popBackStack()
                    finish()
                } else {
                    Toast.makeText(this@KartuAnakActivity, pesanan, Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<ResponsePesan>, t: Throwable) {
                t.printStackTrace()
                Toast.makeText(this@KartuAnakActivity, "Kesalahan tidak terduga!", Toast.LENGTH_LONG).show()

            }

        })
    }
    private fun showDialog() {
        if (!pDialog.isShowing) pDialog.show()
    }

    private fun hideDialog() {
        if (pDialog.isShowing) pDialog.dismiss()
    }
}