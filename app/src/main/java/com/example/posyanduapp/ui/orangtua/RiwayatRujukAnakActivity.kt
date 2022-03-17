package com.example.posyanduapp.ui.orangtua

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.posyanduapp.R
import com.example.posyanduapp.databinding.ActivityRiwayatRujukAnakBinding
import com.example.posyanduapp.ui.bidan.AnakActivity
import com.example.posyanduapp.ui.bidan.RecyclerAdapterRujukan

class RiwayatRujukAnakActivity : AppCompatActivity() {

    private var posyandulist = mutableListOf<String>()
    private var tanggallist = mutableListOf<String>()
    private var namaanaklist = mutableListOf<String>()
    private var namabidanlist = mutableListOf<String>()
    private var puskesmaslist = mutableListOf<String>()
    private var penyakitlist = mutableListOf<String>()

    lateinit var binding : ActivityRiwayatRujukAnakBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRiwayatRujukAnakBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //intent
        binding.btnKembalirujukanak.setOnClickListener {
            startActivity(Intent(this, AnakActivity::class.java))
        }

        //recyclerview
        postToList()

        //recyclerview
        binding.rvRiwayatrujukananak.layoutManager = LinearLayoutManager(this)
        binding.rvRiwayatrujukananak.adapter = RecyclerAdapterRujukan(posyandulist, tanggallist, namaanaklist, namabidanlist,
            puskesmaslist, penyakitlist)
    }
    private fun addToList(posyandu: String, tanggal: String, anak: String, bidan: String, puskesmas: String, penyakit: String){
        posyandulist.add(posyandu)
        tanggallist.add(tanggal)
        namaanaklist.add(anak)
        namabidanlist.add(bidan)
        puskesmaslist.add(puskesmas)
        penyakitlist.add(penyakit)
    }
    private fun postToList(){
        for ( i in 1..25){
            addToList("Posyandu Belewah ${i}","21-04-2022", "Bambang Gentolet", "Bidan Ifa",
                "Puskesmas Paspan", "Anak Menderita Gizi Buruk yang harus segera mendapatkan penanganan oleh dokter di puskesmas terdekat" )
        }

    }
}