package com.example.posyanduapp.ui.kader

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.posyanduapp.R

class RecyclerAdapterOrtu (private var Nortu: List<String>, private var Npos: List<String>) :
RecyclerView.Adapter<RecyclerAdapterOrtu.ViewHolder>(){

    private lateinit var mlistener : onItemClickListener

    interface onItemClickListener{
        fun onItemClick(position: Int)
    }
    fun setOnItemClicklistener(listener: onItemClickListener){
        mlistener = listener
    }

    inner class ViewHolder(itemView: View, listener: onItemClickListener) : RecyclerView.ViewHolder(itemView){
        val namaOrtu : TextView = itemView.findViewById(R.id.tvorangtua_nama)
        val namaPos : TextView = itemView.findViewById(R.id.tvorangtua_namaPosyandu)

        init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_layout_orangtua,parent,false)
        return ViewHolder(v,mlistener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.namaOrtu.text = Nortu[position]
        holder.namaPos.text = Npos[position]
    }

    override fun getItemCount(): Int {
        return Nortu.size
    }

}