package com.example.posyanduapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.posyanduapp.AppConfig.ApiConfig
import com.example.posyanduapp.Helper.SharedPref
import com.example.posyanduapp.Model.ResponRegister
import com.example.posyanduapp.R
import com.example.posyanduapp.ui.bidan.BerandabidanActivity
import com.example.posyanduapp.ui.kader.BerandakaderActivity
import com.example.posyanduapp.ui.orangtua.BerandaOrtuActivity
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_pendaftaran.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    private lateinit var s:SharedPref

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        s = SharedPref(this)

        val tv_daftar = findViewById(R.id.tv_daftar) as TextView
        val btn_login = findViewById(R.id.btn_login) as Button

        btn_login.setOnClickListener {
            login()
        }
        tv_daftar.setOnClickListener {
            startActivity(Intent(this, PencarianActivity::class.java ))
        }




    }
    fun login (){
        if (login_nama.text.toString().trim().isEmpty()) {
            loginnama_textinputlayout.error = "Kolom nama pengguna tidak boleh kosong"
            login_nama.requestFocus()
            return
        } else if(login_sandi.text.toString().trim().isEmpty()) {
            loginsandi_textinputlayout.error = "Kolom kata sandi tidak boleh kosong"
            login_sandi.requestFocus()
            return
        }
        ApiConfig.endpoint.login(
            login_nama.text.toString().trim(),
            login_sandi.text.toString().trim(),
        ).enqueue(object : Callback<ResponRegister> {
            override fun onResponse(call: Call<ResponRegister>, response: Response<ResponRegister>) {
                val respon = response.body()!!

                if (respon.succes == 1){
                    s.setStatusLogin(true)
                    if (respon.user.role.equals("bidan")){
                        s.setUser(respon.user)
                        val intent = Intent(this@LoginActivity, BerandabidanActivity::class.java)
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                        finish()
                    } else if (respon.user.role.equals("kader")){
                        s.setUser(respon.user)
                        val intent = Intent(this@LoginActivity, BerandakaderActivity::class.java)
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                        finish()
                    } else{
                        s.setUser(respon.user)
                        val intent = Intent(this@LoginActivity, BerandaOrtuActivity::class.java)
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                        finish()
                    }
                    Toast.makeText(this@LoginActivity, "Selamat Datang:" +respon.user.nama_pengguna, Toast.LENGTH_SHORT).show()
                } else{
                    Toast.makeText(this@LoginActivity, "Pendaftaran Gagal:"+respon.message, Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ResponRegister>, t: Throwable) {
                Toast.makeText(this@LoginActivity, "Error" + t.message, Toast.LENGTH_SHORT).show()
            }

        })



    }
}