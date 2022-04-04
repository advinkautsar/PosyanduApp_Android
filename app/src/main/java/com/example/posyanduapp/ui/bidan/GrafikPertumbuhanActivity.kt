package com.example.posyanduapp.ui.bidan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.example.posyanduapp.R
import com.example.posyanduapp.databinding.ActivityGrafikPertumbuhanBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class GrafikPertumbuhanActivity : AppCompatActivity() {

    var idnya: String = ""
    var namanya: String = ""

    lateinit var binding: ActivityGrafikPertumbuhanBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGrafikPertumbuhanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val tablayout = findViewById<TabLayout>(R.id.grafiktumbuh_tablayout)
        val viewpager2 = findViewById<ViewPager2>(R.id.grafiktumbuh_viewpager2)

        idnya = intent.getStringExtra("nik").toString()
        namanya= intent.getStringExtra("nama").toString()
        binding.grafiktumbuhNamaAnak.setText(namanya)


        val adapter = FragmentAdapter(supportFragmentManager,lifecycle)

        viewpager2.adapter=adapter

        TabLayoutMediator(tablayout,viewpager2){tab,position->
            when (position){
                0->{
                    tab.text="BB/U"
                }
                1->{
                    tab.text="TB/U"
                }
                2->{
                    tab.text="LK/U"
                }
            }
        }.attach()

        //intent
        binding.btnKembaligrafiktumbuh.setOnClickListener {
            startActivity(Intent(this, AnakActivity::class.java))
        }

        binding.btnStatusgizi.setOnClickListener() {
            intent = Intent(this, RiwayatStatusGiziAnakctivity::class.java)
            intent.putExtra("nik_anak", idnya)
            intent.putExtra("nama_anak", namanya)
            startActivity(intent)
        }


        }
}