package com.example.posyanduapp.ui.orangtua

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
import com.example.posyanduapp.databinding.ActivityRiwayatStatusGiziAnakOrtuBinding
import com.example.posyanduapp.retrofit.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RiwayatStatusGiziAnakOrtuActivity : AppCompatActivity() {

    lateinit var binding:ActivityRiwayatStatusGiziAnakOrtuBinding
    var idnya: String = ""
    var namanya: String = ""
    lateinit var rv_liststatusgiziOrtu : RecyclerView
    lateinit var adapter: AdapterStatusGiziAnakBidan

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRiwayatStatusGiziAnakOrtuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        rv_liststatusgiziOrtu = binding.rvStatusgiziOrtu
        idnya = intent.getStringExtra("nik_anak").toString()
        namanya= intent.getStringExtra("nama_anak").toString()
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
                .enqueue(object: Callback<GetRiwayatStatusGiziAnak>{
                    override fun onResponse(
                        call: Call<GetRiwayatStatusGiziAnak>,
                        response: Response<GetRiwayatStatusGiziAnak>
                    ) {
                        val status = response.body()?.status
                        val data = response.body()?.data
                        Log.d("responseriwstatusgizi", data.toString())
                        if (status == "success" && data != null){
                            rv_liststatusgiziOrtu.adapter = AdapterStatusGiziAnakBidan(this@RiwayatStatusGiziAnakOrtuActivity, data)
                            rv_liststatusgiziOrtu.layoutManager = LinearLayoutManager(this@RiwayatStatusGiziAnakOrtuActivity)

                        }else{
                            Toast.makeText(this@RiwayatStatusGiziAnakOrtuActivity, "Belum ada List",
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