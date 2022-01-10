package com.example.posyanduapp.ui.kader

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ArrayAdapter
import com.example.posyanduapp.R
import com.example.posyanduapp.databinding.ActivityKartuAnakBinding

class KartuAnakActivity : AppCompatActivity() {

    private lateinit var binding : ActivityKartuAnakBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKartuAnakBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backToberandakader.setOnClickListener {
            startActivity(Intent(this, BerandakaderActivity::class.java))
        }
        dropdown()



    }

    fun dropdown(){
        //nama anak
        val nama_anak = resources.getStringArray(R.array.daftar_namaanak)
        val adapteranak = ArrayAdapter(
            this,
            R.layout.dropdown_listanak,
            nama_anak
        )
        with(binding.kartuanakNamaanak){
            setAdapter(adapteranak)
        }
    }
}