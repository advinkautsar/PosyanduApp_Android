package com.example.posyanduapp.ui.orangtua

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.posyanduapp.Helper.SharedPref
import com.example.posyanduapp.Model.ListTipsKes
import com.example.posyanduapp.Model.Orangtua
import com.example.posyanduapp.R
import com.example.posyanduapp.adapter.AdapterRujukanAnak
import com.example.posyanduapp.adapter.Orangtua.AdapterFiturAnakOrangtua
import com.example.posyanduapp.adapter.Orangtua.AdapterTipsKes
import com.example.posyanduapp.databinding.ActivityFiturAnakOrtuBinding
import com.example.posyanduapp.databinding.ActivityTipsKesehatanBinding
import com.example.posyanduapp.retrofit.ApiService
import com.example.posyanduapp.ui.kader.DetailortuActivity
import com.example.posyanduapp.ui.kader.RecyclerAdapterOrtu
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TipsKesehatanActivity : AppCompatActivity() {

//    private lateinit var newRecyclerview : RecyclerView
//    private lateinit var tiplist : ArrayList<Tipkes>
//    private lateinit var imageid : Array<Int>
//    private lateinit var titleid : Array<String>
//    private lateinit var kontenid : Array<String>
//    private lateinit var btn_back : ImageView

    lateinit var binding: ActivityTipsKesehatanBinding
    lateinit var rv_listTips : RecyclerView
    lateinit var adapter : AdapterTipsKes
    lateinit var ListTip: ListTipsKes.Result

    private lateinit var s: SharedPref

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTipsKesehatanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        rv_listTips = binding.rvTipkes
        s = SharedPref(this)

        getTips()

        //intent
        binding.btnKembalitipkes.setOnClickListener {
            startActivity(Intent(this,BerandaOrtuActivity::class.java))
        }

//        btn_back = findViewById(R.id.btn_kembalitipkes)
//        btn_back.setOnClickListener {
//            startActivity(Intent(this, BerandaOrtuActivity::class.java))
//        }
//
//        imageid = arrayOf(
//            R.mipmap.a,
//            R.mipmap.b,
//            R.mipmap.c,
//            R.mipmap.vaccine,
//            R.mipmap.e,
//            R.mipmap.f,
//            R.mipmap.g,
//            R.mipmap.h,
//            R.mipmap.i,
//            R.mipmap.j,
//        )
//        titleid = arrayOf(
//            "Kenali tanda anak anda sehat",
//            "Kenali pola asuh anak yang benar",
//            "Bagaimana Cara Merawat Anak sehari – hari ?",
//            "Apakah Fungsi Imunisasi Anak ?",
//            "Kebutuhan Gizi Bayi usia 0 - 6 bulan",
//            "Perkembangan bayi usia 0 – 6 bulan",
//            "Pemunuhan kebutuhan gizi bayi usia 6-12 bulan",
//            "Perkembangan bayi usia 6- 12 bulan",
//            "Pemunuhan kebutuhan gizi anak usia 1-6 tahun",
//            "Perkembangan anak usia 1 – 6 tahun"
//        )
//        kontenid = arrayOf(
//            getString(R.string.Definisi1),
//            getString(R.string.Definisi2),
//            getString(R.string.Definisi3),
//            getString(R.string.Definisi4),
//            getString(R.string.Definisi5),
//            getString(R.string.Definisi6),
//            getString(R.string.Definisi7),
//            getString(R.string.Definisi8),
//            getString(R.string.Definisi9),
//            getString(R.string.Definisi10),
//
//            )
//
//        newRecyclerview = findViewById(R.id.rv_tipkes)
//        newRecyclerview.layoutManager = LinearLayoutManager(this)
//        newRecyclerview.setHasFixedSize(true)
//
//        tiplist = arrayListOf<Tipkes>()
//        getuserdata()
    }

    private fun getTips() {
        ApiService.endpoint.getTips()
            .enqueue(object : Callback<ListTipsKes> {
                override fun onResponse(call: Call<ListTipsKes>, response: Response<ListTipsKes>) {
                    val status = response.body()?.status
                    val data = response.body()?.data
                    Log.d("responseData", data.toString())
                    if (status == "success" && data != null) {
                        // set adapter and layout manager for rv
                        rv_listTips.adapter = AdapterTipsKes(this@TipsKesehatanActivity, data)
                        rv_listTips.layoutManager = LinearLayoutManager(this@TipsKesehatanActivity)

                    } else {
                        Toast.makeText(
                            this@TipsKesehatanActivity, "Tidak ada List",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onFailure(call: Call<ListTipsKes>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
    }

//    private fun getuserdata() {
//        for (i in imageid.indices){
//            val tip = Tipkes(imageid[i],titleid[i])
//            tiplist.add(tip)
//        }
//        var adapter = RecyclerAdapterTipKes(tiplist)
//        newRecyclerview.adapter = adapter
//        adapter.setOnItemClicklistener(object : RecyclerAdapterTipKes.onItemClickListener{
//            override fun onItemClick(position: Int) {
//                val intent = Intent(this@TipsKesehatanActivity, KontenTipKesActivity::class.java)
//                intent.putExtra("titleid",tiplist[position].judul_tip)
//                intent.putExtra("imageid",tiplist[position].gambar_tip)
//                intent.putExtra("kontenid", kontenid[position])
//                startActivity(intent)
//            }
//
//        })
//    }

}