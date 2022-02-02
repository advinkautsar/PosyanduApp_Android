package com.example.posyanduapp.ui.orangtua

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.example.posyanduapp.R
import com.example.posyanduapp.databinding.ActivityDetailAnakBinding
import com.example.posyanduapp.databinding.ActivityGrafikPertumbuhanBinding
import com.example.posyanduapp.ui.bidan.AnakActivity
import com.example.posyanduapp.ui.orangtua.GrafikFragments.BeratBadanUmurFragment
import com.example.posyanduapp.ui.orangtua.GrafikFragments.LingkarKepalaUmurFragment
import com.example.posyanduapp.ui.orangtua.GrafikFragments.TinggiBadanUmurFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class GrafikPertumbuhanActivity : AppCompatActivity() {

    lateinit var binding: ActivityGrafikPertumbuhanBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGrafikPertumbuhanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val tablayout = findViewById<TabLayout>(R.id.grafiktumbuh_tablayout)
        val viewpager2 = findViewById<ViewPager2>(R.id.grafiktumbuh_viewpager2)

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
        binding.btnStatusgizi.setOnClickListener {
            startActivity(Intent(this, StatusGiziActivity::class.java ))
        }


    }
}