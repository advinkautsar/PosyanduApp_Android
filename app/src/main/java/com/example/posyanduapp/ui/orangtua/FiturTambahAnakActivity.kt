package com.example.posyanduapp.ui.orangtua

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import cn.pedant.SweetAlert.SweetAlertDialog
import com.example.posyanduapp.Helper.SharedPref
import com.example.posyanduapp.Model.GetOrangtua
import com.example.posyanduapp.R
import com.example.posyanduapp.databinding.ActivityFiturTambahAnakBinding
import com.example.posyanduapp.model.ResponsePesan
import com.example.posyanduapp.retrofit.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class FiturTambahAnakActivity : AppCompatActivity() {

    private lateinit var binding : ActivityFiturTambahAnakBinding
    lateinit var orangtua: GetOrangtua.Result
    var id_orangtua : Int = 0
    lateinit var jenis_kelamin:String


    private lateinit var s: SharedPref
    lateinit var pDialog: SweetAlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFiturTambahAnakBinding.inflate(layoutInflater)
        setContentView(binding.root)

        s = SharedPref(this)
        getOrtu()
        dropdownJK()

        //intent
        binding.btnBackFiturAnak.setOnClickListener {
            startActivity(Intent(this,FiturAnakOrtuActivity::class.java))
        }
        binding.btnSimpandataanak.setOnClickListener {
            try {
//
                if (binding.tambahNamaAnak.text!!.trim().isEmpty()  ||
                    binding.tambahNikAnak.text!!.trim().isEmpty()  ||
                    binding.tambahJeniskelaminAnak.text!!.trim().isEmpty()  ||
                    binding.tambahJeniskelaminAnak.text!!.trim().isEmpty()  ||
                    binding.tambahBeratlahirAnak.text!!.trim().isEmpty()  ||
                    binding.tambahPanjanglahirAnak.text!!.trim().isEmpty()

                ) {

                    pDialog = SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("Isi Semua Form")
                        .setConfirmClickListener {
                            hideDialog()
                        }
                    showDialog()
                } else {
                    tambahdataanak()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        //datepicker show
        val calendar = Calendar.getInstance()
        val year = calendar[Calendar.YEAR]
        val month = calendar[Calendar.MONTH]
        val day = calendar[Calendar.DAY_OF_MONTH]

        binding.tambahTanggallahirAnak.setOnClickListener {
            val datePickerDialog = DatePickerDialog(
                this, { view, year, month, day ->
                    var month = month
                    month = month + 1
                    val urutan = "$year-$month-$day"
                    binding.tambahTanggallahirAnak.setText(urutan)
                }, year, month, day
            )
            datePickerDialog.show()
        }
    }

    private fun dropdownJK() {
        val jenis_kelamin1 = resources.getStringArray(R.array.jenis_kelamin)
        val adapterJK = ArrayAdapter(
            this,
            R.layout.dropdown_listanak,
            jenis_kelamin1
        )
        with(binding.tambahJeniskelaminAnak) {
            setAdapter(adapterJK)
        }
        binding.tambahJeniskelaminAnak.onItemClickListener =
            AdapterView.OnItemClickListener { arg0, arg1, position, arg3 ->
                jenis_kelamin=  jenis_kelamin1.get(position)


                Log.d("your selected item", "" + jenis_kelamin)
            }
    }

    private fun tambahdataanak() {
        ApiService.endpoint.tambah_data_anak(
            binding.tambahNikAnak.text.toString(),id_orangtua.toString(),
            binding.tambahNamaAnak.text.toString(),binding.tambahJeniskelaminAnak.text.toString(),
            binding.tambahTanggallahirAnak.text.toString(),binding.tambahBeratlahirAnak.text.toString(),
            binding.tambahPanjanglahirAnak.text.toString()
        ).enqueue(object : Callback<ResponsePesan> {
            override fun onResponse(call: Call<ResponsePesan>, response: Response<ResponsePesan>) {
                val status = response.body()?.status
                val pesanan = response.body()?.pesan

                if (status!!) {
                    Toast.makeText(this@FiturTambahAnakActivity, pesanan, Toast.LENGTH_LONG).show()
//                    this@KelolajadwalimunisasiActivity.fragmentManager?.popBackStack()
                    finish()
                } else {
                    Toast.makeText(this@FiturTambahAnakActivity, pesanan, Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<ResponsePesan>, t: Throwable) {
                t.printStackTrace()
                Toast.makeText(this@FiturTambahAnakActivity, "Kesalahan tidak terduga!", Toast.LENGTH_LONG).show()
            }

        })
    }

    private fun getOrtu() {
        val user = s.getUser()!!
        Log.d("idne mase",user.id.toString())

        ApiService.endpoint.getuserortu(user.id)
            .enqueue(object : Callback<GetOrangtua> {
                override fun onResponse(call: Call<GetOrangtua>, response: Response<GetOrangtua>) {
                    val status = response.body()?.status
                    val data = response.body()?.data
                    Log.d("ResponseOrangtua", data.toString())
                    if (status == "success" && data != null) {
                        // set adapter and layout manager for rv
                        orangtua = data
                        id_orangtua = orangtua.id!!
//                        Log.d("responseDatabidan", Bidan.id.toString())
                        Log.d("ResponseNamaIbu", orangtua.nama_ibu.toString())
                    }
                }

                override fun onFailure(call: Call<GetOrangtua>, t: Throwable) {
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