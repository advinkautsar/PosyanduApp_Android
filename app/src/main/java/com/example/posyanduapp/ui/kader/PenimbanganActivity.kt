package com.example.posyanduapp.ui.kader

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import cn.pedant.SweetAlert.SweetAlertDialog
import com.example.posyanduapp.R
import com.example.posyanduapp.databinding.ActivityKartuAnakBinding
import com.example.posyanduapp.databinding.ActivityPenimbanganBinding
import com.example.posyanduapp.model.ListAnak
import com.example.posyanduapp.model.ResponsePesan
import com.example.posyanduapp.retrofit.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.math.BigInteger
import java.util.ArrayList

class PenimbanganActivity : AppCompatActivity() {
    var bb:String=""
    var lk:String=""
    var tb:String=""
    private lateinit var binding: ActivityPenimbanganBinding
    lateinit var pDialog: SweetAlertDialog
    var listAnak: ArrayList<ListAnak.Result> = ArrayList()
    lateinit var nik_anak: BigInteger
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPenimbanganBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        setContentView(R.layout.activity_penimbangan)


        bb = intent.getStringExtra("bb").toString()
        lk = intent.getStringExtra("lk").toString()
        tb = intent.getStringExtra("tb").toString()

        binding.bb.setText(bb)
        binding.tb.setText(tb)
        binding.lk.setText(lk)




        getlistanak()
        binding.kartuanakNamaanak.onItemClickListener =
            AdapterView.OnItemClickListener { arg0, arg1, position, arg3 ->
                nik_anak = listAnak.get(position).nik_anak

                Log.d("your selected item", "" + nik_anak)

                binding.nikAnak.setText(nik_anak.toString())
            }


        binding.backToberandakader.setOnClickListener {
            startActivity(Intent(this, BerandakaderActivity::class.java))
        }
        binding.btnSimpankartuanak.setOnClickListener {


                    UpdateDataKartuAnak()

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

                        binding.kartuanakNamaanak.setAdapter(adapter)

                    }
                }

                override fun onFailure(call: Call<ListAnak>, t: Throwable) {

                }

            })
    }
    private fun UpdateDataKartuAnak() {


        ApiService.endpoint.postpenimbangan(
            nik_anak.toString(),
            binding.bb.text.toString(),
            binding.tb.text.toString(),
            binding.lk.text.toString(),
        ).enqueue(object : Callback<ResponsePesan> {
            override fun onResponse(
                call: Call<ResponsePesan>,
                response: Response<ResponsePesan>
            ) {
                val status = response.body()?.status
                val pesanan = response.body()?.pesan

                if (status!!) {
                    Toast.makeText(this@PenimbanganActivity, pesanan, Toast.LENGTH_LONG).show()
                    nextbutton()
                } else {
                    Toast.makeText(this@PenimbanganActivity, pesanan, Toast.LENGTH_LONG).show()
                }
                Log.d("isi", pesanan.toString())
            }

            override fun onFailure(call: Call<ResponsePesan>, t: Throwable) {
                t.printStackTrace()
                Toast.makeText(this@PenimbanganActivity, "Kesalahan tidak terduga!", Toast.LENGTH_LONG).show()

            }

        })
    }
    private fun showDialog() {
        if (!pDialog.isShowing) pDialog.show()
    }

    private fun hideDialog() {
        if (pDialog.isShowing) pDialog.dismiss()
    }
    private fun nextbutton() {
        val intent = Intent(this, BerandakaderActivity::class.java)
        startActivity(intent)
    }
}