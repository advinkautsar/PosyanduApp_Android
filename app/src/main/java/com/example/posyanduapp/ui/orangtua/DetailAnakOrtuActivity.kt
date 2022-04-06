package com.example.posyanduapp.ui.orangtua

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.posyanduapp.Helper.SharedPref
import com.example.posyanduapp.Model.GetDetailAnak
import com.example.posyanduapp.R
import com.example.posyanduapp.databinding.ActivityDetailAnakBinding
import com.example.posyanduapp.retrofit.ApiService
import com.example.posyanduapp.ui.bidan.AnakActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailAnakOrtuActivity : AppCompatActivity() {

    lateinit var binding : ActivityDetailAnakBinding
    var idnya: String = ""
    lateinit var DataDetailAnakOrtu: GetDetailAnak.Result
    private lateinit var s: SharedPref

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailAnakBinding.inflate(layoutInflater)
        setContentView(binding.root)

        idnya = intent.getStringExtra("nik").toString()
        s = SharedPref(this)

        //intent
        binding.btnBackdetailanak.setOnClickListener {
            startActivity(Intent(this, FiturAnakOrtuActivity::class.java))
        }


        if (idnya!=null){
            getDetailAnakID(idnya)
            Log.d("enek lo","ada"+idnya)
        }
    }

    private fun getDetailAnakID(idnya: String) {

        ApiService.endpoint.getDetailAnakID(idnya)
            .enqueue(object : Callback<GetDetailAnak>{
                override fun onResponse(
                    call: Call<GetDetailAnak>,
                    response: Response<GetDetailAnak>
                ) {
                    val status = response.body()?.status
                    val data = response.body()?.data
                    Log.d("respondetailanakOrtu", data.toString())
                    if (status == "success" && data != null){
                        DataDetailAnakOrtu = data

                        binding.edtNomorkartuanak.setText(DataDetailAnakOrtu.no_kartu)
                        binding.edtNamaanak.setText(DataDetailAnakOrtu.nama_anak)
                        binding.edtJeniskelaminanak.setText(DataDetailAnakOrtu.jenis_kelamin)
                        binding.edtTanggallahiranak.setText(DataDetailAnakOrtu.tanggal_lahir)
                        binding.edtBeratlahiranak.setText(DataDetailAnakOrtu.berat_lahir)
                        binding.edtPanjanglahiranak.setText(DataDetailAnakOrtu.panjang_lahir)
                        binding.edtNamaibuanak.setText(DataDetailAnakOrtu.nama_ibu)
                    }
                }

                override fun onFailure(call: Call<GetDetailAnak>, t: Throwable) {
                    t.printStackTrace()
                    Toast.makeText(this@DetailAnakOrtuActivity ,"Kesalahan tidak terduga!", Toast.LENGTH_LONG).show()
                }

            })
    }
}