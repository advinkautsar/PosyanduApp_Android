package com.example.posyanduapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import com.example.posyanduapp.AppConfig.ApiConfig
import com.example.posyanduapp.Helper.SharedPref
import com.example.posyanduapp.Model.ResponRegister
import com.example.posyanduapp.R
import com.example.posyanduapp.ui.orangtua.BerandaOrtuActivity
import kotlinx.android.synthetic.main.activity_pendaftaran.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PendaftaranActivity : AppCompatActivity() {

    private lateinit var s: SharedPref

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pendaftaran)

        mainbutton()
        s = SharedPref(this)



    }

    fun mainbutton() {
        val btn_daftar = findViewById(R.id.btn_pendaftaran) as Button
        btn_daftar.setOnClickListener {
            register()
        }
        val btn_backtopencarian = findViewById(R.id.back_daftartocari) as ImageView
        btn_backtopencarian.setOnClickListener {
            startActivity(Intent(this, PencarianActivity::class.java))
        }
    }

    fun register() {
        if (pendaftaran_namapengguna.text.toString().trim().isEmpty()) {
            namapengguna_textinputlayout.error = "Kolom nama pengguna tidak boleh kosong"
            pendaftaran_namapengguna.requestFocus()
            return
        } else if (pendaftaran_katasandi.text.toString().trim().isEmpty()) {
            katasandi_textinputlayout.error = "Kolom nama pengguna tidak boleh kosong"
            pendaftaran_katasandi.requestFocus()
            return
        } else if (pendaftaran_telepon.text.toString().trim().isEmpty()) {
            telepon_textinputlayout.error = "Kolom nama pengguna tidak boleh kosong"
            pendaftaran_telepon.requestFocus()
            return
        }
        ApiConfig.endpoint.register(
            pendaftaran_namapengguna.text.toString().trim(),
            pendaftaran_katasandi.text.toString().trim(),
            pendaftaran_telepon.text.toString().trim()
        ).enqueue(object : Callback<ResponRegister> {
            override fun onResponse(call: Call<ResponRegister>, response: Response<ResponRegister>) {
                //response ketika berhasil
                val respon = response.body()!!

                if (respon.succes == 1){
                    s.setStatusLogin(true)
                    s.setUser(respon.user)
                    val intent = Intent(this@PendaftaranActivity, BerandaOrtuActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)
                    finish()
                    Toast.makeText(this@PendaftaranActivity, "Selamat Datang:" +respon.user.nama_pengguna, Toast.LENGTH_SHORT).show()
                } else{
                    Toast.makeText(this@PendaftaranActivity, "Pendaftaran Gagal:"+respon.message, Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ResponRegister>, t: Throwable) {
                //response ketika gagal
                Toast.makeText(this@PendaftaranActivity, "Error" + t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }

}