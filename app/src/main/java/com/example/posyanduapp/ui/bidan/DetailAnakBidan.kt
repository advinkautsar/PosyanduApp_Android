package com.example.posyanduapp.ui.bidan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.posyanduapp.Helper.SharedPref
import com.example.posyanduapp.Model.GetDetailAnak
import com.example.posyanduapp.R
import com.example.posyanduapp.databinding.ActivityDetailAnakBidanBinding
import com.example.posyanduapp.model.GetRujukan
import com.example.posyanduapp.retrofit.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailAnakBidan : AppCompatActivity() {
    private lateinit var binding: ActivityDetailAnakBidanBinding
    var idnya: String = ""
    lateinit var DataDetailAnakBidan: GetDetailAnak.Result
    private lateinit var s: SharedPref


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailAnakBidanBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //intent
        binding.btnBackdetailanakbidan.setOnClickListener {
            startActivity(Intent(this,AnakActivity::class.java))
        }

        idnya = intent.getStringExtra("nik").toString()
        s = SharedPref(this)

        if (idnya!=null){
            getDetailAnakID(idnya)
            Log.d("enek lo","ada"+idnya)
        }


    }
   private fun getDetailAnakID(idnya : String){

        ApiService.endpoint.getDetailAnakID(idnya)
            .enqueue(object: Callback<GetDetailAnak>{
                override fun onResponse(
                    call: Call<GetDetailAnak>,
                    response: Response<GetDetailAnak>
                ) {
                    val status = response.body()?.status
                    val data = response.body()?.data
                    Log.d("respondetailanakbidan", data.toString())
                    if (status == "success" && data != null){
                        DataDetailAnakBidan = data

                        binding.edtNomorkartuanak.setText(DataDetailAnakBidan.no_kartu)
                        binding.edtNamaanak.setText(DataDetailAnakBidan.nama_anak)
                        binding.edtJeniskelaminanak.setText(DataDetailAnakBidan.jenis_kelamin)
                        binding.edtTanggallahiranak.setText(DataDetailAnakBidan.tanggal_lahir)
                        binding.edtBeratlahiranak.setText(DataDetailAnakBidan.berat_lahir)
                        binding.edtPanjanglahiranak.setText(DataDetailAnakBidan.panjang_lahir)
                        binding.edtNamaibuanak.setText(DataDetailAnakBidan.nama_ibu)




                    }
                }

                override fun onFailure(call: Call<GetDetailAnak>, t: Throwable) {
                    t.printStackTrace()
                    Toast.makeText(this@DetailAnakBidan ,"Kesalahan tidak terduga!", Toast.LENGTH_LONG).show()
                }

            })

    }
}