package com.example.posyanduapp.ui.bidan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.posyanduapp.Model.ListRujukanAnak
import com.example.posyanduapp.R
import com.example.posyanduapp.adapter.AdapterPemeriksaan
import com.example.posyanduapp.adapter.AdapterRiwayatRujukanAnak
import com.example.posyanduapp.databinding.ActivityRiwayatRujukanPerAnakBidanBinding
import com.example.posyanduapp.model.ListPemeriksaan
import com.example.posyanduapp.retrofit.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RiwayatRujukanPerAnakBidan : AppCompatActivity() {

    var idnya: String = ""
    var namanya: String = ""
    lateinit var rvListRujukan : RecyclerView
    lateinit var adapter: AdapterRiwayatRujukanAnak
    private lateinit var binding: ActivityRiwayatRujukanPerAnakBidanBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRiwayatRujukanPerAnakBidanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        rvListRujukan = binding.rvRiwayatrujukananak
        idnya = intent.getStringExtra("nik").toString()
        namanya= intent.getStringExtra("nama").toString()

        Toast.makeText(this, idnya, Toast.LENGTH_SHORT).show()
        getallRujukanID(idnya)
        binding.rujukanakNamaAnak.setText(namanya)

        binding.btnKembalirujukanak.setOnClickListener {
            startActivity(Intent(this, AnakActivity::class.java))
        }


    }

    private fun getallRujukanID(ids: String) {
        try {

            ApiService.endpoint.getallRujukanID(ids)
                .enqueue(object : Callback<ListRujukanAnak>{
                    override fun onResponse(
                        call: Call<ListRujukanAnak>,
                        response: Response<ListRujukanAnak>
                    ) {
                        val status = response.body()?.status
                        val data = response.body()?.data
                        Log.d("responseDataRujukanAnak", data.toString())
                        if (status == "success" && data != null){
                            rvListRujukan.adapter = AdapterRiwayatRujukanAnak(this@RiwayatRujukanPerAnakBidan, data)
                            rvListRujukan.layoutManager= LinearLayoutManager(this@RiwayatRujukanPerAnakBidan)
                        } else {
                            Toast.makeText(
                                this@RiwayatRujukanPerAnakBidan, "Belum ada List",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }

                    override fun onFailure(call: Call<ListRujukanAnak>, t: Throwable) {
                        Toast.makeText(this@RiwayatRujukanPerAnakBidan, t.message, Toast.LENGTH_SHORT).show()
                        Log.d("Error List", t.toString())
                    }

                })




        } catch (e: Exception) {
            Log.d("catch error", e.message.toString())
            Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
        }
    }
}