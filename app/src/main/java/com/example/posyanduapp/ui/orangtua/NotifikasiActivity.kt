package com.example.posyanduapp.ui.orangtua

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.posyanduapp.R
import com.example.posyanduapp.databinding.ActivityNotifikasiBinding
import com.example.posyanduapp.ui.bidan.RecyclerAdapterRujukan

class NotifikasiActivity : AppCompatActivity() {

    private var titlelist = mutableListOf<String>()
    private var kontenlist = mutableListOf<String>()

    lateinit var binding : ActivityNotifikasiBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNotifikasiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //intent
        binding.btnKembalinotif.setOnClickListener {
            startActivity(Intent(this, BerandaOrtuActivity::class.java))
        }

        //add isi
        postToList()

        //rv
        binding.rvNotif.layoutManager = LinearLayoutManager(this)
        binding.rvNotif.adapter = RecyclerAdapterNotif(titlelist,kontenlist)
    }
    private fun addToList(judul:String, Isi:String){
        titlelist.add(judul)
        kontenlist.add(Isi)

    }
    private fun postToList(){
        for ( i in 1..25){
            addToList("Jadwal Imunisasi Anak", "Halo ibu diah, imunisasi anak campak selanjutnya mulai tanggal : 12 Februari 2022" )
        }

    }
}