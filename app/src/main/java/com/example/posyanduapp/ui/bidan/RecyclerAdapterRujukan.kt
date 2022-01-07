package com.example.posyanduapp.ui.bidan

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.example.posyanduapp.R

class RecyclerAdapterRujukan (private var namaposyandu: List<String>, private var tanggalrujukan: List<String>, private var namaanak: List<String>,
                              private var namabidan: List<String>, private var namapuskesmas: List<String>, private var penyakit: List<String>) :
RecyclerView.Adapter<RecyclerAdapterRujukan.ViewHolder>(){

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val posyandu : TextView = itemView.findViewById(R.id.tvrujukan_namaposyandu)
        val tanggal : TextView = itemView.findViewById(R.id.tvrujukan_tanggal)
        val namaanak : TextView = itemView.findViewById(R.id.tvrujukan_namaanak)
        val namabidan : TextView = itemView.findViewById(R.id.tvrujukan_namabidan)
        val namapuskesmas : TextView = itemView.findViewById(R.id.tvrujukan_puskesmas)
        val penyakit : TextView = itemView.findViewById(R.id.tvrujukan_namapenyakit)

        init {
            itemView.setOnClickListener { v: View ->
                val position: Int = adapterPosition
                Toast.makeText(itemView.context,"Kamu sedang memilih item nomor ${position +1}", Toast.LENGTH_SHORT).show()
            }
        }
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_layout_rujukan,parent,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.posyandu.text = namaposyandu[position]
        holder.tanggal.text = tanggalrujukan[position]
        holder.namaanak.text = namaanak[position]
        holder.namabidan.text = namabidan[position]
        holder.namapuskesmas.text = namapuskesmas[position]
        holder.penyakit.text = penyakit[position]

    }

    override fun getItemCount(): Int {
        return namaposyandu.size
    }
}