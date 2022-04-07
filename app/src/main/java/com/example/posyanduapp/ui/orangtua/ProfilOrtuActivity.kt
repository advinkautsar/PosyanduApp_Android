package com.example.posyanduapp.ui.orangtua

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import cn.pedant.SweetAlert.SweetAlertDialog
import com.example.posyanduapp.Helper.SharedPref
import com.example.posyanduapp.Model.*
import com.example.posyanduapp.databinding.ActivityProfilOrtuBinding
import com.example.posyanduapp.model.ResponsePesan
import com.example.posyanduapp.retrofit.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfilOrtuActivity : AppCompatActivity() {

    private lateinit var binding : ActivityProfilOrtuBinding
    lateinit var ProfilOrtu: GetProfilOrtu.Result
    var id_kecamatan: Int = 0
    var id_desa_kelurahan: Int = 0

    var ListKecamatan : ArrayList<ListKecamatan.Result> = ArrayList()
    var ListDesaKelurahan : ArrayList<ListKelurahanDesa.Result> = ArrayList()
    lateinit var pDialog: SweetAlertDialog


    private lateinit var s: SharedPref


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfilOrtuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        s = SharedPref(this)
        getprofilortu()
        getkecamatan()
        getdesakelurahan()

        //Intent Button
        binding.btnKembaliproflortu.setOnClickListener {
            startActivity(Intent(this, BerandaOrtuActivity::class.java))
        }
        binding.btnUbahprofil.setOnClickListener {
            try {
                if ( binding.edtNamapengguna.text!!.trim().isEmpty() ||
                            binding.edtNohp.text!!.trim().isEmpty() ||
                            binding.edtNIKayah.text!!.trim().isEmpty() ||
                            binding.edtNamaayah.text!!.trim().isEmpty() ||
                            binding.edtNIKibu.text!!.trim().isEmpty() ||
                            binding.edtNamaibu.text!!.trim().isEmpty() ||
                            binding.edtAlamatrumah.text!!.trim().isEmpty() ||
                            binding.edtRt.text!!.trim().isEmpty() ||
                            binding.edtRw.text!!.trim().isEmpty()
                    ){
                        pDialog = SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                            .setTitleText("Isi Semua Form")
                            .setConfirmClickListener {
                                hideDialog()
                        }
                        showDialog()

                }else{
                    updateprofilortu()
                }

            }catch (e: Exception) {
                e.printStackTrace()
            }
        }



        //Intent Dropdown
        binding.edtKecamatan.onItemClickListener =
            AdapterView.OnItemClickListener { arg0, arg1, position, arg3 ->
                id_kecamatan = ListKecamatan.get(position).id!!

                Log.d("your selected item", "" +id_kecamatan)
            }

        binding.edtDesaKelurahan.onItemClickListener =
            AdapterView.OnItemClickListener { arg0, arg1, position, arg3 ->
                id_desa_kelurahan = ListDesaKelurahan.get(position).id!!

                Log.d("your selected item", "" +id_desa_kelurahan)
            }



    }

    private fun updateprofilortu() {
        val user = s.getUser()!!
        Log.d("idne mase",user.id.toString())

        ApiService.endpoint.updateProfilOrtu(
            user.id,
            binding.edtNamapengguna.text.toString(),
            binding.edtPassword.text.toString(),
            binding.edtNohp.text.toString(),
            binding.edtNIKayah.text.toString(),
            binding.edtNamaayah.text.toString(),
            binding.edtNIKibu.text.toString(),
            binding.edtNamaibu.text.toString(),
            binding.edtAlamatrumah.text.toString(),
            binding.edtRt.text.toString(),
            binding.edtRw.text.toString(),
            id_kecamatan.toString(),
            id_desa_kelurahan.toString(),
        )
            .enqueue(object : Callback<ResponsePesan>{
                override fun onResponse(
                    call: Call<ResponsePesan>,
                    response: Response<ResponsePesan>
                ) {
                    val status = response.body()?.status
                    val pesanan = response.body()?.pesan

                    if (status!!){
                        Toast.makeText(this@ProfilOrtuActivity, pesanan, Toast.LENGTH_LONG).show()
                        finish()
                    } else{
                        Toast.makeText(this@ProfilOrtuActivity, pesanan, Toast.LENGTH_LONG).show()

                    }
                }

                override fun onFailure(call: Call<ResponsePesan>, t: Throwable) {
                    t.printStackTrace()
                    Toast.makeText(this@ProfilOrtuActivity ,"Kesalahan tidak terduga!", Toast.LENGTH_LONG).show()
                }

            })
    }
//
    private fun getdesakelurahan() {
        ApiService.endpoint.getKelurahan_Desa()
            .enqueue(object : Callback<ListKelurahanDesa>{
                override fun onResponse(
                    call: Call<ListKelurahanDesa>,
                    response: Response<ListKelurahanDesa>
                ) {
                    val status = response.body()?.status
                    val data = response.body()?.data
                    Log.d("responseListDesa", data.toString())
                    if (status == "success" && data != null){
                        ListDesaKelurahan = data

                        val adapter = ArrayAdapter(applicationContext, android.R.layout.simple_list_item_1, ListDesaKelurahan)
                        binding.edtDesaKelurahan.setAdapter(adapter)
                    }
                }

                override fun onFailure(call: Call<ListKelurahanDesa>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
    }
//
    private fun getkecamatan() {
        ApiService.endpoint.getKecamatan()
            .enqueue(object : Callback<ListKecamatan>{
                override fun onResponse(
                    call: Call<ListKecamatan>,
                    response: Response<ListKecamatan>
                ) {
                    val status = response.body()?.status
                    val data = response.body()?.data
                    Log.d("responseListKec", data.toString())
                    if (status == "success" && data != null){
                        ListKecamatan = data
                        val adapter = ArrayAdapter(applicationContext, android.R.layout.simple_list_item_1, ListKecamatan)
                        binding.edtKecamatan.setAdapter(adapter)
                    }
                }

                override fun onFailure(call: Call<ListKecamatan>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
    }

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

    private fun showDialog() {
        if (!pDialog.isShowing) pDialog.show()
    }

    private fun hideDialog() {
        if (pDialog.isShowing) pDialog.dismiss()
    }
}