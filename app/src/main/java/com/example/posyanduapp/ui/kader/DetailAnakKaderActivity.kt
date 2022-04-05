package com.example.posyanduapp.ui.kader

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.posyanduapp.Helper.SharedPref
import com.example.posyanduapp.Model.GetDetailAnak
import com.example.posyanduapp.R
import com.example.posyanduapp.databinding.ActivityDetailAnakKaderBinding
import com.example.posyanduapp.retrofit.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailAnakKaderActivity : AppCompatActivity() {

    lateinit var binding: ActivityDetailAnakKaderBinding
    var idnya: String = ""
    lateinit var DataDetailAnakader: GetDetailAnak.Result
    private lateinit var s: SharedPref

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailAnakKaderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        idnya = intent.getStringExtra("nik").toString()
        s = SharedPref(this)

        //intent
        binding.btnBackdetailanakkader.setOnClickListener {
            startActivity(Intent(this,FiturAnakKaderActivity::class.java))
        }

        if (idnya!=null){
            getDetailAnakID(idnya)
            Log.d("enek lo","ada"+idnya)
        }


    }

    private fun getDetailAnakID(idnya: String) {
        ApiService.endpoint.getDetailAnakID(idnya)
            .enqueue(object: Callback<GetDetailAnak> {
                override fun onResponse(
                    call: Call<GetDetailAnak>,
                    response: Response<GetDetailAnak>
                ) {
                    val status = response.body()?.status
                    val data = response.body()?.data
                    Log.d("respondetailanakbidan", data.toString())
                    if (status == "success" && data != null){
                        DataDetailAnakader = data

                        binding.edtNomorkartuanak.setText(DataDetailAnakader.no_kartu)
                        binding.edtNamaanak.setText(DataDetailAnakader.nama_anak)
                        binding.edtJeniskelaminanak.setText(DataDetailAnakader.jenis_kelamin)
                        binding.edtTanggallahiranak.setText(DataDetailAnakader.tanggal_lahir)
                        binding.edtBeratlahiranak.setText(DataDetailAnakader.berat_lahir)
                        binding.edtPanjanglahiranak.setText(DataDetailAnakader.panjang_lahir)
                        binding.edtNamaibuanak.setText(DataDetailAnakader.nama_ibu)




                    }
                }

                override fun onFailure(call: Call<GetDetailAnak>, t: Throwable) {
                    t.printStackTrace()
                    Toast.makeText(this@DetailAnakKaderActivity ,"Kesalahan tidak terduga!", Toast.LENGTH_LONG).show()
                }

            })
    }
}