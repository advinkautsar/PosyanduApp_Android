package com.example.posyanduapp.ui.orangtua

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.posyanduapp.R

class RecyclerAdapterNotif( private var title: List<String>,
                            private var konten: List<String>)
    : RecyclerView.Adapter<RecyclerAdapterNotif.ViewHolder>(){

        inner class ViewHolder(itemview : View) : RecyclerView.ViewHolder(itemview){
            val judul : TextView = itemView.findViewById(R.id.txt_titlenotification)
            val isi : TextView = itemView.findViewById(R.id.txt_contentnotification)

            init {
                itemView.setOnClickListener { v: View->
                    val position: Int = adapterPosition
                    Toast.makeText(itemView.context,
                        "Kamu sedang memilih riwayat ke ${position +1}",
                        Toast.LENGTH_SHORT).show()
                }
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_layout_notifikasi,parent,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.judul.text = title[position]
        holder.isi.text = konten[position]
    }

    override fun getItemCount(): Int {
        return title.size
    }
}