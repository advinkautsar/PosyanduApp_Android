package com.example.posyanduapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.posyanduapp.Model.ListRujukanAnak
import com.example.posyanduapp.databinding.ItemLayoutRujukanBinding
import com.example.posyanduapp.model.ListRujukan

class AdapterRiwayatRujukanAnak(
    private val context: Context,
    private val data: ArrayList<ListRujukanAnak.Result>
) :

    RecyclerView.Adapter<AdapterRiwayatRujukanAnak.ViewHolder>(){
        class ViewHolder(var binding: ItemLayoutRujukanBinding) :
                RecyclerView.ViewHolder(binding.root){

                }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemLayoutRujukanBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // set current item of result response tarif data
        val currentItem = data[position]
        // set value to the holder view
        holder.binding.tvrujukanNamaanak.text = currentItem.nama_anak
        holder.binding.tvrujukanNamaposyandu.text = currentItem.nama_posyandu
        holder.binding.tvrujukanNamabidan.text = currentItem.nama_bidan
        holder.binding.tvrujukanPuskesmas.text = currentItem.nama_puskesmas
        holder.binding.tvrujukanNamapenyakit.text = currentItem.keluhan_anak
        holder.binding.tvrujukanTanggal.text = currentItem.tanggal_rujukan
    }

    override fun getItemCount() = data.size

    }


