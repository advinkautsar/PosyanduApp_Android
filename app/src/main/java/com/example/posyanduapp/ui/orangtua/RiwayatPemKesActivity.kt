package com.example.posyanduapp.ui.orangtua

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.posyanduapp.adapter.AdapterPemeriksaan
import com.example.posyanduapp.databinding.ActivityRiwayatPemKesBinding
import com.example.posyanduapp.model.ListPemeriksaan
import com.example.posyanduapp.retrofit.ApiService
import com.example.posyanduapp.ui.bidan.AnakActivity
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
        binding.swlayout.setOnRefreshListener {
            // Handler untuk menjalankan jeda selama 5 detik
            Handler().postDelayed({ // Berhenti berputar/refreshing
                binding.swlayout.isRefreshing = false
                getlistPemkes(idnya)
                Toast.makeText(this, "Data diperbaharui", Toast.LENGTH_SHORT).show()
            }, 3000)

        }
        //add content into recyclerview
//        postToList()
        //recyclerview
//        binding.rvRiwayatPemKes.layoutManager = LinearLayoutManager(this)
//        binding.rvRiwayatPemKes.adapter = RecyclerAdapterRiwPemkes (tanngallist, pemkes1list, pemkes2list,
//        pemkes3list, pemkes4list, pemkes5list, pemkes6list, pemkes7list, pemkes8list, pemkes9list, pemkes10list, pemkes11list,
//        pemkes12list, pemkes13list, pemkes14list, pemkes15list)
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
                                this@RiwayatPemKesActivity, "Tidak ada List",
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

//    private fun postToList() {
//        for (i in 1..10){
//            addTolist( "21 - 02 - 2022", "3 Kg", "30cm", "-",
//            "Polio 1", "Tidak", "Tidak", "Tidak", "Tidak", "Tidak",
//            "Tidak", "Tidak", "Tidak", "Tidak", "Tidak", "Bu Ida")
//
//        }
//    }
//
//    private fun addTolist( tgl : String, pemkes1 : String, pemkes2 : String, pemkes3 : String, pemkes4 : String, pemkes5 : String,
//                           pemkes6 : String, pemkes7 : String, pemkes8 : String, pemkes9 : String, pemkes10 : String, pemkes11 : String,
//                           pemkes12 : String, pemkes13 : String, pemkes14 : String, pemkes15 : String,) {
//        tanngallist.add(tgl)
//        pemkes1list.add(pemkes1)
//        pemkes2list.add(pemkes2)
//        pemkes3list.add(pemkes3)
//        pemkes4list.add(pemkes4)
//        pemkes5list.add(pemkes5)
//        pemkes6list.add(pemkes6)
//        pemkes7list.add(pemkes7)
//        pemkes8list.add(pemkes8)
//        pemkes9list.add(pemkes9)
//        pemkes10list.add(pemkes10)
//        pemkes11list.add(pemkes11)
//        pemkes12list.add(pemkes12)
//        pemkes13list.add(pemkes13)
//        pemkes14list.add(pemkes14)
//        pemkes15list.add(pemkes15)
//
//
//
//
//    }
}