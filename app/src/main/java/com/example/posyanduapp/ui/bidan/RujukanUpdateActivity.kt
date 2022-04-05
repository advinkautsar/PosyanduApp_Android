package com.example.posyanduapp.ui.bidan

import android.R
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import cn.pedant.SweetAlert.SweetAlertDialog
import com.example.posyanduapp.Helper.SharedPref
import com.example.posyanduapp.databinding.ActivityRujukanUpdateBinding
import com.example.posyanduapp.model.*
import com.example.posyanduapp.retrofit.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.math.BigInteger
import java.util.*

class RujukanUpdateActivity : AppCompatActivity() {

    private lateinit var binding : ActivityRujukanUpdateBinding
    var nik_anak: String=""
    var listAnak: ArrayList<ListAnak.Result> = ArrayList()
    lateinit var Rujukantunggal:GetRujukan.Result
    lateinit var Bidan: Bidan.Result
    var id_bidan :Int = 0
    var id_puskesmas: Int = 0
    var id_posyandu: Int = 0
    var idnya: String = ""
    var listPosyandus: ArrayList<ListPosyandu.Result> = ArrayList()
    var listPuskesmas: ArrayList<ListPuskesmas.Result> = ArrayList()
    private lateinit var s: SharedPref
    lateinit var pDialog: SweetAlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_rujukan_update)
        binding = ActivityRujukanUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)
        idnya = intent.getStringExtra("idrujukan").toString()
        s = SharedPref(this)


        if (idnya!=null){
            getdatarujukan_id(idnya)

//            Log.d("enek lo","ada"+idnya)
        }
        getlisPosyandu()
        getlisPuskesmas()
        getlistanak()
        getbidan()

        binding.rujukanNamaanak.onItemClickListener =
            AdapterView.OnItemClickListener { arg0, arg1, position, arg3 ->
                nik_anak = listAnak.get(position).nik_anak.toString()

                Log.d("your selected item", "" + nik_anak)
            }

        binding.rujukanNamaposyandu.onItemClickListener =
            AdapterView.OnItemClickListener { arg0, arg1, position, arg3 ->
                id_posyandu = listPosyandus.get(position).id!!

                Log.d("your selected item", "" +id_posyandu)
            }

        binding.rujukanNamapuskesmas.onItemClickListener =
            AdapterView.OnItemClickListener { arg0, arg1, position, arg3 ->
                id_puskesmas = listPuskesmas.get(position).id!!

                Log.d("your selected item", "" +id_puskesmas)
            }

        //datepicker
        val calendar = Calendar.getInstance()
        val year = calendar[Calendar.YEAR]
        val month = calendar[Calendar.MONTH]
        val day = calendar[Calendar.DAY_OF_MONTH]

        //intent back
//        val btn_backtoberandabidan = findViewById(com.example.posyanduapp.R.id.back_toberandabidan) as ImageView
//        btn_backtoberandabidan.setOnClickListener {
//            startActivity(Intent(this, RiwayatrujukanActivity::class.java))
//        }

        binding.rujukanTanggalrujuk.setOnClickListener {
            val datePickerDialog = DatePickerDialog(
                this, { view, year, month, day ->
                    var month = month
                    month = month + 1
                    val urutan = "$year-$month-$day"
                    binding.rujukanTanggalrujuk.setText(urutan)
                }, year, month, day
            )
            datePickerDialog.show()
        }

        binding.btnUpdatenrujukan.setOnClickListener{

            try {
//
                if (binding.rujukanNamaanak.text.trim().isEmpty() || binding.rujukanNamaposyandu.text.trim()
                        .isEmpty() || binding.rujukanNamapuskesmas.text.trim().isEmpty()|| binding.rujukanKeluhan.text.toString().trim().isEmpty()
                    || binding.rujukanTanggalrujuk.text.trim().isEmpty()
                ) {

                    pDialog = SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("Isi Semua Form")
                        .setConfirmClickListener {
                            hideDialog()
                        }
                    showDialog()
                } else {
                    PostRujukan()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
    }
    private fun getdatarujukan_id(idnya: String) {
        Toast.makeText(this@RujukanUpdateActivity, idnya, Toast.LENGTH_LONG).show()

        ApiService.endpoint.getRujukanID(idnya.toInt())
            .enqueue(object : Callback<GetRujukan> {
                override fun onResponse(
                    call: Call<GetRujukan>,
                    response: Response<GetRujukan>
                ) {
                    val status = response.body()?.status
                    val data = response.body()?.data
                    Log.d("responseruju", data.toString())
                    if (status == "success" && data != null) {
                        // set adapter and layout manager for rv
                        Rujukantunggal = data
//                        id_bidan = Bidan.id!!
                        Log.d("responseDatarujukan", Rujukantunggal.toString())
                        nik_anak = Rujukantunggal.nik_anak.toString()
                        id_posyandu = Rujukantunggal.id_posyandu!!
                        id_puskesmas = Rujukantunggal.puskesmas_id!!

                        binding.rujukanKeluhan.setText(Rujukantunggal.keluhan_anak)
                        binding.rujukanTanggalrujuk.setText(Rujukantunggal.tanggal_rujukan)
                        binding.rujukanNamaposyandu.setText(Rujukantunggal.nama_posyandu)
                        binding.rujukanNamapuskesmas.setText(Rujukantunggal.nama_puskesmas)
                        binding.rujukanNamaanak.setText(Rujukantunggal.nama_anak)

                    }
                }

                override fun onFailure(call: Call<GetRujukan>, t: Throwable) {

                }

            })



    }
    private fun getlisPosyandu() {
        ApiService.endpoint.getposyandu()
            .enqueue(object : Callback<ListPosyandu> {
                override fun onResponse(
                    call: Call<ListPosyandu>,
                    response: Response<ListPosyandu>
                ) {
                    val status = response.body()?.status
                    val data = response.body()?.data
                    Log.d("responseData", data.toString())
                    if (status == "success" && data != null) {
                        // set adapter and layout manager for rv
                        listPosyandus = data
                        val adapter = ArrayAdapter(applicationContext, R.layout.simple_list_item_1, listPosyandus)

                        binding.rujukanNamaposyandu.setAdapter(adapter)
                    }
                }

                override fun onFailure(call: Call<ListPosyandu>, t: Throwable) {

                }

            })
    }

    private fun getlisPuskesmas() {
        ApiService.endpoint.getpuskesmas()
            .enqueue(object : Callback<ListPuskesmas> {
                override fun onResponse(
                    call: Call<ListPuskesmas>,
                    response: Response<ListPuskesmas>
                ) {
                    val status = response.body()?.status
                    val data = response.body()?.data
                    Log.d("responseData", data.toString())
                    if (status == "success" && data != null) {
                        // set adapter and layout manager for rv
                        listPuskesmas = data
                        val adapter = ArrayAdapter(applicationContext, R.layout.simple_list_item_1, listPuskesmas)
                        binding.rujukanNamapuskesmas.setAdapter(adapter)
                    }
                }

                override fun onFailure(call: Call<ListPuskesmas>, t: Throwable) {

                }

            })
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

                        val adapter = ArrayAdapter(applicationContext, R.layout.simple_list_item_1, listAnak)

                        binding.rujukanNamaanak.setAdapter(adapter)
                    }
                }

                override fun onFailure(call: Call<ListAnak>, t: Throwable) {

                }

            })
    }

    private fun PostRujukan() {


        ApiService.endpoint.updateRujukan(
            idnya,
            binding.rujukanTanggalrujuk.text.toString(),
            binding.rujukanKeluhan.text.toString(),
            id_posyandu.toString(),
            id_puskesmas.toString(),
            id_bidan.toString(),
            nik_anak.toString()
        ).enqueue(object : Callback<ResponsePesan> {
            override fun onResponse(
                call: Call<ResponsePesan>,
                response: Response<ResponsePesan>
            ) {
                val status = response.body()?.status
                val pesanan = response.body()?.pesan

                if (status!!) {
                    Toast.makeText(this@RujukanUpdateActivity, pesanan, Toast.LENGTH_LONG).show()
//                    this@KelolajadwalimunisasiActivity.fragmentManager?.popBackStack()
                    finish()
                } else {
                    Toast.makeText(this@RujukanUpdateActivity, pesanan, Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<ResponsePesan>, t: Throwable) {
                t.printStackTrace()
                Toast.makeText(this@RujukanUpdateActivity ,"Kesalahan tidak terduga!", Toast.LENGTH_LONG).show()

            }

        })
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
                    Log.d("responseDatabidan", data.toString())
                    if (status == "success" && data != null) {
                        // set adapter and layout manager for rv
                        Bidan = data
                        id_bidan = Bidan.id!!
//                        Log.d("responseDatabidan", Bidan.id.toString())
                        Log.d("responseDatabidan2", Bidan.nama_bidan.toString())

                    }
                }

                override fun onFailure(call: Call<Bidan>, t: Throwable) {

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