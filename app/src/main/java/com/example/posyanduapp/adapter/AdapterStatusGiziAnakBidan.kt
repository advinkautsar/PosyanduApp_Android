package com.example.posyanduapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.posyanduapp.Model.GetRiwayatStatusGiziAnak
import com.example.posyanduapp.databinding.ActivityStatusGiziBinding
import com.example.posyanduapp.databinding.ItemLayoutRiwayatpemkesBinding
import com.example.posyanduapp.databinding.ItemLayoutStatusgiziBinding
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class AdapterStatusGiziAnakBidan(
    private val context: Context,
    private val data: ArrayList<GetRiwayatStatusGiziAnak.Result>

) : RecyclerView.Adapter<AdapterStatusGiziAnakBidan.ViewHolder>() {
        class ViewHolder(var binding: ItemLayoutStatusgiziBinding) :
            RecyclerView.ViewHolder(binding.root){

            }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return AdapterStatusGiziAnakBidan.ViewHolder(
            ItemLayoutStatusgiziBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = data[position]
        holder.binding.statusgiziTgl.text = currentItem.created_at
        holder.binding.sgiziBB.text = currentItem.berat_badan
        holder.binding.sgiziPB.text = currentItem.tinggi_badan
        holder.binding.sgiziLK.text = currentItem.lingkar_kepala
        holder.binding.sgiziBBUmur.text = currentItem.status_bb_u
        holder.binding.sgiziTBUmur.text = currentItem.status_tb_u
        holder.binding.sgiziLKUmur.text = currentItem.status_lk_u
        holder.binding.sgiziBbTb.text = currentItem.status_bb_tb
        holder.binding.sgiziIMT.text = currentItem.status_imt_u


    }

    fun convertFormat(inputDate: String?): String? {
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
        var date: Date? = null
        try {
            date = simpleDateFormat.parse(inputDate)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        if (date == null) {
            return ""
        }
        val convetDateFormat = SimpleDateFormat("dd-MMMM-yyyy hh:mm")
        return convetDateFormat.format(date)
    }


    override fun getItemCount()= data.size
}