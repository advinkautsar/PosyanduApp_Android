package com.example.posyanduapp.ui.orangtua

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.posyanduapp.R
import com.example.posyanduapp.databinding.ActivityDetailAnakBinding
import com.example.posyanduapp.ui.bidan.AnakActivity

class DetailAnakActivity : AppCompatActivity() {

    lateinit var binding : ActivityDetailAnakBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailAnakBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //intent
        binding.btnBackdetailanak.setOnClickListener {
            startActivity(Intent(this, AnakActivity::class.java))
        }
    }
}