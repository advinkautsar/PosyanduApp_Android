package com.example.posyanduapp.ui.orangtua

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.posyanduapp.Helper.SharedPref
import com.example.posyanduapp.adapter.AdapterAnak
import com.example.posyanduapp.adapter.AdapterNotifikasi
import com.example.posyanduapp.databinding.ActivityNotifikasiBinding
import com.example.posyanduapp.model.ListAnak
import com.example.posyanduapp.model.ListNotifikasi
import com.example.posyanduapp.retrofit.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NotifikasiActivity : AppCompatActivity() {

    private var titlelist = mutableListOf<String>()
    private var kontenlist = mutableListOf<String>()

    lateinit var rvListNotif: RecyclerView
    lateinit var adapter: AdapterNotifikasi
    private lateinit var s: SharedPref

    lateinit var binding : ActivityNotifikasiBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNotifikasiBinding.inflate(layoutInflater)
        setContentView(binding.root)
        rvListNotif = binding.rvNotif
        s = SharedPref(this)


        //intent
        binding.btnKembalinotif.setOnClickListener {
            startActivity(Intent(this, BerandaOrtuActivity::class.java))
        }
        getlistNotif()

        //swiper Refresh
        binding.swlayout.setOnRefreshListener {
            // Handler untuk menjalankan jeda selama 5 detik
            Handler().postDelayed({ // Berhenti berputar/refreshing
                binding.swlayout.isRefreshing = false
                getlistNotif()
                Toast.makeText(this, "Data diperbaharui", Toast.LENGTH_SHORT).show()
            }, 3000)

        }

        //add isi
//        postToList()
//
//        //rv
//        binding.rvNotif.layoutManager = LinearLayoutManager(this)
//        binding.rvNotif.adapter = RecyclerAdapterNotif(titlelist,kontenlist)
    }
//    private fun addToList(judul:String, Isi:String){
//        titlelist.add(judul)
//        kontenlist.add(Isi)
//
//    }
//    private fun postToList(){
//        for ( i in 1..25){
//            addToList("Jadwal Imunisasi Anak", "Halo ibu diah, imunisasi anak campak selanjutnya mulai tanggal : 12 Februari 2022" )
//        }
//
//    }
private fun getlistNotif() {
    val user = s.getUser()!!
    Log.d("idne mase",user.id.toString())
    try {

        ApiService.endpoint.getnotif(user.id)
            .enqueue(object : Callback<ListNotifikasi> {
                override fun onResponse(
                    call: Call<ListNotifikasi>,
                    response: Response<ListNotifikasi>
                ) {
                    val status = response.body()?.status
                    val data = response.body()?.data
                    Log.d("responseData", data.toString())
                    if (status == "success" && data != null) {
                        // set adapter and layout manager for rv
                        rvListNotif.adapter = AdapterNotifikasi(this@NotifikasiActivity, data)
                        rvListNotif.layoutManager = LinearLayoutManager(this@NotifikasiActivity)

                    } else {
                        Toast.makeText(
                            this@NotifikasiActivity, "Tidak ada List",
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

                override fun onFailure(call: Call<ListNotifikasi>, t: Throwable) {
                    Toast.makeText(this@NotifikasiActivity, t.message, Toast.LENGTH_SHORT).show()
                    Log.d("Error List", t.toString())
                }

            })
    } catch (e: Exception) {
        Log.d("catch error", e.message.toString())
        Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
    }
}
}