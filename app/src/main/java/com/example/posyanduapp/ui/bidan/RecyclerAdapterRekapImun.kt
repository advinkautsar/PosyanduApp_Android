package com.example.posyanduapp.ui.bidan

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.posyanduapp.R

class RecyclerAdapterRekapImun
    (private var vaksin1: List<String>,
     private var vaksin2: List<String>,
     private var vaksin3: List<String>,
     private var vaksin4: List< String>,
     private var vaksin5: List<String>,
     private var vaksin6: List<String>,
     private var vaksin7: List<String>,
     private var vaksin8: List<String>,
     private var vaksin9: List<String>,
     private var vaksin10: List<String>,
     private var vaksin11: List<String>,
     private var vaksin12: List<String>,
     private var vaksin13: List<String>,) :
RecyclerView.Adapter<RecyclerAdapterRekapImun.ViewHolder>(){

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val jumlah_vaksin1 : TextView = itemView.findViewById(R.id.rekapimun_vaksin1)
        val jumlah_vaksin2 : TextView = itemView.findViewById(R.id.rekapimun_vaksin2)
        val jumlah_vaksin3 : TextView = itemView.findViewById(R.id.rekapimun_vaksin3)
        val jumlah_vaksin4 : TextView = itemView.findViewById(R.id.rekapimun_vaksin4)
        val jumlah_vaksin5 : TextView = itemView.findViewById(R.id.rekapimun_vaksin5)
        val jumlah_vaksin6 : TextView = itemView.findViewById(R.id.rekapimun_vaksin6)
        val jumlah_vaksin7 : TextView = itemView.findViewById(R.id.rekapimun_vaksin7)
        val jumlah_vaksin8 : TextView = itemView.findViewById(R.id.rekapimun_vaksin8)
        val jumlah_vaksin9 : TextView = itemView.findViewById(R.id.rekapimun_vaksin9)
        val jumlah_vaksin10 : TextView = itemView.findViewById(R.id.rekapimun_vaksin10)
        val jumlah_vaksin11 : TextView = itemView.findViewById(R.id.rekapimun_vaksin11)
        val jumlah_vaksin12 : TextView = itemView.findViewById(R.id.rekapimun_vaksin12)
        val jumlah_vaksin13 : TextView = itemView.findViewById(R.id.rekapimun_vaksin13)

        init {
            itemView.setOnClickListener { v: View->
                val position: Int = adapterPosition
                Toast.makeText(itemView.context,
                    "Kamu sedang memilih informasi ke ${position +1}",
                    Toast.LENGTH_SHORT).show()
            }
        }


}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_layout_rekapimun,parent,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.jumlah_vaksin1.text = vaksin1[position]
        holder.jumlah_vaksin2.text = vaksin2[position]
        holder.jumlah_vaksin3.text = vaksin3[position]
        holder.jumlah_vaksin4.text = vaksin4[position]
        holder.jumlah_vaksin5.text = vaksin5[position]
        holder.jumlah_vaksin6.text = vaksin6[position]
        holder.jumlah_vaksin7.text = vaksin7[position]
        holder.jumlah_vaksin8.text = vaksin8[position]
        holder.jumlah_vaksin9.text = vaksin9[position]
        holder.jumlah_vaksin10.text = vaksin10[position]
        holder.jumlah_vaksin11.text = vaksin11[position]
        holder.jumlah_vaksin12.text = vaksin12[position]
        holder.jumlah_vaksin13.text = vaksin13[position]
    }

    override fun getItemCount(): Int {
        return vaksin1.size
    }
}