package com.example.posyanduapp.ui.kader

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.posyanduapp.R
import com.example.posyanduapp.databinding.ActivityOrangtuaBinding
import com.example.posyanduapp.ui.bidan.RecyclerAdapterAnak

class OrangtuaActivity : AppCompatActivity() {

    private var ortulist = mutableListOf<String>()
    private var poslist = mutableListOf<String>()

    private lateinit var binding : ActivityOrangtuaBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrangtuaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        postTolist()
        //intent
        binding.btnKembaliorangtua.setOnClickListener {
            startActivity(Intent(this, BerandakaderActivity::class.java))
        }
        //onitemclicklistener intent to another activity
        binding.rvOrangtua.layoutManager = LinearLayoutManager(this)
        var adapter = RecyclerAdapterOrtu(ortulist,poslist)
        binding.rvOrangtua.adapter = adapter
        adapter.setOnItemClicklistener(object : RecyclerAdapterOrtu.onItemClickListener{
            override fun onItemClick(position: Int) {
                val intent = Intent(this@OrangtuaActivity, DetailortuActivity::class.java)
                startActivity(intent)
            }

        })
    }

    private fun addTolist(orangtua: String, posyandu: String){
        ortulist.add(orangtua)
        poslist.add(posyandu)


    }
    private fun postTolist(){
        for ( i in 1..20){
            addTolist("Lely Indah Wulandari","Posyandu Melati" )
        }
    }

}