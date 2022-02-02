package com.example.posyanduapp.ui.orangtua

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.posyanduapp.R

class RecyclerAdapterStatGizi
    (
     private var tanggalstat: List<String>,
     private var bb: List<String>,
     private var tb: List<String>,
     private var lk: List<String>,
     private var bbumur: List<String>,
     private var tbumur: List<String>,
     private var lkumur: List<String>,
     private var bbtb: List<String>,
     private var imtumur: List<String>,)
    :RecyclerView.Adapter<RecyclerAdapterStatGizi.ViewHolder>(){

        inner class ViewHolder(itemview : View) : RecyclerView.ViewHolder(itemview){
            val tgl : TextView = itemView.findViewById(R.id.statusgizi_tgl)
            val beratbadan : TextView = itemView.findViewById(R.id.sgizi_BB)
            val tinggibadan : TextView = itemView.findViewById(R.id.sgizi_PB)
            val lingkarkepala: TextView = itemView.findViewById(R.id.sgizi_LK)
            val BBumur : TextView = itemView.findViewById(R.id.sgizi_BBUmur)
            val TBumur : TextView = itemView.findViewById(R.id.sgizi_TBUmur)
            val LKumur : TextView = itemView.findViewById(R.id.sgizi_LKUmur)
            val BBTB : TextView = itemView.findViewById(R.id.sgizi_BbTb)
            val IMT : TextView = itemView.findViewById(R.id.sgizi_IMT)

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
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_layout_statusgizi,parent,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tgl.text = tanggalstat[position]
        holder.beratbadan.text = bb[position]
        holder.tinggibadan.text = tb[position]
        holder.lingkarkepala.text = lk[position]
        holder.BBumur.text = bbumur[position]
        holder.TBumur.text = tbumur[position]
        holder.LKumur.text = lkumur[position]
        holder.BBTB.text = bbtb[position]
        holder.IMT.text = imtumur[position]
    }

    override fun getItemCount(): Int {
        return tanggalstat.size
    }
}