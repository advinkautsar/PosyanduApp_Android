package com.example.posyanduapp.ui.orangtua

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.posyanduapp.R
import com.example.posyanduapp.ui.kader.DetailortuActivity
import com.example.posyanduapp.ui.kader.RecyclerAdapterOrtu

class TipsKesehatanActivity : AppCompatActivity() {

    private lateinit var newRecyclerview : RecyclerView
    private lateinit var tiplist : ArrayList<Tipkes>
    private lateinit var imageid : Array<Int>
    private lateinit var titleid : Array<String>
    private lateinit var kontenid : Array<String>
    private lateinit var btn_back : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tips_kesehatan)

        btn_back = findViewById(R.id.btn_kembalitipkes)
        btn_back.setOnClickListener {
            startActivity(Intent(this, BerandaOrtuActivity::class.java))
        }

        imageid = arrayOf(
            R.mipmap.a,
            R.mipmap.b,
            R.mipmap.c,
            R.mipmap.vaccine,
            R.mipmap.e,
            R.mipmap.f,
            R.mipmap.g,
            R.mipmap.h,
            R.mipmap.i,
            R.mipmap.j,
        )
        titleid = arrayOf(
            "Kenali tanda anak anda sehat",
            "Kenali pola asuh anak yang benar",
            "Bagaimana Cara Merawat Anak sehari – hari ?",
            "Apakah Fungsi Imunisasi Anak ?",
            "Kebutuhan Gizi Bayi usia 0 - 6 bulan",
            "Perkembangan bayi usia 0 – 6 bulan",
            "Pemunuhan kebutuhan gizi bayi usia 6-12 bulan",
            "Perkembangan bayi usia 6- 12 bulan",
            "Pemunuhan kebutuhan gizi anak usia 1-6 tahun",
            "Perkembangan anak usia 1 – 6 tahun"
        )
        kontenid = arrayOf(
            getString(R.string.Definisi1),
            getString(R.string.Definisi2),
            getString(R.string.Definisi3),
            getString(R.string.Definisi4),
            getString(R.string.Definisi5),
            getString(R.string.Definisi6),
            getString(R.string.Definisi7),
            getString(R.string.Definisi8),
            getString(R.string.Definisi9),
            getString(R.string.Definisi10),

            )

        newRecyclerview = findViewById(R.id.rv_tipkes)
        newRecyclerview.layoutManager = LinearLayoutManager(this)
        newRecyclerview.setHasFixedSize(true)

        tiplist = arrayListOf<Tipkes>()
        getuserdata()
    }

    private fun getuserdata() {
        for (i in imageid.indices){
            val tip = Tipkes(imageid[i],titleid[i])
            tiplist.add(tip)
        }
        var adapter = RecyclerAdapterTipKes(tiplist)
        newRecyclerview.adapter = adapter
        adapter.setOnItemClicklistener(object : RecyclerAdapterTipKes.onItemClickListener{
            override fun onItemClick(position: Int) {
                val intent = Intent(this@TipsKesehatanActivity, KontenTipKesActivity::class.java)
                intent.putExtra("titleid",tiplist[position].judul_tip)
                intent.putExtra("imageid",tiplist[position].gambar_tip)
                intent.putExtra("kontenid", kontenid[position])
                startActivity(intent)
            }

        })
    }
}