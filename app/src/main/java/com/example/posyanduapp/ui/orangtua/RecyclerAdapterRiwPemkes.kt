package com.example.posyanduapp.ui.orangtua

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.posyanduapp.R

class RecyclerAdapterRiwPemkes
    (private var tgl: List<String>,
     private var pemkes1: List<String>,
     private var pemkes2: List<String>,
     private var pemkes3: List<String>,
     private var pemkes4: List<String>,
     private var pemkes5: List<String>,
     private var pemkes6: List<String>,
     private var pemkes7: List<String>,
     private var pemkes8: List<String>,
     private var pemkes9: List<String>,
     private var pemkes10: List<String>,
     private var pemkes11: List<String>,
     private var pemkes12: List<String>,
     private var pemkes13: List<String>,
     private var pemkes14: List<String>,
     private var pemkes15: List<String>,)
:RecyclerView.Adapter<RecyclerAdapterRiwPemkes.ViewHolder>(){

    inner class ViewHolder(itemview : View) : RecyclerView.ViewHolder(itemview){
        val tgl_periksa : TextView = itemView.findViewById(R.id.riwayatpemkes_tgl)
        val pemkes_bb : TextView = itemView.findViewById(R.id.riwpemkes_bb)
        val pemkes_tb : TextView = itemView.findViewById(R.id.riwpemkes_PB)
        val pemkes_lk : TextView = itemView.findViewById(R.id.riwpemkes_LK)
        val pemkes_imun1 : TextView = itemView.findViewById(R.id.riwpemkes_imun1)
        val pemkes_imun2 : TextView = itemView.findViewById(R.id.riwpemkes_imun2)
        val pemkes_imun3 : TextView = itemView.findViewById(R.id.riwpemkes_imun3)
        val pemkes_vitAmerah : TextView = itemView.findViewById(R.id.riwpemkes_vitAmerah)
        val pemkes_vitAbiru : TextView = itemView.findViewById(R.id.riwpemkes_vitAbiru)
        val pemkes_oralit : TextView = itemView.findViewById(R.id.riwpemkes_oralit)
        val pemkes_fe1 : TextView = itemView.findViewById(R.id.riwpemkes_Fe1)
        val pemkes_fe2 : TextView = itemView.findViewById(R.id.riwpemkes_Fe2)
        val pemkes_pmt : TextView = itemView.findViewById(R.id.riwpemkes_PMT)
        val pemkes_asi : TextView = itemView.findViewById(R.id.riwpemkes_asi)
        val pemkes_cacing : TextView = itemView.findViewById(R.id.riwpemkes_obatcacing)
        val pemkes_bidan : TextView = itemView.findViewById(R.id.riwpemkes_bidan)

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
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_layout_riwayatpemkes,parent,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tgl_periksa.text = tgl[position]
        holder.pemkes_bb.text = pemkes1[position]
        holder.pemkes_tb.text = pemkes2[position]
        holder.pemkes_lk.text = pemkes3[position]
        holder.pemkes_imun1.text = pemkes4[position]
        holder.pemkes_imun2.text = pemkes5[position]
        holder.pemkes_imun3.text = pemkes6[position]
        holder.pemkes_vitAmerah.text = pemkes7[position]
        holder.pemkes_vitAbiru.text = pemkes8[position]
        holder.pemkes_oralit.text = pemkes9[position]
        holder.pemkes_fe1.text = pemkes10[position]
        holder.pemkes_fe2.text = pemkes11[position]
        holder.pemkes_pmt.text = pemkes12[position]
        holder.pemkes_asi.text = pemkes13[position]
        holder.pemkes_cacing.text = pemkes14[position]
        holder.pemkes_bidan.text = pemkes15[position]
    }

    override fun getItemCount(): Int {
        return tgl.size
    }

}
