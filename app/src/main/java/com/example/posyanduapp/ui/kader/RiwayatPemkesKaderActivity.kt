package com.example.posyanduapp.ui.kader

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.posyanduapp.R
import com.example.posyanduapp.adapter.AdapterPemeriksaan
import com.example.posyanduapp.adapter.adapterKader.AdapterPemKesAnakKader
import com.example.posyanduapp.databinding.ActivityRiwayatPemkesKaderBinding
import com.example.posyanduapp.model.ListPemeriksaan
import com.example.posyanduapp.retrofit.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RiwayatPemkesKaderActivity : AppCompatActivity() {

    lateinit var binding: ActivityRiwayatPemkesKaderBinding
    var idnya: String = ""
    var namanya: String = ""
    lateinit var rvListPemkesKader : RecyclerView
    lateinit var adapter: AdapterPemKesAnakKader

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRiwayatPemkesKaderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        rvListPemkesKader = binding.rvRiwayatPemKesKader
        idnya = intent.getStringExtra("nik").toString()
        namanya= intent.getStringExtra("nama").toString()
        getlistPemkes(idnya)
        binding.tvNamaAnak.setText(namanya)

        //intent
        binding.btnKembaliriwayatPemKes.setOnClickListener {
            startActivity(Intent(this,FiturAnakKaderActivity::class.java))
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

    private fun getlistPemkes(idnya: String) {
        try {

            ApiService.endpoint.getallPemeriksaanID(idnya)
                .enqueue(object : Callback<ListPemeriksaan>{
                    override fun onResponse(
                        call: Call<ListPemeriksaan>,
                        response: Response<ListPemeriksaan>
                    ) {
                        val status = response.body()?.status
                        val data = response.body()?.data
                        Log.d("responseDatapemkes", data.toString())
                        if (status == "success" && data != null) {
                            // set adapter and layout manager for rv
                            rvListPemkesKader.adapter = AdapterPemKesAnakKader(this@RiwayatPemkesKaderActivity, data)
                            rvListPemkesKader.layoutManager = LinearLayoutManager(this@RiwayatPemkesKaderActivity)

                        } else {
                            Toast.makeText(
                                this@RiwayatPemkesKaderActivity, "Belum ada List",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }

                    override fun onFailure(call: Call<ListPemeriksaan>, t: Throwable) {
                        Toast.makeText(this@RiwayatPemkesKaderActivity, t.message, Toast.LENGTH_SHORT).show()
                        Log.d("Error List", t.toString())
                    }

                })

        } catch (e: Exception) {
            Log.d("catch error", e.message.toString())
            Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
        }
    }
}