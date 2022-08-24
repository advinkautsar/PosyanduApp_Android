package com.example.posyanduapp.ui.bidan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.posyanduapp.Model.GetRiwayatStatusGiziAnak
import com.example.posyanduapp.adapter.AdapterPemeriksaan
import com.example.posyanduapp.adapter.AdapterStatusGiziAnakBidan
import com.example.posyanduapp.databinding.ActivityStatusGiziBinding
import com.example.posyanduapp.retrofit.ApiService
import com.example.posyanduapp.ui.orangtua.RecyclerAdapterStatGizi
import kotlinx.android.synthetic.main.activity_riwayat_pem_kes.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RiwayatStatusGiziAnakctivity : AppCompatActivity() {

    var idnya: String = ""
    var namanya: String = ""
    lateinit var rvriwpemkesanak : RecyclerView
    lateinit var adapter: AdapterStatusGiziAnakBidan

    private lateinit var binding : ActivityStatusGiziBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStatusGiziBinding.inflate(layoutInflater)
        setContentView(binding.root)

        rvriwpemkesanak = binding.rvStatusgizi


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



//
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
                            rvriwpemkesanak.adapter = AdapterStatusGiziAnakBidan(this@RiwayatStatusGiziAnakctivity, data)
                            rvriwpemkesanak.layoutManager = LinearLayoutManager(this@RiwayatStatusGiziAnakctivity)

                        }else{
                            Toast.makeText(
                                this@RiwayatStatusGiziAnakctivity, "Belum ada List",
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

//
}