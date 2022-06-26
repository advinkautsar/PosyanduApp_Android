package com.example.posyanduapp.ui.bidan

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import cn.pedant.SweetAlert.SweetAlertDialog
import com.example.posyanduapp.Helper.SharedPref
import com.example.posyanduapp.R
import com.example.posyanduapp.databinding.ActivityKelolajadwalimunisasiBinding
import com.example.posyanduapp.model.Bidan
import com.example.posyanduapp.model.ListAnak
import com.example.posyanduapp.model.ListImunisasi
import com.example.posyanduapp.model.ResponsePesan
import com.example.posyanduapp.retrofit.ApiService
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.math.BigInteger
import java.util.*


class KelolajadwalimunisasiActivity : AppCompatActivity() {
    var listAnak: ArrayList<ListAnak.Result> = ArrayList()
    var listImunisasi: ArrayList<ListImunisasi.Result> = ArrayList()
    lateinit var nik_anak: BigInteger
    var id_imun :Int = 0
    lateinit var pDialog: SweetAlertDialog
    lateinit var Bidan: Bidan.Result
    var id_bidan :Int = 0
    private lateinit var s: SharedPref

    private lateinit var binding : ActivityKelolajadwalimunisasiBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKelolajadwalimunisasiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        s = SharedPref(this)
        getlistanak()
        getlistImunisasi()
        getbidan()

        //datepicker
        val calendar = Calendar.getInstance()
        val year = calendar[Calendar.YEAR]
        val month = calendar[Calendar.MONTH]
        val day = calendar[Calendar.DAY_OF_MONTH]

        val btn_kembali = findViewById<ImageView>(R.id.btn_kembalikelolajadwal)


        binding.kelolaimunisasiNamaanak.onItemClickListener =
            OnItemClickListener { arg0, arg1, position, arg3 ->
                nik_anak = listAnak.get(position).nik_anak

                Log.d("your selected item", "" +nik_anak)
            }
        binding.kelolaimunisasiNamavaksin.onItemClickListener =
            OnItemClickListener { arg0, arg1, position, arg3 ->
                id_imun = listImunisasi.get(position).id!!

                Log.d("your selected item2", "" +id_imun)
            }

//        val nama_vaksin = resources.getStringArray(R.array.imunisasi)
//        val adaptervaksin = ArrayAdapter(
//            this,
//            R.layout.dropdown_listanak,
//            nama_vaksin
//        )
//        with(binding.kelolaimunisasiNamavaksin){
//            setAdapter(adaptervaksin)
//            Log.d("your selected jenis", "" +nama_vaksin)
//        }


        //datepicker show
        binding.kelolaimunisasiTanggal.setOnClickListener {
            val datePickerDialog = DatePickerDialog(
                this, { view, year, month, day ->
                    var month = month
                    month = month + 1
                    val urutan = "$year-$month-$day"
                    binding.kelolaimunisasiTanggal.setText(urutan)
                }, year, month, day
            )
            datePickerDialog.show()
        }

        btn_kembali.setOnClickListener {
            startActivity(Intent(this, KesehatananakActivity::class.java))
        }
        binding.btnSimpanrujukan.setOnClickListener {

            try {
//
                if (binding.kelolaimunisasiNamaanak.text.trim().isEmpty() || binding.kelolaimunisasiNamavaksin.text.trim()
                        .isEmpty() || binding.kelolaimunisasiTanggal.text.trim().isEmpty()
                ) {

                    pDialog = SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("Isi Semua Form")
                        .setConfirmClickListener {
                            hideDialog()
                        }
                    showDialog()
                } else {
                  PostJadwalImunisasi()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

    }
    private fun getlistanak() {
        ApiService.endpoint.getanak()
            .enqueue(object : Callback<ListAnak> {
                override fun onResponse(
                    call: Call<ListAnak>,
                    response: Response<ListAnak>
                ) {
                    val status = response.body()?.status
                    val data = response.body()?.data
                    Log.d("responseData", data.toString())
                    if (status == "success" && data != null) {
                        // set adapter and layout manager for rv
                        listAnak = data
                        val adapter = ArrayAdapter(applicationContext, android.R.layout.simple_list_item_1, listAnak)

                        binding.kelolaimunisasiNamaanak.setAdapter(adapter)
                    }
                }

                override fun onFailure(call: Call<ListAnak>, t: Throwable) {

                }

            })
    }

    private fun getlistImunisasi() {
        ApiService.endpoint.getimunisasi()
            .enqueue(object : Callback<ListImunisasi> {
                override fun onResponse(
                    call: Call<ListImunisasi>,
                    response: Response<ListImunisasi>
                ) {
                    val status = response.body()?.status
                    val data = response.body()?.data
                    Log.d("responseData2", data.toString())
                    if (status == "success" && data != null) {
                        // set adapter and layout manager for rv
                        listImunisasi = data
                        val adapter = ArrayAdapter(applicationContext, android.R.layout.simple_list_item_1, listImunisasi)

                        binding.kelolaimunisasiNamavaksin.setAdapter(adapter)
                    }
                }

                override fun onFailure(call: Call<ListImunisasi>, t: Throwable) {

                }

            })
    }
    private fun PostJadwalImunisasi() {
        ApiService.endpoint.postJadwalImunisasi(
            binding.kelolaimunisasiTanggal.text.toString()
            ,id_bidan.toString(),
            nik_anak.toString(),
            id_imun.toString()
        ).enqueue(object : Callback<ResponsePesan> {
            override fun onResponse(
                call: Call<ResponsePesan>,
                response: Response<ResponsePesan>
            ) {
                val status = response.body()?.status
                val pesanan = response.body()?.pesan

                if (status!!) {
                    Toast.makeText(this@KelolajadwalimunisasiActivity, pesanan, Toast.LENGTH_LONG).show()
//                    this@KelolajadwalimunisasiActivity.fragmentManager?.popBackStack()
                    finish()
                } else {
                    Toast.makeText(this@KelolajadwalimunisasiActivity, pesanan, Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<ResponsePesan>, t: Throwable) {
                t.printStackTrace()
                Toast.makeText(this@KelolajadwalimunisasiActivity,
                    "Kesalahan tidak terduga!", Toast.LENGTH_LONG).show()

            }

        })
    }
    private fun showDialog() {
        if (!pDialog.isShowing) pDialog.show()
    }

    private fun hideDialog() {
        if (pDialog.isShowing) pDialog.dismiss()
    }

    private fun getbidan() {
        val user = s.getUser()!!
        Log.d("idne mase",user.id.toString())

        ApiService.endpoint.getbidan(user.id)
            .enqueue(object : Callback<Bidan> {
                override fun onResponse(
                    call: Call<Bidan>,
                    response: Response<Bidan>
                ) {
                    val status = response.body()?.status
                    val data = response.body()?.data
                    Log.d("responseData", data.toString())
                    if (status == "success" && data != null) {
                        // set adapter and layout manager for rv
                        Bidan = data
                        id_bidan = Bidan.id!!
                        Log.d("responseData8", Bidan.id.toString())

                    }
                }

                override fun onFailure(call: Call<Bidan>, t: Throwable) {

                }

            })
    }


}