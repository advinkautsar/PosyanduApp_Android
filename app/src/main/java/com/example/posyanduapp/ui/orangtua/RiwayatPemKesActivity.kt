package com.example.posyanduapp.ui.orangtua

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.posyanduapp.R
import com.example.posyanduapp.databinding.ActivityRiwayatPemKesBinding
import com.example.posyanduapp.ui.bidan.AnakActivity
import com.example.posyanduapp.ui.bidan.RecyclerAdapterRekapImun

class RiwayatPemKesActivity : AppCompatActivity() {

    private var tanngallist = mutableListOf<String>()
    private var pemkes1list = mutableListOf<String>()
    private var pemkes2list = mutableListOf<String>()
    private var pemkes3list = mutableListOf<String>()
    private var pemkes4list = mutableListOf<String>()
    private var pemkes5list = mutableListOf<String>()
    private var pemkes6list = mutableListOf<String>()
    private var pemkes7list = mutableListOf<String>()
    private var pemkes8list = mutableListOf<String>()
    private var pemkes9list = mutableListOf<String>()
    private var pemkes10list = mutableListOf<String>()
    private var pemkes11list = mutableListOf<String>()
    private var pemkes12list = mutableListOf<String>()
    private var pemkes13list = mutableListOf<String>()
    private var pemkes14list = mutableListOf<String>()
    private var pemkes15list = mutableListOf<String>()

    private lateinit var binding : ActivityRiwayatPemKesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRiwayatPemKesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Intent
        binding.btnKembaliriwayatPemKes.setOnClickListener {
            startActivity(Intent(this, AnakActivity::class.java))
        }

        //add content into recyclerview
        postToList()


        //recyclerview
        binding.rvRiwayatPemKes.layoutManager = LinearLayoutManager(this)
        binding.rvRiwayatPemKes.adapter = RecyclerAdapterRiwPemkes (tanngallist, pemkes1list, pemkes2list,
        pemkes3list, pemkes4list, pemkes5list, pemkes6list, pemkes7list, pemkes8list, pemkes9list, pemkes10list, pemkes11list,
        pemkes12list, pemkes13list, pemkes14list, pemkes15list)
    }

    private fun postToList() {
        for (i in 1..10){
            addTolist( "21 - 02 - 2022", "3 Kg", "30cm", "-",
            "Polio 1", "Tidak", "Tidak", "Tidak", "Tidak", "Tidak",
            "Tidak", "Tidak", "Tidak", "Tidak", "Tidak", "Bu Ida")

        }
    }

    private fun addTolist( tgl : String, pemkes1 : String, pemkes2 : String, pemkes3 : String, pemkes4 : String, pemkes5 : String,
                           pemkes6 : String, pemkes7 : String, pemkes8 : String, pemkes9 : String, pemkes10 : String, pemkes11 : String,
                           pemkes12 : String, pemkes13 : String, pemkes14 : String, pemkes15 : String,) {
        tanngallist.add(tgl)
        pemkes1list.add(pemkes1)
        pemkes2list.add(pemkes2)
        pemkes3list.add(pemkes3)
        pemkes4list.add(pemkes4)
        pemkes5list.add(pemkes5)
        pemkes6list.add(pemkes6)
        pemkes7list.add(pemkes7)
        pemkes8list.add(pemkes8)
        pemkes9list.add(pemkes9)
        pemkes10list.add(pemkes10)
        pemkes11list.add(pemkes11)
        pemkes12list.add(pemkes12)
        pemkes13list.add(pemkes13)
        pemkes14list.add(pemkes14)
        pemkes15list.add(pemkes15)




    }
}