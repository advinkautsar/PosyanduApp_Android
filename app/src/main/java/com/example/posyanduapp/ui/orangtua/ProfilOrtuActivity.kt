package com.example.posyanduapp.ui.orangtua

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import com.example.posyanduapp.Helper.SharedPref
import com.example.posyanduapp.Model.GetProfilOrtu
import com.example.posyanduapp.Model.Orangtua
import com.example.posyanduapp.R
import com.example.posyanduapp.databinding.ActivityProfilOrtuBinding
import com.example.posyanduapp.retrofit.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfilOrtuActivity : AppCompatActivity() {

    private lateinit var binding : ActivityProfilOrtuBinding
    lateinit var ProfilOrtu: GetProfilOrtu.Result
    var id_kecamatan: Int = 0
    var id_desa_kelurahan: Int = 0


    private lateinit var s: SharedPref


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfilOrtuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        s = SharedPref(this)
        getprofilortu()

//        getkecamatan()
//        getdesakelurahan()
//        updateprofilortu()

        binding.btnKembaliproflortu.setOnClickListener {
            startActivity(Intent(this, BerandaOrtuActivity::class.java))
        }
    }

//    private fun updateprofilortu() {
//        TODO("Not yet implemented")
//    }
//
//    private fun getdesakelurahan() {
//        TODO("Not yet implemented")
//    }
//
//    private fun getkecamatan() {
//        TODO("Not yet implemented")
//    }

    private fun getprofilortu() {
        val user = s.getUser()!!
        Log.d("idne mase",user.id.toString())

        ApiService.endpoint.getProfilOrtu(user.id)
            .enqueue(object : Callback<GetProfilOrtu>{
                override fun onResponse(
                    call: Call<GetProfilOrtu>,
                    response: Response<GetProfilOrtu>
                ) {
                    val status = response.body()?.status
                    val data = response.body()?.data
                    Log.d("responseProfilOrtu", data.toString())
                    if (status == "success" && data != null){
                        ProfilOrtu = data

                        id_kecamatan = ProfilOrtu.kecamatan_id!!
                        id_desa_kelurahan = ProfilOrtu.desa_kelurahan_id!!

                        binding.edtNamaayah.setText(ProfilOrtu.nama_ayah)
                        binding.edtNIKayah.setText(ProfilOrtu.nik_ayah)
                        binding.edtNIKibu.setText(ProfilOrtu.nik_ibu)
                        binding.edtNamaibu.setText(ProfilOrtu.nama_ibu)
                        binding.edtAlamatrumah.setText(ProfilOrtu.alamat)
                        binding.edtRt.setText(ProfilOrtu.rt)
                        binding.edtRw.setText(ProfilOrtu.rw)
                        binding.edtDesaKelurahan.setText(ProfilOrtu.nama)
                        binding.edtKecamatan.setText(ProfilOrtu.nama_kecamatan)
                        binding.edtNamapengguna.setText(ProfilOrtu.nama_pengguna)
                        binding.edtNohp.setText(ProfilOrtu.no_hp)
                    }
                }

                override fun onFailure(call: Call<GetProfilOrtu>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
    }
}