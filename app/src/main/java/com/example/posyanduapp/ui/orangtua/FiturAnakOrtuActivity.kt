package com.example.posyanduapp.ui.orangtua

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.posyanduapp.Helper.SharedPref
import com.example.posyanduapp.Model.ListAnakOrtu
import com.example.posyanduapp.Model.Orangtua
import com.example.posyanduapp.adapter.Orangtua.AdapterFiturAnakOrangtua
import com.example.posyanduapp.adapter.adapterKader.AdapterFiturAnakKader
import com.example.posyanduapp.databinding.ActivityFiturAnakOrtuBinding
import com.example.posyanduapp.retrofit.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FiturAnakOrtuActivity : AppCompatActivity() {

    lateinit var binding:ActivityFiturAnakOrtuBinding
    lateinit var rv_listAnakOrtu : RecyclerView
    lateinit var adapter : AdapterFiturAnakOrangtua
    lateinit var Orangtua: Orangtua.Result

    private lateinit var s: SharedPref

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFiturAnakOrtuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        rv_listAnakOrtu = binding.rvAnakOrtu
        s = SharedPref(this)

        getortu()
//        getdataAnakOrtu(id_ortu)


        //intent
        binding.btnKembalianak.setOnClickListener {
            startActivity(Intent(this,BerandaOrtuActivity::class.java))
        }
        binding.fabTambahanak.setOnClickListener {
            startActivity(Intent(this, FiturTambahAnakActivity::class.java))
        }


    }

//    private fun getdataAnakOrtu(id_Arttu: Int) {
//
//        ApiService.endpoint.getdataAnakOrtu(id_Arttu)
//            .enqueue(object:Callback<ListAnakOrtu>{
//                override fun onResponse(
//                    call: Call<ListAnakOrtu>,
//                    response: Response<ListAnakOrtu>
//                ) {
//                    val status = response.body()?.status
//                    val data = response.body()?.data
//                    Log.d("responseDataAnakOrtu", data.toString())
//                    if (status == "success" && data != null){
//                        rv_listAnakOrtu.adapter = AdapterFiturAnakOrangtua(this@FiturAnakOrtuActivity, data)
//                        rv_listAnakOrtu.layoutManager = LinearLayoutManager(this@FiturAnakOrtuActivity)
//                    }
//                }
//
//                override fun onFailure(call: Call<ListAnakOrtu>, t: Throwable) {
//                    TODO("Not yet implemented")
//                }
//
//            })
//
//    }

    private fun getortu() {
        val user = s.getUser()!!
        Log.d("idne mase",user.id.toString())

        ApiService.endpoint.getortu(user.id)
            .enqueue(object : Callback<Orangtua>{
                override fun onResponse(call: Call<Orangtua>, response: Response<Orangtua>) {
                    val status = response.body()?.status
                    val data = response.body()?.data
                    Log.d("responseDataOrtu", data.toString())
                    if (status == "success" && data != null){
                        rv_listAnakOrtu.adapter = AdapterFiturAnakOrangtua(this@FiturAnakOrtuActivity, data)
                        rv_listAnakOrtu.layoutManager = LinearLayoutManager(this@FiturAnakOrtuActivity)
                    }
                }

                override fun onFailure(call: Call<Orangtua>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
    }
}