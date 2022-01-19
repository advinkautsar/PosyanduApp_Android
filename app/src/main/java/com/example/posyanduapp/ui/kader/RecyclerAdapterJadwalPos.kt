package com.example.posyanduapp.ui.kader

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.posyanduapp.R

class RecyclerAdapterJadwalPos(private var tanggal_pos: List<String>, private var nama_pos: List<String>, private var waktu_pos: List<String>,
private var alamat_pos: List<String>, private var ket_pos: List<String>):
RecyclerView.Adapter<RecyclerAdapterJadwalPos.ViewHolder>(){

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val tanggal : TextView = itemView.findViewById(R.id.tvjadwal_tanggal)
        val pos : TextView = itemView.findViewById(R.id.tvjadwal_namaposyandu)
        val waktu: TextView = itemView.findViewById(R.id.tvjadwal_waktukegiatan)
        val alamat : TextView = itemView.findViewById(R.id.tvjadwal_alamatkegiatan)
        val ket : TextView = itemView.findViewById(R.id.tvjadwal_keterangan)

        init {
            itemView.setOnClickListener { v: View ->
                val position: Int = adapterPosition
                Toast.makeText(itemView.context,"Kamu sedang memilih riwayat ${position +1}", Toast.LENGTH_SHORT).show()
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_layout_jadwalposyandu,parent,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tanggal.text = tanggal_pos[position]
        holder.pos.text = nama_pos[position]
        holder.waktu.text = waktu_pos[position]
        holder.alamat.text = alamat_pos[position]
        holder.ket.text = ket_pos[position]

    }

    override fun getItemCount(): Int {
        return tanggal_pos.size
    }
}