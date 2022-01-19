package com.example.posyanduapp.ui.kader

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.posyanduapp.databinding.ActivityRiwayatJadwalPosyanduBinding

class RiwayatJadwalPosyanduActivity : AppCompatActivity() {

    private var tanggalposlist = mutableListOf<String>()
    private var namaposlist = mutableListOf<String>()
    private var waktuposlist = mutableListOf<String>()
    private var alamatposlist = mutableListOf<String>()
    private var ketposlist = mutableListOf<String>()

    private lateinit var binding: ActivityRiwayatJadwalPosyanduBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRiwayatJadwalPosyanduBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //intent
        binding.btnToberandakaderjadwalposyandu.setOnClickListener {
            startActivity(Intent(this, BerandakaderActivity::class.java))
        }
        binding.fabRiwayatjadwalposyandu.setOnClickListener {
            startActivity(Intent(this, JadwalPosyanduActivity::class.java))
        }

        //fungsi isi rv
        postTolist()

        //recycleview
        binding.rvRiwayatjadwalposyandu.layoutManager = LinearLayoutManager(this)
        binding.rvRiwayatjadwalposyandu.adapter = RecyclerAdapterJadwalPos(tanggalposlist, namaposlist, waktuposlist, alamatposlist, ketposlist  )
    }

    fun addTolist(tanggal: String, pos: String, waktu: String, alamat: String, ket: String){
        tanggalposlist.add(tanggal)
        namaposlist.add(pos)
        waktuposlist.add(waktu)
        alamatposlist.add(alamat)
        ketposlist.add(ket)
    }
    fun postTolist(){
        for (i in 1..5){
            addTolist("21-02-2022","posyandu anggrek 1", "09.00", "Jl. Belimbing Blok D5/32",
            "Kegiatan Posyandu")
        }
    }

}