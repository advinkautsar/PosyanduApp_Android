package com.example.posyanduapp

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDelegate
import com.example.posyanduapp.Helper.SharedPref
import com.example.posyanduapp.helper.PreferenceHelper.customPreference
import com.example.posyanduapp.helper.PreferenceHelper.islogin
import com.example.posyanduapp.ui.LoginActivity
import com.example.posyanduapp.ui.bidan.BerandabidanActivity
import com.example.posyanduapp.ui.kader.BerandakaderActivity
import com.example.posyanduapp.ui.orangtua.BerandaOrtuActivity


class MainActivity : AppCompatActivity() {
    private lateinit var s: SharedPref



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        s = SharedPref(this)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        var logo = findViewById<ImageView>(R.id.iv_posyandu)



        logo.alpha = 0f
        logo.animate().setDuration(1800).alpha(1f).withEndAction {
            val i = Intent(this, LoginActivity::class.java)
            startActivity(i)
            finish()

        }

    }
    override fun onStart() {
        super.onStart()
        val user = s.getUser()
        // check if user not logged in, redirect to LoginActivity
        if (!checkLoginState()) {
            val builder = AlertDialog.Builder(this)
            with(builder) {
                setTitle("Info")
                setMessage("Silahkan login untuk melanjutkan.")
                setPositiveButton("OK", DialogInterface.OnClickListener(function = okButtonClick))
                show()
            }
        }else{
            if (user != null) {
                if(user.role=="bidan"){
                    val intent = Intent(this,BerandabidanActivity::class.java)
                    startActivity(intent)
                    finish()

                }else if (user.role=="kader"){
                    val intent = Intent(this,BerandakaderActivity::class.java)
                    startActivity(intent)
                    finish()

                }else{
                    val intent = Intent(this,BerandaOrtuActivity::class.java)
                    startActivity(intent)
                    finish()

                }
            }

        }
    }
    private fun checkLoginState(): Boolean {

        s = SharedPref(this)
        return s.getStatusLogin()
//        val prefs = customPreference(this, "userdata")
//        return prefs.islogin
    }

    private val okButtonClick = { dialog: DialogInterface, which: Int ->
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }

}