package com.example.posyanduapp.ui.orangtua

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.posyanduapp.R
import com.example.posyanduapp.databinding.ActivityStatusGiziBinding
import com.example.posyanduapp.ui.bidan.AnakActivity

class StatusGiziActivity : AppCompatActivity() {

    private var tgllist = mutableListOf<String>()
    private var bblist = mutableListOf<String>()
    private var tblist = mutableListOf<String>()
    private var lklist = mutableListOf<String>()
    private var bbumurlist = mutableListOf<String>()
    private var tbumurlist = mutableListOf<String>()
    private var lkumurlist = mutableListOf<String>()
    private var bbtblist = mutableListOf<String>()
    private var imtlist = mutableListOf<String>()


    private lateinit var binding : ActivityStatusGiziBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStatusGiziBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //intent
        binding.btnKembalistatusgizi.setOnClickListener {
            startActivity(Intent(this, GrafikPertumbuhanActivity::class.java))
        }

        //add content rv
        postToList()


        //recyclerview
        binding.rvStatusgizi.layoutManager = LinearLayoutManager(this)
        binding.rvStatusgizi.adapter = RecyclerAdapterStatGizi( tgllist, bblist, tblist, lklist, bbumurlist,
        tbumurlist,lkumurlist,bbtblist,imtlist)

    }

    private fun postToList() {
        for (i in 1..10){
            addTolist("21-02-2022", "15 kg","70 cm" ,"-"
            , "Normal", "Normal", "-", "Gizi baik", "Gizi Baik")
        }

    }

    private fun addTolist( tanggalukur : String, beratbadan : String, tinggibadan : String,
    lingkarkepala : String, bbumur : String, tbumur : String, lkumur : String, bbtb : String, imt : String ) {

        tgllist.add(tanggalukur)
        bblist.add(beratbadan)
        tblist.add(tinggibadan)
        lklist.add(lingkarkepala)
        bbumurlist.add(bbumur)
        tbumurlist.add(tbumur)
        lkumurlist.add(lkumur)
        bbtblist.add(bbtb)
        imtlist.add(imt)

    }
}