package com.example.posyanduapp.ui.bidan

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextClock
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.posyanduapp.R

class RecyclerAdapterAnak (private var anak: List<String>, private var ibu: List<String>) :
RecyclerView.Adapter<RecyclerAdapterAnak.ViewHolder>(){

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val nama_anak : TextView = itemView.findViewById(R.id.tvanak_namanak)
        val nama_ibu : TextView = itemView.findViewById(R.id.tvanak_namaibu)

        init {
            itemView.setOnClickListener { v: View->
                val position: Int = adapterPosition
                Toast.makeText(itemView.context, "Kamu sedang memilih informasi Anak ke ${position +1}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_layout_anak,parent,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.nama_anak.text = anak[position]
        holder.nama_ibu.text = ibu[position]
    }

    override fun getItemCount(): Int {
        return anak.size
    }
}