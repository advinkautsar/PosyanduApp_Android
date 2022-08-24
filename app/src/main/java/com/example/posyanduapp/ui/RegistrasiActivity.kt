package com.example.posyanduapp.ui

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Toast
import cn.pedant.SweetAlert.SweetAlertDialog
import com.example.posyanduapp.Helper.SharedPref
import com.example.posyanduapp.Model.ListKecamatan
import com.example.posyanduapp.Model.ListKelurahanDesa
import com.example.posyanduapp.Model.ResponRegister
import com.example.posyanduapp.R
import com.example.posyanduapp.databinding.ActivityRegistrasiBinding
import com.example.posyanduapp.model.ListPosyandu
import com.example.posyanduapp.retrofit.ApiService
import com.example.posyanduapp.ui.orangtua.BerandaOrtuActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.collections.ArrayList

class RegistrasiActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegistrasiBinding
    private lateinit var s: SharedPref
    var token: String = ""

    lateinit var jenis_kelamin:String


    var id_kecamatan: Int = 0
    var ListKecamatan: ArrayList<ListKecamatan.Result> = ArrayList()
    var id_desa_kelurahan: Int = 0
    var ListDesaKelurahan: ArrayList<ListKelurahanDesa.Result> = ArrayList()
    var listPosyandu: ArrayList<ListPosyandu.Result> = ArrayList()
    var id_posyandu: Int = 0

    lateinit var pDialog: SweetAlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrasiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        s = SharedPref(this)
        dropdown_JK()
        getdesakelurahan()
        getkecamatan()
        getlisPosyandu()


        FirebaseMessaging.getInstance().token
            .addOnCompleteListener(OnCompleteListener { task ->
                if (!task.isSuccessful) {
                    Log.w("Gagal", "getInstanceId failed", task.exception)
                    return@OnCompleteListener
                }

                // Get new Instance ID token
                token = task.result.toString()

                Log.d("Token Firebase", token)
//                Toast.makeText(this, token, Toast.LENGTH_SHORT).show()
            })

        //intent
        binding.backDaftartocari.setOnClickListener {
            startActivity(Intent(this,LoginActivity::class.java))
        }
        val btn_daftar = findViewById(R.id.btn_pendaftaran) as Button
        btn_daftar.setOnClickListener {
            try {
//
                if (binding.daftarNamapengguna.text.toString().isEmpty()||
                    binding.daftarKatasandi.text.toString().isEmpty()||
                    binding.daftarNohp.text.toString().isEmpty()||

                    binding.daftarNIKayah.text.toString().isEmpty()||
                    binding.daftarNamaayah.text!!.trim().isEmpty() ||
                    binding.daftarNIKibu.text!!.trim().isEmpty() ||
                    binding.daftarNamaibu.text!!.trim().isEmpty() ||
                    binding.daftarAlamatrumah.text!!.trim().isEmpty() ||
                    binding.daftarRt.text!!.trim().isEmpty() ||
                    binding.daftarRw.text!!.trim().isEmpty()  ||
                    binding.daftarKecamatan.text!!.trim().isEmpty()  ||
                    binding.daftarDesaKelurahan.text!!.trim().isEmpty()  ||
                    binding.daftarPosyandu.text!!.trim().isEmpty()  ||

                    binding.daftarNamaAnak.text!!.trim().isEmpty()  ||
                    binding.daftarNikAnak.text!!.trim().isEmpty()  ||
                    binding.daftarTanggallahirAnak.text!!.trim().isEmpty()  ||
                    binding.daftarBeratlahirAnak.text!!.trim().isEmpty()  ||
                    binding.daftarPanjanglahirAnak.text!!.trim().isEmpty()  ||
                    binding.daftarJeniskelaminAnak.text!!.trim().isEmpty()

                ) {

                    pDialog = SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("Isi Semua Form")
                        .setConfirmClickListener {
                            hideDialog()
                        }
                    showDialog()
                } else {
                    registrasi()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        //Dropdown
        binding.daftarKecamatan.onItemClickListener =
            AdapterView.OnItemClickListener { arg0, arg1, position, arg3 ->
                id_kecamatan = ListKecamatan.get(position).id!!

                Log.d("your selected item", "" + id_kecamatan)
            }

        binding.daftarDesaKelurahan.onItemClickListener =
            AdapterView.OnItemClickListener { arg0, arg1, position, arg3 ->
                id_desa_kelurahan = ListDesaKelurahan.get(position).id!!

                Log.d("your selected item", "" + id_desa_kelurahan)
            }

        binding.daftarPosyandu.onItemClickListener =
            AdapterView.OnItemClickListener { arg0, arg1, position, arg3 ->
                id_posyandu = listPosyandu.get(position).id!!
                Log.d("your selected item", "" + id_posyandu)
            }


        //datepicker show
        val calendar = Calendar.getInstance()
        val year = calendar[Calendar.YEAR]
        val month = calendar[Calendar.MONTH]
        val day = calendar[Calendar.DAY_OF_MONTH]

        binding.daftarTanggallahirAnak.setOnClickListener {
            val datePickerDialog = DatePickerDialog(
                this, { view, year, month, day ->
                    var month = month
                    month = month + 1
                    val urutan = "$year-$month-$day"
                    binding.daftarTanggallahirAnak.setText(urutan)
                }, year, month, day
            )
            datePickerDialog.show()
        }

    }

    private fun registrasi() {
        ApiService.endpoint.registrasi(
            binding.daftarNamapengguna.text.toString(),binding.daftarKatasandi.text.toString(),
            binding.daftarNohp.text.toString(), token,
            binding.daftarNIKayah.text.toString(),binding.daftarNamaayah.text.toString(),
            binding.daftarNIKibu.text.toString(),binding.daftarNamaibu.text.toString(),
            binding.daftarAlamatrumah.text.toString(),binding.daftarRt.text.toString(),binding.daftarRw.text.toString(),
            id_kecamatan,id_desa_kelurahan,id_posyandu,
            binding.daftarNikAnak.text.toString(),binding.daftarNamaAnak.text.toString(),
            jenis_kelamin, binding.daftarTanggallahirAnak.text.toString(),
            binding.daftarBeratlahirAnak.text.toString(),binding.daftarPanjanglahirAnak.text.toString()
        ).enqueue(object : Callback<ResponRegister> {
            override fun onResponse(call: Call<ResponRegister>, response: Response<ResponRegister>) {
                val respon = response.body()!!

                if (respon.succes == 1){
                    s.setStatusLogin(true)
                    s.setUser(respon.user)
                    val intent = Intent(this@RegistrasiActivity, BerandaOrtuActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)
                    finish()
                    Toast.makeText(this@RegistrasiActivity, "Selamat Datang:" +respon.user.nama_pengguna, Toast.LENGTH_SHORT).show()
                } else{
                    Toast.makeText(this@RegistrasiActivity, "Pendaftaran Gagal:"+respon.message, Toast.LENGTH_SHORT).show()
                }

            }

            override fun onFailure(call: Call<ResponRegister>, t: Throwable) {
                //response ketika gagal
                Toast.makeText(this@RegistrasiActivity, "Error" + t.message, Toast.LENGTH_SHORT).show()
            }

        })
    }

    private fun getdesakelurahan() {
        ApiService.endpoint.getKelurahan_Desa()
            .enqueue(object : Callback<ListKelurahanDesa> {
                override fun onResponse(
                    call: Call<ListKelurahanDesa>,
                    response: Response<ListKelurahanDesa>
                ) {
                    val status = response.body()?.status
                    val data = response.body()?.data
                    Log.d("responseListDesa", data.toString())
                    if (status == "success" && data != null) {
                        ListDesaKelurahan = data

                        val adapter = ArrayAdapter(
                            applicationContext,
                            android.R.layout.simple_list_item_1,
                            ListDesaKelurahan
                        )
                        binding.daftarDesaKelurahan.setAdapter(adapter)
                    }
                }

                override fun onFailure(call: Call<ListKelurahanDesa>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
    }

    private fun getkecamatan() {
        ApiService.endpoint.getKecamatan()
            .enqueue(object : Callback<ListKecamatan> {
                override fun onResponse(
                    call: Call<ListKecamatan>,
                    response: Response<ListKecamatan>
                ) {
                    val status = response.body()?.status
                    val data = response.body()?.data
                    Log.d("responseListKec", data.toString())
                    if (status == "success" && data != null) {
                        ListKecamatan = data
                        val adapter = ArrayAdapter(
                            applicationContext,
                            android.R.layout.simple_list_item_1,
                            ListKecamatan
                        )
                        binding.daftarKecamatan.setAdapter(adapter)
                    }
                }

                override fun onFailure(call: Call<ListKecamatan>, t: Throwable) {
                    TODO("Not yet implemented")
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
                        listPosyandu = data
                        val adapter = ArrayAdapter(
                            applicationContext,
                            android.R.layout.simple_list_item_1,
                            listPosyandu
                        )

                        binding.daftarPosyandu.setAdapter(adapter)
                    }
                }

                override fun onFailure(call: Call<ListPosyandu>, t: Throwable) {

                }

            })
    }

    private fun dropdown_JK() {
        val jenis_kelamin1 = resources.getStringArray(R.array.jenis_kelamin)
        val adapterJK = ArrayAdapter(
            this,
            R.layout.dropdown_listanak,
            jenis_kelamin1
        )
        with(binding.daftarJeniskelaminAnak) {
            setAdapter(adapterJK)
        }
        binding.daftarJeniskelaminAnak.onItemClickListener =
            AdapterView.OnItemClickListener { arg0, arg1, position, arg3 ->
                jenis_kelamin=  jenis_kelamin1.get(position)


                Log.d("your selected item", "" + jenis_kelamin)
            }


    }

    private fun showDialog() {
        if (!pDialog.isShowing) pDialog.show()
    }

    private fun hideDialog() {
        if (pDialog.isShowing) pDialog.dismiss()
    }

}