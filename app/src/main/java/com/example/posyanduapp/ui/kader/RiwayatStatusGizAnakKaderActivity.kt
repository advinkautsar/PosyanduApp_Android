package com.example.posyanduapp.ui.kader

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.posyanduapp.Model.GetRiwayatStatusGiziAnak
import com.example.posyanduapp.R
import com.example.posyanduapp.adapter.AdapterStatusGiziAnakBidan
import com.example.posyanduapp.databinding.ActivityRiwayatStatusGizAnakKaderBinding
import com.example.posyanduapp.retrofit.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RiwayatStatusGizAnakKaderActivity : AppCompatActivity() {

    lateinit var binding: ActivityRiwayatStatusGizAnakKaderBinding
    var idnya: String = ""
    var namanya: String = ""
    lateinit var rv_liststatusgiziKader : RecyclerView
    lateinit var adapter: AdapterStatusGiziAnakBidan

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRiwayatStatusGizAnakKaderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        rv_liststatusgiziKader = binding.rvStatusgiziKader

        idnya = intent.getStringExtra("nik").toString()
        namanya= intent.getStringExtra("nama").toString()
        binding.statusNamaanak.setText(namanya)
        getDetailStatGizAnakID(idnya)

        //swipe refresh
        binding.swlayout.setOnRefreshListener {
            // Handler untuk menjalankan jeda selama 5 detik
            Handler().postDelayed({ // Berhenti berputar/refreshing
                binding.swlayout.isRefreshing = false
                getDetailStatGizAnakID(idnya)
                Toast.makeText(this, "Data diperbaharui", Toast.LENGTH_SHORT).show()
            }, 3000)

        }
    }

    private fun getDetailStatGizAnakID(idnya: String) {
        try {

            ApiService.endpoint.getDetailStatGizAnakID(idnya)
                .enqueue(object: Callback<GetRiwayatStatusGiziAnak> {
                    override fun onResponse(
                        call: Call<GetRiwayatStatusGiziAnak>,
                        response: Response<GetRiwayatStatusGiziAnak>
                    ) {
                        val status = response.body()?.status
                        val data = response.body()?.data
                        Log.d("responseriwstatusgizi", data.toString())
                        if (status == "success" && data != null){
                            rv_liststatusgiziKader.adapter = AdapterStatusGiziAnakBidan(this@RiwayatStatusGizAnakKaderActivity, data)
                            rv_liststatusgiziKader.layoutManager = LinearLayoutManager(this@RiwayatStatusGizAnakKaderActivity)

                        }else{
                            Toast.makeText(this@RiwayatStatusGizAnakKaderActivity, "Belum ada List",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }

                    override fun onFailure(call: Call<GetRiwayatStatusGiziAnak>, t: Throwable) {
                        TODO("Not yet implemented")
                    }

                })

        }catch (e: Exception) {
            Log.d("catch error", e.message.toString())
            Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
        }
    }
}