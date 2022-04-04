package com.example.posyanduapp.ui.bidan

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import cn.pedant.SweetAlert.SweetAlertDialog
import com.example.posyanduapp.Helper.SharedPref
import com.example.posyanduapp.databinding.ActivityPemeriksaanKesehatanUpdateBinding
import com.example.posyanduapp.model.*
import com.example.posyanduapp.retrofit.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.collections.ArrayList


class PemeriksaanKesehatanUpdateActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPemeriksaanKesehatanUpdateBinding
    lateinit var Bidan: Bidan.Result
    var id_bidan :Int = 0
    private lateinit var s: SharedPref
    lateinit var vitAmerahs :String
    lateinit var vitABirus :String
    lateinit var oralits:String
    lateinit var fe1s :String
    lateinit var fe2s :String
    lateinit var pmts :String
    lateinit var asis :String
    lateinit var obatcacings :String
    lateinit var pDialog: SweetAlertDialog
    lateinit var PemkesTunggal:GetPemeriksaan.Result
    lateinit var PemkesTunggal2 :GetPemeriksaanTunggal.Result
    var id_imun :Int = 0
    var id_imun2 :Int = 0
    var id_imun3 :Int = 0
    var nik_anak: String=""
    var idnya: String = ""
    var listAnak: ArrayList<ListAnak.Result> = ArrayList()
    var listImunisasi: ArrayList<ListImunisasi.Result> = ArrayList()
    var listImunisasi2: ArrayList<ListImunisasi.Result> = ArrayList()
    var listImunisasi3: ArrayList<ListImunisasi.Result> = ArrayList()

    var listAsi: ArrayList<StatusPilihan.Result> = ArrayList()
    var listoralit: ArrayList<StatusPilihan.Result> = ArrayList()
    var listobat: ArrayList<StatusPilihan.Result> = ArrayList()
    var listfe1: ArrayList<StatusPilihan.Result> = ArrayList()
    var listfe2: ArrayList<StatusPilihan.Result> = ArrayList()
    var listpmt: ArrayList<StatusPilihan.Result> = ArrayList()
    var listvitmerah: ArrayList<StatusPilihan.Result> = ArrayList()
    var listvitbiru: ArrayList<StatusPilihan.Result> = ArrayList()

//    var adapterasi: ArrayAdapter<String>? = null
//    var str = arrayOf("Pilih ", "Ya", "Tidak")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_pemeriksaan_kesehatan_update)
        binding = ActivityPemeriksaanKesehatanUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        adapterasi = ArrayAdapter(this, android.R.layout.simple_list_item_1)
//        adapterasi= ArrayAdapter(applicationContext, android.R.layout.simple_list_item_1, str)
//        adapterasi!!.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
//        binding.periksaAsi.setAdapter(adapterasi)


        idnya = intent.getStringExtra("idpemkes").toString()
        s = SharedPref(this)

        if (idnya!=null){
            getdatapemkes_id(idnya)
            Log.d("enek lo","ada"+idnya)
        }
        dropdown()
//        pindah()
        getlistImunisasi()
        getlistanak()
        getbidan()
        getStatus()

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

        binding.periksaNamaanak.onItemClickListener =
            AdapterView.OnItemClickListener { arg0, arg1, position, arg3 ->
                nik_anak = listAnak.get(position).nik_anak.toString()

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


    }
    fun dropdown (){

        binding.periksaVitAmerah.onItemClickListener =
            AdapterView.OnItemClickListener { arg0, arg1, position, arg3 ->
                vitAmerahs= listvitmerah.get(position).status!!


                Log.d("your selected item", "" + vitAmerahs)
            }


        binding.periksaVitAbiru.onItemClickListener =
            AdapterView.OnItemClickListener { arg0, arg1, position, arg3 ->
                vitABirus= listvitbiru.get(position).status!!


                Log.d("your selected item", "" + vitABirus)
            }


        binding.periksaOralit.onItemClickListener =
            AdapterView.OnItemClickListener { arg0, arg1, position, arg3 ->
                oralits= listoralit.get(position).status!!


                Log.d("your selected item", "" + oralits)
            }


        binding.periksaFe1.onItemClickListener =
            AdapterView.OnItemClickListener { arg0, arg1, position, arg3 ->
                fe1s= listfe1.get(position).status!!


                Log.d("your selected item", "" + fe1s)
            }


        binding.periksaFe2.onItemClickListener =
            AdapterView.OnItemClickListener { arg0, arg1, position, arg3 ->
                fe2s= listfe2.get(position).status!!


                Log.d("your selected item", "" + fe2s)
            }

        binding.periksaPMT.onItemClickListener =
            AdapterView.OnItemClickListener { arg0, arg1, position, arg3 ->
                pmts= listpmt.get(position).status!!


                Log.d("your selected item", "" +pmts)
            }


        binding.periksaAsi.onItemClickListener =
            AdapterView.OnItemClickListener { arg0, arg1, position, arg3 ->
                asis = listAsi.get(position).status!!


                Log.d("your selected item", "" + asis)
            }

        binding.periksaObatcacing.onItemClickListener =
            AdapterView.OnItemClickListener { arg0, arg1, position, arg3 ->
                obatcacings= listobat.get(position).status.toString()


                Log.d("your selected item", "" + obatcacings)
            }


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
//    fun pindah(){
//        binding.btnKembalipemeriksaankesehatan.setOnClickListener {
//            startActivity(Intent(this, RiwayatPemKesActivity::class.java))
//        }
//    }
    private fun getdatapemkes_id(idnya: String) {
        Toast.makeText(this@PemeriksaanKesehatanUpdateActivity, idnya, Toast.LENGTH_LONG).show()
        ApiService.endpoint.getPemeriksaanID(idnya)
            .enqueue(object : Callback<GetPemeriksaan> {
                override fun onResponse(
                    call: Call<GetPemeriksaan>,
                    response: Response<GetPemeriksaan>
                ) {
                    val status = response.body()?.status
                    val data = response.body()?.data

                    Log.d("responspemkses", data.toString())
//                    Toast.makeText(this@PemeriksaanKesehatanUpdateActivity, "isieopo:"+data.toString(), Toast.LENGTH_LONG).show()
                    if (status == "success" && data != null) {
                        // set adapter and layout manager for rv
                        PemkesTunggal = data
//                        id_bidan = Bidan.id!!
                        Log.d("responsepemeks", PemkesTunggal.toString())
                        nik_anak = PemkesTunggal.nik_anak.toString()
                        binding.periksaTanggalpemeriksaan.setText(PemkesTunggal.tanggal_pemeriksaan)
                        id_imun = PemkesTunggal.imunisasi_id_1!!
                        id_imun2 = PemkesTunggal.imunisasi_id_2!!
                        id_imun3 = PemkesTunggal.imunisasi_id_3!!
                        pmts = PemkesTunggal.PMT.toString()
                        fe1s = PemkesTunggal.Fe_1.toString()
                        fe2s = PemkesTunggal.Fe_2.toString()
                        asis = PemkesTunggal.asi_ekslusif.toString()
                        oralits= PemkesTunggal.oralit.toString()
                        obatcacings = PemkesTunggal.obat_cacing.toString()
                        vitABirus = PemkesTunggal.vitA_biru.toString()
                        vitAmerahs =PemkesTunggal.vitA_merah.toString()
                        binding.periksaAsi.setText(PemkesTunggal.asi_ekslusif)
////
////                        val a= adapter!!.getPosition(PemkesTunggal.asi_ekslusif)
////                        Log.d("adapter-a", a.toString())
////                        binding.periksaAsi.setSelection(a)
//
//
//                        binding.periksaAsi.onItemClickListener =
//                            AdapterView.OnItemClickListener { arg0, arg1, position, arg3 ->
//                                asis =str.get(position)
//
//
//                                Log.d("your selected item", "" + asis)
//                            }


                        binding.periksaVitAbiru.setText(PemkesTunggal.vitA_biru)
                        binding.periksaVitAmerah.setText(PemkesTunggal.vitA_merah)
                        binding.periksaOralit.setText(PemkesTunggal.oralit)
                        binding.periksaObatcacing.setText(PemkesTunggal.obat_cacing)
                        binding.periksaImunisasi1.setText(PemkesTunggal.imun1.toString())
                        binding.periksaImunisasi2.setText(PemkesTunggal.imun2.toString())
                        binding.periksaImunisasi3.setText(PemkesTunggal.imun3.toString())
                        Log.d("imun1", PemkesTunggal.imun1.toString())
                        Log.d("imun2", PemkesTunggal.imun2.toString())
                        Log.d("imun3", PemkesTunggal.imun3.toString())

                        binding.periksaNamaanak.setText(PemkesTunggal.nama_anak)
                        binding.periksaFe1.setText(PemkesTunggal.Fe_1)
                        binding.periksaFe2.setText(PemkesTunggal.Fe_2)
                        binding.periksaPMT.setText(PemkesTunggal.PMT)


                    }
                }

                override fun onFailure(call: Call<GetPemeriksaan>, t: Throwable) {

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
                        listImunisasi2 = data
                        listImunisasi3 = data
                        val adapter = ArrayAdapter(applicationContext, android.R.layout.simple_list_item_1, listImunisasi)
                        val adapter2 = ArrayAdapter(applicationContext, android.R.layout.simple_list_item_1, listImunisasi2)
                        val adapter3 = ArrayAdapter(applicationContext, android.R.layout.simple_list_item_1, listImunisasi3)

                        binding.periksaImunisasi1.setAdapter(adapter)
                        binding.periksaImunisasi2.setAdapter(adapter2)
                        binding.periksaImunisasi3.setAdapter(adapter3)
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

    private fun getStatus() {
        ApiService.endpoint.getstatus()
            .enqueue(object : Callback<StatusPilihan> {
                override fun onResponse(
                    call: Call<StatusPilihan>,
                    response: Response<StatusPilihan>
                ) {
                    val status = response.body()?.status
                    val data = response.body()?.data
                    Log.d("responseDataasi", data.toString())
                    if (status == "success" && data != null) {
                        // set adapter and layout manager for rv
                        listAsi = data
                        listoralit= data
                        listobat= data
                        listfe1=  data
                        listfe2= data
                        listvitmerah = data
                        listpmt   = data
                        listvitbiru = data

                        val adapterasi= ArrayAdapter(applicationContext, android.R.layout.simple_list_item_1, listAsi)

                        val adapteroralit = ArrayAdapter(applicationContext, android.R.layout.simple_list_item_1, listoralit)
                        val adapterobat = ArrayAdapter(applicationContext, android.R.layout.simple_list_item_1, listobat)
                        val adapterfe1 = ArrayAdapter(applicationContext, android.R.layout.simple_list_item_1, listfe1)
                        val adapterfe2 = ArrayAdapter(applicationContext, android.R.layout.simple_list_item_1, listfe2)
                        val adapterpmt = ArrayAdapter(applicationContext, android.R.layout.simple_list_item_1, listpmt)
                        val adaptervitmerah = ArrayAdapter(applicationContext, android.R.layout.simple_list_item_1, listvitmerah)
                        val adaptervitbiru = ArrayAdapter(applicationContext, android.R.layout.simple_list_item_1, listvitbiru)


                        binding.periksaAsi.setAdapter(adapterasi)
                        binding.periksaOralit.setAdapter(adapteroralit)
                        binding.periksaObatcacing.setAdapter(adapterobat)
                        binding.periksaFe1.setAdapter(adapterfe1)
                        binding.periksaFe2.setAdapter(adapterfe2)
                        binding.periksaPMT.setAdapter(adapterpmt)
                        binding.periksaVitAbiru.setAdapter(adaptervitbiru)
                        binding.periksaVitAmerah.setAdapter(adaptervitmerah)
                    }
                }

                override fun onFailure(call: Call<StatusPilihan>, t: Throwable) {

                }

            })
    }


    private fun PostPemeriksaanKesehatan() {


        ApiService.endpoint.updatepemeriksaankesehatan(
            idnya,
            id_bidan.toString(),
            nik_anak.toString(),
            id_imun.toString(), id_imun2.toString(), id_imun3.toString(),
            vitAmerahs,vitABirus,
            fe1s,fe2s,
            pmts,
            asis,
            oralits,
            obatcacings,
            binding.periksaTanggalpemeriksaan.text.toString()
            ).enqueue(object : Callback<ResponsePesan> {
            override fun onResponse(
                call: Call<ResponsePesan>,
                response: Response<ResponsePesan>
            ) {
                val status = response.body()?.status
                val pesanan = response.body()?.pesan

                if (status!!) {
                    Toast.makeText(this@PemeriksaanKesehatanUpdateActivity, pesanan, Toast.LENGTH_LONG).show()
//                    this@KelolajadwalimunisasiActivity.fragmentManager?.popBackStack()
//                    val intent = Intent(this@PemeriksaanKesehatanUpdateActivity, RiwayatPemKesActivity::class.java)


                    listAsi.clear()
                    listImunisasi.clear()
                    listImunisasi2.clear()
                    listImunisasi3.clear()
                    listfe1.clear()
                    listfe2.clear()
                    listpmt.clear()
                    listvitbiru.clear()
                    listvitmerah.clear()
                    listobat.clear()
                    listoralit.clear()
//                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this@PemeriksaanKesehatanUpdateActivity, pesanan, Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<ResponsePesan>, t: Throwable) {
                t.printStackTrace()
                Toast.makeText(this@PemeriksaanKesehatanUpdateActivity, "Kesalahan tidak terduga!", Toast.LENGTH_LONG).show()

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