package com.example.posyanduapp.ui.kader

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.app.TimePickerDialog.OnTimeSetListener
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.TimePicker
import com.example.posyanduapp.R
import com.example.posyanduapp.databinding.ActivityJadwalPosyanduBinding
import java.util.*

class JadwalPosyanduActivity : AppCompatActivity() {


    private lateinit var binding: ActivityJadwalPosyanduBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJadwalPosyanduBinding.inflate(layoutInflater)
        setContentView(binding.root)

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
                    val urutan = "$day - $month - $year"
                    binding.jadwalposyanduTanggalposyandu.setText(urutan)
                }, year, month, day
            )
            datePickerDialog.show()
        }
        //timepickershow
        binding.jadwalposyanduWaktukegiatan.setOnClickListener {
           val mcurrentTime = Calendar.getInstance()
           val hour = mcurrentTime[Calendar.HOUR_OF_DAY]
           val minute = mcurrentTime[Calendar.MINUTE]
           val jam =  if (hour < 10) "0" + hour else hour
           val menit =  if (minute < 10) "0" + minute else minute

           val mTimePicker: TimePickerDialog
           mTimePicker = TimePickerDialog(this,
               { timePicker, selectedHour, selectedMinute
                   -> binding.jadwalposyanduWaktukegiatan.setText("" + checkDigit(selectedHour) + ":" + checkDigit(selectedMinute)) },
               jam as Int,
               menit as Int,
               true
           )
           mTimePicker.show()
       }
        //intentback
        binding.backToberandakader.setOnClickListener {
            startActivity(Intent(this, RiwayatJadwalPosyanduActivity::class.java))
        }


    }

    override fun onPostResume() {
        super.onPostResume()
        val pos = resources.getStringArray(R.array.nama_posyandu)
        val arrayAdapter = ArrayAdapter(this, R.layout.dropdown_listanak,pos)
        binding.jadwalposyanduNamaposyandu.setAdapter(arrayAdapter)
    }

    fun checkDigit(number: Int): String? {
        return if (number <= 9) "0$number" else number.toString()
    }


}