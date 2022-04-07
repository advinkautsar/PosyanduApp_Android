package com.example.posyanduapp.ui.bidan

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import cn.pedant.SweetAlert.SweetAlertDialog
import com.example.posyanduapp.Helper.SharedPref
import com.example.posyanduapp.R
import com.example.posyanduapp.databinding.ActivityRujukanBinding
import com.example.posyanduapp.model.*
import com.example.posyanduapp.retrofit.ApiService
import kotlinx.android.synthetic.main.activity_berandabidan.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.math.BigInteger
import java.util.*

class RujukanCreateActivity : AppCompatActivity() {

    private lateinit var binding : ActivityRujukanBinding
    lateinit var nik_anak: BigInteger
    var listAnak: ArrayList<ListAnak.Result> = ArrayList()
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
        binding = ActivityRujukanBinding.inflate(layoutInflater)
        setContentView(binding.root)




        s = SharedPref(this)

        getlisPosyandu()
        getlisPuskesmas()
        getlistanak()
        getbidan()

        binding.rujukanNamaanak.onItemClickListener =
            AdapterView.OnItemClickListener { arg0, arg1, position, arg3 ->
                nik_anak = listAnak.get(position).nik_anak

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
        val btn_backtoberandabidan = findViewById(R.id.back_toberandabidan) as ImageView
        btn_backtoberandabidan.setOnClickListener {
            startActivity(Intent(this, RiwayatrujukanActivity::class.java))
        }

        dropdown()

        //datepicker show
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


        binding.btnSimpanrujukan.setOnClickListener{

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





    }

    fun dropdown(){
        //dropdown nama anak
//        val nama_anak = resources.getStringArray(R.array.daftar_namaanak)
//        val adapter = ArrayAdapter(
//            this,
//            R.layout.dropdown_listanak,
//            nama_anak
//        )
//        with(binding.rujukanNamaanak){
//            setAdapter(adapter)
//        }
//
//        //dropdown bidan
//        val nama_bidan = resources.getStringArray(R.array.nama_bidan)
//        val adapterbidan = ArrayAdapter(
//            this,
//            R.layout.dropdown_listanak,
//            nama_bidan
//        )
//        with(binding.rujukanNamabidan){
//            setAdapter(adapterbidan)
//        }
//
//        //dropdown puskesmas
//        val nama_puskesmas = resources.getStringArray(R.array.nama_puskesmas)
//        val adapterpus = ArrayAdapter(
//            this,
//            R.layout.dropdown_listanak,
//            nama_puskesmas
//        )
//        with(binding.rujukanNamapuskesmas){
//            setAdapter(adapterpus)
//        }
//
//        //dropdown nama posyandu
//        val nama_posyandu = resources.getStringArray(R.array.nama_posyandu)
//        val adapterposyandu = ArrayAdapter(
//            this,
//            R.layout.dropdown_listanak,
//            nama_posyandu
//        )
//        with(binding.rujukanNamaposyandu){
//            setAdapter(adapterposyandu)
//        }
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
                        val adapter = ArrayAdapter(applicationContext, android.R.layout.simple_list_item_1, listPosyandus)

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
                        val adapter = ArrayAdapter(applicationContext, android.R.layout.simple_list_item_1, listPuskesmas)
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

                        val adapter = ArrayAdapter(applicationContext, android.R.layout.simple_list_item_1, listAnak)

                        binding.rujukanNamaanak.setAdapter(adapter)
                    }
                }

                override fun onFailure(call: Call<ListAnak>, t: Throwable) {

                }

            })
    }

    private fun PostRujukan() {


        ApiService.endpoint.postRujukan(
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
                    Toast.makeText(this@RujukanCreateActivity, pesanan, Toast.LENGTH_LONG).show()
//                    this@KelolajadwalimunisasiActivity.fragmentManager?.popBackStack()
                    finish()
                } else {
                    Toast.makeText(this@RujukanCreateActivity, pesanan, Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<ResponsePesan>, t: Throwable) {
                t.printStackTrace()
                Toast.makeText(this@RujukanCreateActivity, "Kesalahan tidak terduga!", Toast.LENGTH_LONG).show()

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

    private fun showDialog() {
        if (!pDialog.isShowing) pDialog.show()
    }

    private fun hideDialog() {
        if (pDialog.isShowing) pDialog.dismiss()
    }


}

