package com.example.posyanduapp.ui.bidan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.posyanduapp.adapter.AdapterPemeriksaan
import com.example.posyanduapp.databinding.ActivityRiwayatPemKesBinding
import com.example.posyanduapp.model.ListPemeriksaan
import com.example.posyanduapp.retrofit.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RiwayatPemKesActivity : AppCompatActivity() {

    var idnya: String = ""
    var namanya: String = ""
    lateinit var rvListPemkes : RecyclerView
    lateinit var adapter: AdapterPemeriksaan
    private lateinit var binding : ActivityRiwayatPemKesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRiwayatPemKesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        rvListPemkes = binding.rvRiwayatPemKes
        idnya = intent.getStringExtra("nik").toString()
        namanya= intent.getStringExtra("nama").toString()
        Toast.makeText(this, idnya, Toast.LENGTH_SHORT).show()
        getlistPemkes(idnya)
        binding.tvNamaAnak.setText(namanya)

        //Intent
        binding.btnKembaliriwayatPemKes.setOnClickListener {
            startActivity(Intent(this, AnakActivity::class.java))
        }

        //swipe refresh
        binding.swlayout.setOnRefreshListener {
            // Handler untuk menjalankan jeda selama 5 detik
            Handler().postDelayed({ // Berhenti berputar/refreshing
                binding.swlayout.isRefreshing = false
                getlistPemkes(idnya)
                Toast.makeText(this, "Data diperbaharui", Toast.LENGTH_SHORT).show()
            }, 3000)

        }

    }

    private fun getlistPemkes(ids: String) {
//        Toast.makeText(this, "iki :"+ids, Toast.LENGTH_SHORT).show()
        try {

            ApiService.endpoint.getallPemeriksaanID(ids)
                .enqueue(object : Callback<ListPemeriksaan> {
                    override fun onResponse(
                        call: Call<ListPemeriksaan>,
                        response: Response<ListPemeriksaan>
                    ) {
                        val status = response.body()?.status
                        val data = response.body()?.data
                        Log.d("responseDatapemkes", data.toString())
                        if (status == "success" && data != null) {
                            // set adapter and layout manager for rv
                            rvListPemkes.adapter = AdapterPemeriksaan(this@RiwayatPemKesActivity, data)
                            rvListPemkes.layoutManager = LinearLayoutManager(this@RiwayatPemKesActivity)

                        } else {
                            Toast.makeText(
                                this@RiwayatPemKesActivity, "Belum ada List",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                    }


                    override fun onFailure(call: Call<ListPemeriksaan>, t: Throwable) {
                        Toast.makeText(this@RiwayatPemKesActivity, t.message, Toast.LENGTH_SHORT).show()
                        Log.d("Error List", t.toString())
                    }

                })
        } catch (e: Exception) {
            Log.d("catch error", e.message.toString())
            Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
        }
    }

}