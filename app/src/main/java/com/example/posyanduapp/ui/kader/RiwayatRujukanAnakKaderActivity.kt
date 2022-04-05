package com.example.posyanduapp.ui.kader

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.posyanduapp.Model.ListRujukanAnak
import com.example.posyanduapp.R
import com.example.posyanduapp.adapter.AdapterRiwayatRujukanAnak
import com.example.posyanduapp.databinding.ActivityRiwayatRujukanAnakKaderBinding
import com.example.posyanduapp.retrofit.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RiwayatRujukanAnakKaderActivity : AppCompatActivity() {

    lateinit var binding: ActivityRiwayatRujukanAnakKaderBinding
    var idnya: String = ""
    var namanya: String = ""
    lateinit var rv_listRujukanAnakKader : RecyclerView
    lateinit var adapter: AdapterRiwayatRujukanAnak

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRiwayatRujukanAnakKaderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        rv_listRujukanAnakKader = binding.rvRiwayatrujukananakKader
        idnya = intent.getStringExtra("nik").toString()
        namanya= intent.getStringExtra("nama").toString()
        binding.rujukanakNamaAnak.setText(namanya)

        //panggil main function
        getallRujukanID(idnya)

        //intent
        binding.btnKembalirujukanak.setOnClickListener {
            startActivity(Intent(this,FiturAnakKaderActivity::class.java))
        }

        //swipe refresh
        binding.swlayout.setOnRefreshListener {
            // Handler untuk menjalankan jeda selama 5 detik
            Handler().postDelayed({ // Berhenti berputar/refreshing
                binding.swlayout.isRefreshing = false
                getallRujukanID(idnya)
                Toast.makeText(this, "Data diperbaharui", Toast.LENGTH_SHORT).show()
            }, 3000)

        }



    }

    private fun getallRujukanID(ids: String) {
        try {

            ApiService.endpoint.getallRujukanID(ids)
                .enqueue(object : Callback<ListRujukanAnak> {
                    override fun onResponse(
                        call: Call<ListRujukanAnak>,
                        response: Response<ListRujukanAnak>
                    ) {
                        val status = response.body()?.status
                        val data = response.body()?.data
                        Log.d("responseDataRujukanAnak", data.toString())
                        if (status == "success" && data != null){
                            rv_listRujukanAnakKader.adapter = AdapterRiwayatRujukanAnak(this@RiwayatRujukanAnakKaderActivity, data)
                            rv_listRujukanAnakKader.layoutManager= LinearLayoutManager(this@RiwayatRujukanAnakKaderActivity)
                        } else {
                            Toast.makeText(
                                this@RiwayatRujukanAnakKaderActivity, "Belum ada List",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }

                    override fun onFailure(call: Call<ListRujukanAnak>, t: Throwable) {
                        Toast.makeText(this@RiwayatRujukanAnakKaderActivity, t.message, Toast.LENGTH_SHORT).show()
                        Log.d("Error List", t.toString())
                    }

                })




        } catch (e: Exception) {
            Log.d("catch error", e.message.toString())
            Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
        }
    }
}