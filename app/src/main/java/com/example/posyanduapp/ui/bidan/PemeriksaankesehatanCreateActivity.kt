package com.example.posyanduapp.ui.bidan

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
import com.example.posyanduapp.R
import com.example.posyanduapp.databinding.ActivityPemeriksaankesehatanBinding
import com.example.posyanduapp.model.Bidan
import com.example.posyanduapp.model.ListAnak
import com.example.posyanduapp.model.ListImunisasi
import com.example.posyanduapp.model.ResponsePesan
import com.example.posyanduapp.retrofit.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.math.BigInteger
import java.util.*

class PemeriksaankesehatanCreateActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPemeriksaankesehatanBinding
    lateinit var Bidan: Bidan.Result
    var id_bidan :Int = 0
    lateinit var vitAmerahs :String
    lateinit var vitABirus :String
    lateinit var oralits:String
    lateinit var fe1s :String
    lateinit var fe2s :String
    lateinit var pmts :String
    lateinit var asis :String
    lateinit var obatcacings :String
    lateinit var pDialog: SweetAlertDialog
    var id_imun :Int = 0
    var id_imun2 :Int = 0
    var id_imun3 :Int = 0
    lateinit var nik_anak: BigInteger
    private lateinit var s: SharedPref
    var listAnak: ArrayList<ListAnak.Result> = ArrayList()
    var listImunisasi: ArrayList<ListImunisasi.Result> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPemeriksaankesehatanBinding.inflate(layoutInflater)
        setContentView(binding.root)
        s = SharedPref(this)

        dropdown()
        pindah()
        getlistImunisasi()
        getlistanak()
        getbidan()


        binding.periksaNamaanak.onItemClickListener =
            AdapterView.OnItemClickListener { arg0, arg1, position, arg3 ->
                nik_anak = listAnak.get(position).nik_anak

                Log.d("your selected item", "" + nik_anak)
            }

        binding.periksaImunisasi1.onItemClickListener =
            AdapterView.OnItemClickListener { arg0, arg1, position, arg3 ->
                id_imun = listImunisasi.get(position).id!!

                Log.d("your selected item21", "" + id_imun)
            }


        binding.periksaImunisasi2.onItemClickListener =
            AdapterView.OnItemClickListener { arg0, arg1, position, arg3 ->
                id_imun2 = listImunisasi.get(position).id!!

                Log.d("your selected item22", "" + id_imun2)
            }

        binding.periksaImunisasi3.onItemClickListener =
            AdapterView.OnItemClickListener { arg0, arg1, position, arg3 ->
                id_imun3 = listImunisasi.get(position).id!!

                Log.d("your selected item23", "" + id_imun3)
            }

        //datepicker show
        //datepicker
        val calendar = Calendar.getInstance()
        val year = calendar[Calendar.YEAR]
        val month = calendar[Calendar.MONTH]
        val day = calendar[Calendar.DAY_OF_MONTH]

        binding.periksaTanggalpemeriksaan.setOnClickListener {
            val datePickerDialog = DatePickerDialog(
                this, { view, year, month, day ->
                    var month = month
                    month = month + 1
                    val urutan = "$year-$month-$day"
                    binding.periksaTanggalpemeriksaan.setText(urutan)
                }, year, month, day
            )
            datePickerDialog.show()
        }


    }
    fun dropdown (){
//
        //dropdown vitA merah
        val vit1 = resources.getStringArray(R.array.obat)
        val adaptervit1 = ArrayAdapter(
            this,
            R.layout.dropdown_listanak,
            vit1
        )
        with(binding.periksaVitAmerah){
            setAdapter(adaptervit1)

//            Log.d("your selected item", "" +vit1)
        }
        binding.periksaVitAmerah.onItemClickListener =
            AdapterView.OnItemClickListener { arg0, arg1, position, arg3 ->
              vitAmerahs=  vit1.get(position)


                Log.d("your selected item", "" + vitAmerahs)
            }

        //dropdown vitA biru
        val vit2 = resources.getStringArray(R.array.obat)
        val adaptervit2 = ArrayAdapter(
            this,
            R.layout.dropdown_listanak,
            vit2
        )
        with(binding.periksaVitAbiru){
            setAdapter(adaptervit2)
        }
        binding.periksaVitAbiru.onItemClickListener =
            AdapterView.OnItemClickListener { arg0, arg1, position, arg3 ->
                vitABirus=  vit2.get(position)


                Log.d("your selected item", "" + vitABirus)
            }

        //oralit
        val oralit = resources.getStringArray(R.array.obat)
        val adapteroralit = ArrayAdapter(
            this,
            R.layout.dropdown_listanak,
            oralit
        )
        with(binding.periksaOralit){
            setAdapter(adapteroralit)
        }
        binding.periksaOralit.onItemClickListener =
            AdapterView.OnItemClickListener { arg0, arg1, position, arg3 ->
               oralits=  oralit.get(position)


                Log.d("your selected item", "" + oralits)
            }

        //Fe1
        val fe1 = resources.getStringArray(R.array.obat)
        val adapterfe1 = ArrayAdapter(
            this,
            R.layout.dropdown_listanak,
            fe1
        )
        with(binding.periksaFe1){
            setAdapter(adapterfe1)
        }
        binding.periksaFe1.onItemClickListener =
            AdapterView.OnItemClickListener { arg0, arg1, position, arg3 ->
                fe1s=  fe1.get(position)


                Log.d("your selected item", "" + fe1s)
            }

        //Fe2
        val fe2 = resources.getStringArray(R.array.obat)
        val adapterfe2 = ArrayAdapter(
            this,
            R.layout.dropdown_listanak,
            fe2
        )
        with(binding.periksaFe2){
            setAdapter(adapterfe2)
        }
        binding.periksaFe2.onItemClickListener =
            AdapterView.OnItemClickListener { arg0, arg1, position, arg3 ->
                fe2s=  fe2.get(position)


                Log.d("your selected item", "" + fe2s)
            }

        //PMT
        val pmt = resources.getStringArray(R.array.obat)
        val adapterpmt = ArrayAdapter(
            this,
            R.layout.dropdown_listanak,
            pmt
        )
        with(binding.periksaPMT){
            setAdapter(adapterpmt)
        }
        binding.periksaPMT.onItemClickListener =
            AdapterView.OnItemClickListener { arg0, arg1, position, arg3 ->
                pmts=  pmt.get(position)


                Log.d("your selected item", "" +pmts)
            }

        //asi
        val asi = resources.getStringArray(R.array.obat)
        val adapterasi = ArrayAdapter(
            this,
            R.layout.dropdown_listanak,
            asi
        )
        with(binding.periksaAsi){
            setAdapter(adapterasi)
        }
        binding.periksaAsi.onItemClickListener =
            AdapterView.OnItemClickListener { arg0, arg1, position, arg3 ->
              asis =asi.get(position)


                Log.d("your selected item", "" + asis)
            }

        //obat cacing
        val obatcacing = resources.getStringArray(R.array.obat)
        val adaptercacing = ArrayAdapter(
            this,
            R.layout.dropdown_listanak,
            obatcacing
        )
        with(binding.periksaObatcacing){
            setAdapter(adaptercacing)
        }

        binding.periksaObatcacing.onItemClickListener =
            AdapterView.OnItemClickListener { arg0, arg1, position, arg3 ->
                obatcacings=  obatcacing.get(position)


                Log.d("your selected item", "" + obatcacings)
            }

        //nama bidan
//        val namabidan = resources.getStringArray(R.array.nama_bidan)
//        val adapterbidan = ArrayAdapter(
//            this,
//            R.layout.dropdown_listanak,
//            namabidan
//        )
//        with(binding.periksaNamabidan){
//            setAdapter(adapterbidan)
//        }

        binding.btnSimpanperiksa.setOnClickListener {

            try {
//
                if (binding.periksaAsi.text.toString().isEmpty()||binding.periksaFe1.text.toString().isEmpty()||
                    binding.periksaFe2.text.toString().isEmpty()||binding.periksaOralit.text.toString().isEmpty()||binding.periksaObatcacing.text.toString().isEmpty()||
                    binding.periksaImunisasi1.text.toString().isEmpty()||binding.periksaImunisasi2.text.toString().isEmpty()||binding.periksaImunisasi3.text.toString().isEmpty()||
                    binding.periksaNamaanak.text.toString().isEmpty()||binding.periksaPMT.text.toString().isEmpty()||
                    binding.periksaVitAbiru.text.toString().isEmpty()|| binding.periksaVitAmerah.text.toString().isEmpty() || binding.periksaTanggalpemeriksaan.text.trim().isEmpty()
                ) {

                    pDialog = SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("Isi Semua Form")
                        .setConfirmClickListener {
                            hideDialog()
                        }
                    showDialog()
                } else {
                    PostPemeriksaanKesehatan()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
    }
    fun pindah(){
        binding.btnKembalipemeriksaankesehatan.setOnClickListener {
            startActivity(Intent(this, KesehatananakActivity::class.java))
        }
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

                        binding.periksaImunisasi1.setAdapter(adapter)
                        binding.periksaImunisasi2.setAdapter(adapter)
                        binding.periksaImunisasi3.setAdapter(adapter)
                    }
                }

                override fun onFailure(call: Call<ListImunisasi>, t: Throwable) {

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

                        binding.periksaNamaanak.setAdapter(adapter)
                    }
                }

                override fun onFailure(call: Call<ListAnak>, t: Throwable) {

                }

            })
    }

    private fun PostPemeriksaanKesehatan() {
        ApiService.endpoint.postpemeriksaankesehatan(
           id_bidan.toString(), nik_anak.toString(),
            id_imun.toString(), id_imun2.toString(), id_imun3.toString(), vitAmerahs,vitABirus,
            fe1s,fe2s, pmts,
            asis,oralits, obatcacings,
            binding.periksaTanggalpemeriksaan.text.toString(),
        ).enqueue(object : Callback<ResponsePesan> {
            override fun onResponse(
                call: Call<ResponsePesan>,
                response: Response<ResponsePesan>
            ) {
                val status = response.body()?.status
                val pesanan = response.body()?.pesan

                if (status!!) {
                    Toast.makeText(this@PemeriksaankesehatanCreateActivity, pesanan, Toast.LENGTH_LONG).show()
//                    this@KelolajadwalimunisasiActivity.fragmentManager?.popBackStack()
                    finish()
                } else {
                    Toast.makeText(this@PemeriksaankesehatanCreateActivity, pesanan, Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<ResponsePesan>, t: Throwable) {
                t.printStackTrace()
                Toast.makeText(this@PemeriksaankesehatanCreateActivity, "Kesalahan tidak terduga!", Toast.LENGTH_LONG).show()

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