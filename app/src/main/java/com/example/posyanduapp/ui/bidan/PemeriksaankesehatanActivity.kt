package com.example.posyanduapp.ui.bidan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.example.posyanduapp.R
import com.example.posyanduapp.databinding.ActivityKelolajadwalimunisasiBinding
import com.example.posyanduapp.databinding.ActivityPemeriksaankesehatanBinding

class PemeriksaankesehatanActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPemeriksaankesehatanBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPemeriksaankesehatanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dropdown()
        pindah()

    }
    fun dropdown (){
        //dropdown nama anak
        val nama_anak = resources.getStringArray(R.array.daftar_namaanak)
        val adapter = ArrayAdapter(
            this,
            R.layout.dropdown_listanak,
            nama_anak
        )
        with(binding.periksaNamaanak){
            setAdapter(adapter)
        }

        //dropdown imunisasi1
        val imunisasi1 = resources.getStringArray(R.array.imunisasi)
        val adapterimun1 = ArrayAdapter(
            this,
            R.layout.dropdown_listanak,
            imunisasi1
        )
        with(binding.periksaImunisasi1){
            setAdapter(adapterimun1)
        }

        //dropdown imunisasi2
        val imunisasi2 = resources.getStringArray(R.array.imunisasi)
        val adapterimun2 = ArrayAdapter(
            this,
            R.layout.dropdown_listanak,
            imunisasi2
        )
        with(binding.periksaImunisasi2){
            setAdapter(adapterimun2)
        }

        //dropdown imunisasi3
        val imunisasi3 = resources.getStringArray(R.array.imunisasi)
        val adapterimun3 = ArrayAdapter(
            this,
            R.layout.dropdown_listanak,
            imunisasi3
        )
        with(binding.periksaImunisasi3){
            setAdapter(adapterimun3)
        }

        //dropdown vitA merah
        val vit1 = resources.getStringArray(R.array.obat)
        val adaptervit1 = ArrayAdapter(
            this,
            R.layout.dropdown_listanak,
            vit1
        )
        with(binding.periksaVitAmerah){
            setAdapter(adaptervit1)
        }

        //dropdown vitA biru
        val vit2 = resources.getStringArray(R.array.obat)
        val adaptervit2 = ArrayAdapter(
            this,
            R.layout.dropdown_listanak,
            vit2
        )
        with(binding.periksaVitAbiru){
            setAdapter(adaptervit2)
        }

        //oralit
        val oralit = resources.getStringArray(R.array.obat)
        val adapteroralit = ArrayAdapter(
            this,
            R.layout.dropdown_listanak,
            oralit
        )
        with(binding.periksaOralit){
            setAdapter(adapteroralit)
        }

        //Fe1
        val fe1 = resources.getStringArray(R.array.obat)
        val adapterfe1 = ArrayAdapter(
            this,
            R.layout.dropdown_listanak,
            fe1
        )
        with(binding.periksaFe1){
            setAdapter(adapterfe1)
        }

        //Fe2
        val fe2 = resources.getStringArray(R.array.obat)
        val adapterfe2 = ArrayAdapter(
            this,
            R.layout.dropdown_listanak,
            fe2
        )
        with(binding.periksaFe2){
            setAdapter(adapterfe2)
        }

        //PMT
        val pmt = resources.getStringArray(R.array.obat)
        val adapterpmt = ArrayAdapter(
            this,
            R.layout.dropdown_listanak,
            pmt
        )
        with(binding.periksaPMT){
            setAdapter(adapterpmt)
        }

        //asi
        val asi = resources.getStringArray(R.array.obat)
        val adapterasi = ArrayAdapter(
            this,
            R.layout.dropdown_listanak,
            asi
        )
        with(binding.periksaAsi){
            setAdapter(adapterasi)
        }

        //obat cacing
        val obatcacing = resources.getStringArray(R.array.obat)
        val adaptercacing = ArrayAdapter(
            this,
            R.layout.dropdown_listanak,
            obatcacing
        )
        with(binding.periksaObatcacing){
            setAdapter(adaptercacing)
        }

        //nama bidan
        val namabidan = resources.getStringArray(R.array.nama_bidan)
        val adapterbidan = ArrayAdapter(
            this,
            R.layout.dropdown_listanak,
            namabidan
        )
        with(binding.periksaNamabidan){
            setAdapter(adapterbidan)
        }
    }
    fun pindah(){
        binding.btnKembalipemeriksaankesehatan.setOnClickListener {
            startActivity(Intent(this, KesehatananakActivity::class.java))
        }
    }
}