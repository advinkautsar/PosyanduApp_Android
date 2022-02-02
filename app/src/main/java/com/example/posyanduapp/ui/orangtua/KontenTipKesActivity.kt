package com.example.posyanduapp.ui.orangtua

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.example.posyanduapp.R

class KontenTipKesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_konten_tip_kes)

        val judul = findViewById<TextView>(R.id.konten_judul)
        val gambar = findViewById<ImageView>(R.id.konten_gambar)
        val konten = findViewById<TextView>(R.id.konten_isi)

        val bundle : Bundle?= intent.extras
        val titleid = bundle!!.getString("titleid")
        val imageid = bundle.getInt("imageid")
        val kontenid = bundle.getString("kontenid")

        judul.text = titleid
        gambar.setImageResource(imageid)
        konten.text = kontenid
    }
}