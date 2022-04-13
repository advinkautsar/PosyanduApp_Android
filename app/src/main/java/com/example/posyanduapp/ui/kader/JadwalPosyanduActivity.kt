package com.example.posyanduapp.ui.kader

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.text.format.DateFormat
import android.util.Log
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AdapterView.OnItemClickListener
import android.widget.TimePicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import cn.pedant.SweetAlert.SweetAlertDialog
import com.example.posyanduapp.Helper.SharedPref
import com.example.posyanduapp.databinding.ActivityJadwalPosyanduBinding
import com.example.posyanduapp.model.Bidan
import com.example.posyanduapp.model.Kader
import com.example.posyanduapp.model.ListPosyandu
import com.example.posyanduapp.model.ResponsePesan
import com.example.posyanduapp.retrofit.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class JadwalPosyanduActivity : AppCompatActivity() {

    var listPosyandu: ArrayList<ListPosyandu.Result> = ArrayList()
    var id_posyandu: Int = 0
    private lateinit var binding: ActivityJadwalPosyanduBinding
    lateinit var pDialog: SweetAlertDialog
    lateinit var timePickerDialog: TimePickerDialog
    private lateinit var s: SharedPref
    lateinit var Kader: Kader.Result
    var id_kader :Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJadwalPosyanduBinding.inflate(layoutInflater)
        setContentView(binding.root)
        s = SharedPref(this)
        getlisPosyandu()
        getkader()




        //datepicker
        val calendar = Calendar.getInstance()
        val year = calendar[Calendar.YEAR]
        val month = calendar[Calendar.MONTH]
        val day = calendar[Calendar.DAY_OF_MONTH]


        //datepickershow
        binding.jadwalposyanduTanggalposyandu.setOnClickListener {
            val datePickerDialog = DatePickerDialog(
                this, { view, year, month, day ->
                    var month = month
                    month = month + 1
//                    val urutan = "$day-$month-$year-$month-$day"
                    val urutan = "$year-$month-$day"
                    binding.jadwalposyanduTanggalposyandu.setText(urutan)
                }, year, month, day
            )
            datePickerDialog.show()
        }
        //timepickershow
        binding.jadwalposyanduWaktukegiatan.setOnClickListener {
//           val mcurrentTime = Calendar.getInstance()
//           val hour = mcurrentTime[Calendar.HOUR_OF_DAY]
//           val minute = mcurrentTime[Calendar.MINUTE]
//           val jam =  if (hour < 10) "0" + hour else hour
//           val menit =  if (minute < 10) "0" + minute else minute
//
//           val mTimePicker: TimePickerDialog
//           mTimePicker = TimePickerDialog(this,
//               { timePicker, selectedHour, selectedMinute
//                   -> binding.jadwalposyanduWaktukegiatan.setText("" + checkDigit(selectedHour) + ":" + checkDigit(selectedMinute)) },
//               jam as Int,
//               menit as Int,
//               true
//           )
//           mTimePicker.show()
            val calendar = Calendar.getInstance()

            timePickerDialog = TimePickerDialog(
                this,
                { view: TimePicker?, hourOfDay: Int, minute: Int ->
                    binding.jadwalposyanduWaktukegiatan.setText(
                        "$hourOfDay:$minute:00"
                    )
                },
                calendar[Calendar.HOUR_OF_DAY], calendar[Calendar.MINUTE],
                DateFormat.is24HourFormat(this)
            )

            timePickerDialog.show()
       }
        //intentback
        binding.backToberandakader.setOnClickListener {
            startActivity(Intent(this, BerandakaderActivity::class.java))
        }
        binding.jadwalposyanduNamaposyandu.onItemClickListener =
            OnItemClickListener { arg0, arg1, position, arg3 ->
                id_posyandu = listPosyandu.get(position).id!!
                Log.d("your selected item", "" + id_posyandu)
            }

        binding.btnSimpanjadwalposyandu.setOnClickListener {

            try {
//
                if (binding.jadwalposyanduKeterangan.text.toString().isEmpty() ||
                    binding.jadwalposyanduNamaposyandu.text.trim()
                        .isEmpty() || binding.jadwalposyanduTanggalposyandu.text.trim().isEmpty()||binding.jadwalposyanduWaktukegiatan.text.trim().isEmpty()
                ) {

                    pDialog = SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("Isi Semua Form")
                        .setConfirmClickListener {
                            hideDialog()
                        }
                    showDialog()
                } else {
                    simpanJadwalPosyandu()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }


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
                        listPosyandu = data
                        val adapter = ArrayAdapter(applicationContext, android.R.layout.simple_list_item_1, listPosyandu)

                        binding.jadwalposyanduNamaposyandu.setAdapter(adapter)
                    }
                }

                override fun onFailure(call: Call<ListPosyandu>, t: Throwable) {

                }

            })
    }

    private fun simpanJadwalPosyandu() {
        ApiService.endpoint.postJadwalPosyandu(
            binding.jadwalposyanduTanggalposyandu.text.toString(),
            binding.jadwalposyanduWaktukegiatan.text.toString(),
            id_kader.toString(),
            id_posyandu.toString(),
            binding.jadwalposyanduKeterangan.text.toString(),

            ).enqueue(object : Callback<ResponsePesan> {
            override fun onResponse(
                call: Call<ResponsePesan>,
                response: Response<ResponsePesan>
            ) {
                val status = response.body()?.status
                val pesanan = response.body()?.pesan
                if (status!!) {
                    Toast.makeText(this@JadwalPosyanduActivity, pesanan, Toast.LENGTH_LONG).show()
//                    this@KelolajadwalimunisasiActivity.fragmentManager?.popBackStack()
                    finish()
                } else {
                    Toast.makeText(this@JadwalPosyanduActivity, pesanan, Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<ResponsePesan>, t: Throwable) {
                t.printStackTrace()
                Toast.makeText(this@JadwalPosyanduActivity, "Kesalahan tidak terduga!", Toast.LENGTH_LONG).show()

            }

        })

    }

//    override fun onPostResume() {
//        super.onPostResume()
//        val pos = resources.getStringArray(R.array.nama_posyandu)
//        val arrayAdapter = ArrayAdapter(this, R.layout.dropdown_listanak,pos)
//        binding.jadwalposyanduNamaposyandu.setAdapter(arrayAdapter)
//    }

//    fun checkDigit(number: Int): String? {
//        return if (number <= 9) "0$number" else number.toString()
//    }
private fun getkader() {
    val user = s.getUser()!!
    Log.d("idne mase",user.id.toString())

    ApiService.endpoint.getkader(user.id)
        .enqueue(object : Callback<Kader> {
            override fun onResponse(
                call: Call<Kader>,
                response: Response<Kader>
            ) {
                val status = response.body()?.status
                val data = response.body()?.data
                Log.d("responseData", data.toString())
                if (status == "success" && data != null) {
                    // set adapter and layout manager for rv
                    Kader = data
                    id_kader = Kader.id!!
                    Log.d("responseData8", Kader.id.toString())

                }
            }

            override fun onFailure(call: Call<Kader>, t: Throwable) {

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