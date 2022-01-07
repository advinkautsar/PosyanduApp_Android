package com.example.posyanduapp.ui.bidan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.posyanduapp.R
import com.example.posyanduapp.databinding.ActivityRekapimunisasiBinding

class RekapimunisasiActivity : AppCompatActivity() {

    private var vaksin1list = mutableListOf<String>()
    private var vaksin2list = mutableListOf<String>()
    private var vaksin3list = mutableListOf<String>()
    private var vaksin4list = mutableListOf<String>()
    private var vaksin5list = mutableListOf<String>()
    private var vaksin6list = mutableListOf<String>()
    private var vaksin7list = mutableListOf<String>()
    private var vaksin8list = mutableListOf<String>()
    private var vaksin9list = mutableListOf<String>()
    private var vaksin10list = mutableListOf<String>()
    private var vaksin11list = mutableListOf<String>()
    private var vaksin12list = mutableListOf<String>()
    private var vaksin13list = mutableListOf<String>()


    private lateinit var binding: ActivityRekapimunisasiBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRekapimunisasiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        postToList()

        binding.btnKembalirekapimun.setOnClickListener {
            startActivity(Intent(this, BerandabidanActivity::class.java))
        }

        binding.rvRekapimun.layoutManager = LinearLayoutManager(this)
        binding.rvRekapimun.adapter = RecyclerAdapterRekapImun (vaksin1list, vaksin2list, vaksin3list, vaksin4list, vaksin5list, vaksin6list,
        vaksin7list, vaksin8list, vaksin9list, vaksin10list, vaksin11list, vaksin12list, vaksin13list)

    }
    private fun addToList(vaksin1: String, vaksin2: String,vaksin3: String, vaksin4: String, vaksin5: String, vaksin6: String, vaksin7: String,
                          vaksin8: String, vaksin9: String, vaksin10: String, vaksin11: String, vaksin12: String, vaksin13: String,){
        vaksin1list.add(vaksin1)
        vaksin2list.add(vaksin2)
        vaksin3list.add(vaksin3)
        vaksin4list.add(vaksin4)
        vaksin5list.add(vaksin5)
        vaksin6list.add(vaksin6)
        vaksin7list.add(vaksin7)
        vaksin8list.add(vaksin8)
        vaksin9list.add(vaksin9)
        vaksin10list.add(vaksin10)
        vaksin11list.add(vaksin11)
        vaksin12list.add(vaksin12)
        vaksin13list.add(vaksin13)




    }
    private fun postToList(){
        for (i in 1..7){
            addToList("2 anak", "1 anak", "-", "5 anak", "7 anak", "1 anak", "11 anak",
            "8 anak", "1 anak", "3 anak", "1 anak", "-", "-")
        }
    }
}