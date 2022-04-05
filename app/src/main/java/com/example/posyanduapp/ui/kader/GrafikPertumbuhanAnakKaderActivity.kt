package com.example.posyanduapp.ui.kader

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TableLayout
import androidx.viewpager2.widget.ViewPager2
import com.example.posyanduapp.R
import com.example.posyanduapp.databinding.ActivityGrafikPertumbuhanAnakKaderBinding
import com.example.posyanduapp.ui.bidan.FragmentAdapter
import com.example.posyanduapp.ui.bidan.RiwayatStatusGiziAnakctivity
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class GrafikPertumbuhanAnakKaderActivity : AppCompatActivity() {

    lateinit var binding: ActivityGrafikPertumbuhanAnakKaderBinding
    var idnya: String = ""
    var namanya: String = ""
    lateinit var tablayout1 : TabLayout
    lateinit var viewPager2 : ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGrafikPertumbuhanAnakKaderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        idnya = intent.getStringExtra("nik").toString()
        namanya= intent.getStringExtra("nama").toString()
        binding.grafiktumbuhNamaAnak.setText(namanya)

        tablayout1 = binding.grafiktumbuhTablayout
        viewPager2 = binding.grafiktumbuhViewpager2

        //intent
        binding.btnKembaligrafiktumbuh.setOnClickListener {
            startActivity(Intent(this,FiturAnakKaderActivity::class.java))
        }
        binding.btnStatusgiziKader.setOnClickListener {
            intent = Intent(this, RiwayatStatusGizAnakKaderActivity::class.java)
            intent.putExtra("nik_anak", idnya)
            intent.putExtra("nama_anak", namanya)
            startActivity(intent)
        }

        //Panggil adapter
        val adapter = FragmentAdapter(supportFragmentManager,lifecycle)
        viewPager2.adapter=adapter
        TabLayoutMediator(tablayout1,viewPager2){tab,position->
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

    }
}