package com.example.posyanduapp.ui.kader

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import cn.pedant.SweetAlert.SweetAlertDialog
import com.example.posyanduapp.Helper.SharedPref
import com.example.posyanduapp.Model.GetDetailAnak
import com.example.posyanduapp.Model.GetDetailOrtu
import com.example.posyanduapp.Model.ListPersetujuan
import com.example.posyanduapp.R
import com.example.posyanduapp.databinding.ActivityReadProfilOrtuBinding
import com.example.posyanduapp.model.ResponsePesan
import com.example.posyanduapp.retrofit.ApiService
import com.example.posyanduapp.ui.orangtua.ProfilOrtuActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ReadProfilOrtuActivity : AppCompatActivity() {

    var idnya: String = ""
    lateinit var DataProfilOrtu: GetDetailOrtu.Result
    lateinit var binding: ActivityReadProfilOrtuBinding

    private lateinit var s: SharedPref
    lateinit var pDialog: SweetAlertDialog

    lateinit var status_persetujuan:String
    var liststatuspersetujuan: ArrayList<ListPersetujuan.Result> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReadProfilOrtuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        idnya = intent.getStringExtra("idnya").toString()
        s = SharedPref(this)
        getstatuspersetujuan()

        if (idnya!=null){
            getProfilOrtu(idnya)
            Log.d("enek lo","ada"+idnya)
        }


        //intent
        binding.btnKembaliproflortu.setOnClickListener {
            startActivity(Intent(this,OrangtuaActivity::class.java))
        }
        binding.btnUbahprofilortu.setOnClickListener {
            try {
                if (
                    binding.edtStatusPersetujuan.text!!.trim().isEmpty()
                ){
                    pDialog = SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("Status Persetujuan tidak boleh kosong")
                        .setConfirmClickListener {
                            hideDialog()
                        }
                    showDialog()

                }else{
                    ubahpersetujuan()
                }

            }catch (e: Exception) {
                e.printStackTrace()
            }
        }

        binding.edtStatusPersetujuan.onItemClickListener =
            AdapterView.OnItemClickListener { arg0, arg1, position, arg3 ->
                status_persetujuan= liststatuspersetujuan.get(position).toString()


                Log.d("your selected item", "" + status_persetujuan)
            }
    }

    private fun getstatuspersetujuan() {
        ApiService.endpoint.getstatuspersetujuan()
            .enqueue(object : Callback<ListPersetujuan>{
                override fun onResponse(
                    call: Call<ListPersetujuan>,
                    response: Response<ListPersetujuan>
                ) {
                    val status = response.body()?.status
                    val data = response.body()?.data
                    Log.d("responseData2", data.toString())
                    if (status == "success" && data != null) {
                        // set adapter and layout manager for rv
                        liststatuspersetujuan = data

                        val adapterpersetujuan= ArrayAdapter(applicationContext, android.R.layout.simple_list_item_1, liststatuspersetujuan)

                        binding.edtStatusPersetujuan.setAdapter(adapterpersetujuan)
                    }

                }

                override fun onFailure(call: Call<ListPersetujuan>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
    }
    private fun ubahpersetujuan() {
        ApiService.endpoint.ubah_persetujuan(idnya,binding.edtStatusPersetujuan.text.toString())
            .enqueue(object : Callback<ResponsePesan>{
                override fun onResponse(
                    call: Call<ResponsePesan>,
                    response: Response<ResponsePesan>
                ) {
                    val status = response.body()?.status
                    val pesanan = response.body()?.pesan

                    if (status!!){
                        Toast.makeText(this@ReadProfilOrtuActivity, pesanan, Toast.LENGTH_LONG).show()
                        finish()
                    } else{
                        Toast.makeText(this@ReadProfilOrtuActivity, pesanan, Toast.LENGTH_LONG).show()

                    }
                }

                override fun onFailure(call: Call<ResponsePesan>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
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
                        DataProfilOrtu = data

                        binding.edtNIKayah.setText(DataProfilOrtu.nik_ayah)
                        binding.edtNamaayah.setText(DataProfilOrtu.nama_ayah)
                        binding.edtNIKibu.setText(DataProfilOrtu.nik_ibu)
                        binding.edtNamaibu.setText(DataProfilOrtu.nama_ibu)
                        binding.edtAlamatrumah.setText(DataProfilOrtu.alamat)
                        binding.edtDesakelurahan.setText(DataProfilOrtu.nama)
                        binding.edtKecamtan.setText(DataProfilOrtu.nama_kecamatan)
                        binding.edtRt.setText(DataProfilOrtu.rt)
                        binding.edtRw.setText(DataProfilOrtu.rw)
                        binding.edtStatusPersetujuan.setText(DataProfilOrtu.status_persetujuan)

                    }
                }

                override fun onFailure(call: Call<GetDetailOrtu>, t: Throwable) {
                    t.printStackTrace()
                    Toast.makeText(this@ReadProfilOrtuActivity ,"Kesalahan tidak terduga!", Toast.LENGTH_LONG).show()
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