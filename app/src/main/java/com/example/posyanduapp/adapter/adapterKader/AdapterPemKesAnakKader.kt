package com.example.posyanduapp.adapter.adapterKader

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.posyanduapp.adapter.AdapterPemeriksaan
import com.example.posyanduapp.databinding.ItemLayoutRiwayatpemkesBinding
import com.example.posyanduapp.model.ListPemeriksaan

class AdapterPemKesAnakKader(
    private val context: Context,
    private val data: ArrayList<ListPemeriksaan.Result>
) :
    RecyclerView.Adapter<AdapterPemKesAnakKader.PesananViewHolder>(){
        class PesananViewHolder(var binding: ItemLayoutRiwayatpemkesBinding):
                RecyclerView.ViewHolder(binding.root){

                }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PesananViewHolder {
        return AdapterPemKesAnakKader.PesananViewHolder(
            ItemLayoutRiwayatpemkesBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PesananViewHolder, position: Int) {
        val currentItem = data[position]
        // set value to the holder view
        holder.binding.riwayatpemkesTgl.text = currentItem.tanggal_pemeriksaan
        holder.binding.riwpemkesAsi.text = currentItem.asi_ekslusif
        holder.binding.riwpemkesFe1.text = currentItem.Fe_1
        holder.binding.riwpemkesFe2.text = currentItem.Fe_2
        holder.binding.riwpemkesImun1.text = currentItem.imun1
        holder.binding.riwpemkesImun2.text = currentItem.imun2
        holder.binding.riwpemkesImun3.text = currentItem.imun3
        holder.binding.riwpemkesVitAbiru.text = currentItem.vitA_biru
        holder.binding.riwpemkesVitAmerah.text = currentItem.vitA_merah
        holder.binding.riwpemkesOralit.text = currentItem.oralit
        holder.binding.riwpemkesObatcacing.text = currentItem.obat_cacing
        holder.binding.riwpemkesBidan.text = currentItem.nama_bidan
    }

    override fun getItemCount() = data.size
    }
