package com.example.posyanduapp.ui.bidan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.posyanduapp.R
import com.example.posyanduapp.databinding.ActivityAnakBinding

class AnakActivity : AppCompatActivity() {

    private var anaklist = mutableListOf<String>()
    private var ibulist = mutableListOf<String>()
    private var poslist = mutableListOf<String>()


    private lateinit var binding: ActivityAnakBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnakBinding.inflate(layoutInflater)
        setContentView(binding.root)

        postTolist()

        binding.btnKembalianak.setOnClickListener {
            startActivity(Intent(this, BerandabidanActivity::class.java))
        }

        binding.rvAnak.layoutManager = LinearLayoutManager(this)
        binding.rvAnak.adapter = RecyclerAdapterAnak(this,anaklist, ibulist, poslist)
    }

    private fun addTolist(anak: String, ibu: String, pos: String){
        anaklist.add(anak)
        ibulist.add(ibu)
        poslist.add(pos)


    }
    private fun postTolist(){
        for ( i in 1..20){
            addTolist("Abrar Ibrahim","Ibu Leni ikawangi", "Posyandu Melati")
        }
    }
}