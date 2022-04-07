package com.example.posyanduapp.ui.orangtua

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
import com.example.posyanduapp.databinding.ActivityRiwayatRujukAnakBinding
import com.example.posyanduapp.retrofit.ApiService
import com.example.posyanduapp.ui.bidan.AnakActivity
import com.example.posyanduapp.ui.bidan.RecyclerAdapterRujukan
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RiwayatRujukAnakOrangtuaActivity : AppCompatActivity() {

    lateinit var binding : ActivityRiwayatRujukAnakBinding
    var idnya: String = ""
    var namanya: String = ""
    lateinit var rv_listRujukanAnakOrtu : RecyclerView
    lateinit var adapter: AdapterRiwayatRujukanAnak

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRiwayatRujukAnakBinding.inflate(layoutInflater)
        setContentView(binding.root)

        rv_listRujukanAnakOrtu = binding.rvRiwayatrujukananakOrtu
        idnya = intent.getStringExtra("nik").toString()
        namanya= intent.getStringExtra("nama").toString()
        binding.rujukanakNamaAnak.setText(namanya)

        getallRujukanID(idnya)

        //swipe refresh
        binding.swlayout.setOnRefreshListener {
            // Handler untuk menjalankan jeda selama 5 detik
            Handler().postDelayed({ // Berhenti berputar/refreshing
                binding.swlayout.isRefreshing = false
                getallRujukanID(idnya)
                Toast.makeText(this, "Data diperbaharui", Toast.LENGTH_SHORT).show()
            }, 3000)

        }

        //intent
        binding.btnKembalirujukanak.setOnClickListener {
            startActivity(Intent(this, FiturAnakOrtuActivity::class.java))
        }

        //recyclerview
//        postToList()
//
//        //recyclerview
//        binding.rvRiwayatrujukananak.layoutManager = LinearLayoutManager(this)
//        binding.rvRiwayatrujukananak.adapter = RecyclerAdapterRujukan(posyandulist, tanggallist, namaanaklist, namabidanlist,
//            puskesmaslist, penyakitlist)
    }

    private fun getallRujukanID(idnya: String) {

        ApiService.endpoint.getallRujukanID(idnya)
            .enqueue(object : Callback<ListRujukanAnak>{
                override fun onResponse(
                    call: Call<ListRujukanAnak>,
                    response: Response<ListRujukanAnak>
                ) {
                    val status = response.body()?.status
                    val data = response.body()?.data
                    Log.d("responseDataRujukanAnak", data.toString())
                    if (status == "success" && data != null){
                        rv_listRujukanAnakOrtu.adapter = AdapterRiwayatRujukanAnak(this@RiwayatRujukAnakOrangtuaActivity, data)
                        rv_listRujukanAnakOrtu.layoutManager= LinearLayoutManager(this@RiwayatRujukAnakOrangtuaActivity)
                    }else{
                        Toast.makeText(
                            this@RiwayatRujukAnakOrangtuaActivity, "Belum ada List",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onFailure(call: Call<ListRujukanAnak>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
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
}