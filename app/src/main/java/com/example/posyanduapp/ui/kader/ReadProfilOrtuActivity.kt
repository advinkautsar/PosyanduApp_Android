package com.example.posyanduapp.ui.kader

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.posyanduapp.Helper.SharedPref
import com.example.posyanduapp.Model.GetDetailAnak
import com.example.posyanduapp.Model.GetDetailOrtu
import com.example.posyanduapp.R
import com.example.posyanduapp.databinding.ActivityReadProfilOrtuBinding
import com.example.posyanduapp.retrofit.ApiService
import com.example.posyanduapp.ui.orangtua.ProfilOrtuActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ReadProfilOrtuActivity : AppCompatActivity() {

    var idnya: String = ""
    lateinit var DataDetailOrtu: GetDetailOrtu.Result
    lateinit var binding: ActivityReadProfilOrtuBinding
    private lateinit var s: SharedPref


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReadProfilOrtuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        idnya = intent.getStringExtra("idnya").toString()
        s = SharedPref(this)

        if (idnya!=null){
            getProfilOrtu(idnya)
            Log.d("enek lo","ada"+idnya)
        }


        //intent
        binding.btnKembaliproflortu.setOnClickListener {
            startActivity(Intent(this,OrangtuaActivity::class.java))
        }



    }

    private fun getProfilOrtu(idnya: String) {

        ApiService.endpoint.getProfilOrtu(idnya)
            .enqueue(object : Callback<GetDetailOrtu>{
                override fun onResponse(
                    call: Call<GetDetailOrtu>,
                    response: Response<GetDetailOrtu>
                ) {
                    val status = response.body()?.status
                    val data = response.body()?.data
                    Log.d("respondReadProfilOrtu", data.toString())
                    if (status == "success" && data != null){
                        DataDetailOrtu = data

                        binding.edtNIKayah.setText(DataDetailOrtu.nik_ayah)
                        binding.edtNamaayah.setText(DataDetailOrtu.nama_ayah)
                        binding.edtNIKibu.setText(DataDetailOrtu.nik_ibu)
                        binding.edtNamaibu.setText(DataDetailOrtu.nama_ibu)
                        binding.edtAlamatrumah.setText(DataDetailOrtu.alamat)
                        binding.edtDesakelurahan.setText(DataDetailOrtu.nama)
                        binding.edtKecamtan.setText(DataDetailOrtu.nama_kecamatan)
                        binding.edtRt.setText(DataDetailOrtu.rt)
                        binding.edtRw.setText(DataDetailOrtu.rw)

                    }
                }

                override fun onFailure(call: Call<GetDetailOrtu>, t: Throwable) {
                    t.printStackTrace()
                    Toast.makeText(this@ReadProfilOrtuActivity ,"Kesalahan tidak terduga!", Toast.LENGTH_LONG).show()
                }

            })
    }
}