package com.example.posyanduapp.ui.bidan

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
import com.example.posyanduapp.adapter.AdapterRujukanAnak
import com.example.posyanduapp.databinding.ActivityRiwayatrujukanBinding
import com.example.posyanduapp.model.ListRujukan
import com.example.posyanduapp.retrofit.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RiwayatrujukanActivity : AppCompatActivity() {

    lateinit var rvListrujukan: RecyclerView
    lateinit var adapter: AdapterRujukanAnak
    private lateinit var binding: ActivityRiwayatrujukanBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRiwayatrujukanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnKembaliriwayatrujukan.setOnClickListener {
            startActivity(Intent(this, BerandabidanActivity::class.java))
        }

        binding.fabRiwayatrujukan.setOnClickListener {
//            startActivity(Intent(this, RujukanActivity::class.java))
            val intent = Intent(this, RujukanCreateActivity::class.java)
//            intent.putExtra("ids","0")
            startActivity(intent)
        }
        rvListrujukan = binding.rvRiwayatrujukan


        // Mengeset listener yang akan dijalankan saat layar di refresh/swipe
        binding.swlayout.setOnRefreshListener(OnRefreshListener {
            // Handler untuk menjalankan jeda selama 5 detik
            Handler().postDelayed({ // Berhenti berputar/refreshing
                binding.swlayout.isRefreshing = false
                getlistRujukan()
                Toast.makeText(this, "Data diperbaharui", Toast.LENGTH_SHORT).show()
            }, 3000)
        })
        getlistRujukan()
//        postToList()
//
//        //recyclerview
//        binding.rvRiwayatrujukan.layoutManager = LinearLayoutManager(this)
//        binding.rvRiwayatrujukan.adapter = RecyclerAdapterRujukan(posyandulist, tanggallist, namaanaklist, namabidanlist,
//        puskesmaslist, penyakitlist)
    }

//    private fun addToList(posyandu: String, tanggal: String, anak: String, bidan: String, puskesmas: String, penyakit: String){
//        posyandulist.add(posyandu)
//        tanggallist.add(tanggal)
//        namaanaklist.add(anak)
//        namabidanlist.add(bidan)
//        puskesmaslist.add(puskesmas)
//        penyakitlist.add(penyakit)
//    }
//    private fun postToList(){
//        for ( i in 1..25){
//            addToList("Posyandu Belewah ${i}","21-04-2022", "Bambang Gentolet", "Bidan Ifa",
//                "Puskesmas Paspan", "Anak Menderita Gizi Buruk yang harus segera mendapatkan penanganan oleh dokter di puskesmas terdekat" )
//        }
//
//    }

    private fun getlistRujukan() {
        try {

            ApiService.endpoint.getRujukan()
                .enqueue(object : Callback<ListRujukan> {
                    override fun onResponse(
                        call: Call<ListRujukan>,
                        response: Response<ListRujukan>
                    ) {
                        val status = response.body()?.status
                        val data = response.body()?.data
                        Log.d("responseData", data.toString())
                        if (status == "success" && data != null) {
                            // set adapter and layout manager for rv
                            rvListrujukan.adapter = AdapterRujukanAnak(this@RiwayatrujukanActivity, data)
                            rvListrujukan.layoutManager = LinearLayoutManager(this@RiwayatrujukanActivity)

                        } else {
                            Toast.makeText(
                                this@RiwayatrujukanActivity, "Tidak ada List",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                    }
//                    {
//                        Log.d("Req Response", response.body().toString())
//                        val data = response.body()?.data
//                        if (response.body()?.sukses == true && data != null) {
//                            // set adapter and layout manager
//                            rvListAnak.adapter = AdapterAnak(this@AnakActivity, data)
//                            rvListAnak.layoutManager = LinearLayoutManager(this@AnakActivity)
//                        } else {
//                            Toast.makeText(
//                                this@AnakActivity, "Tidak ada pesanan.",
//                                Toast.LENGTH_SHORT
//                            ).show()
//                        }
//                    }

                    override fun onFailure(call: Call<ListRujukan>, t: Throwable) {
                        Toast.makeText(this@RiwayatrujukanActivity, t.message, Toast.LENGTH_SHORT).show()
                        Log.d("Error List", t.toString())
                    }

                })
        } catch (e: Exception) {
            Log.d("catch error", e.message.toString())
            Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
        }
    }

}