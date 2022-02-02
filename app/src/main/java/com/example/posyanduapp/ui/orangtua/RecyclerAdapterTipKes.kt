package com.example.posyanduapp.ui.orangtua

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.posyanduapp.R
import com.example.posyanduapp.ui.kader.RecyclerAdapterOrtu
import com.google.android.material.imageview.ShapeableImageView

class RecyclerAdapterTipKes(private val tiplist : ArrayList<Tipkes>) :
    RecyclerView.Adapter<RecyclerAdapterTipKes.ViewHolder>() {

    private lateinit var mlistener : onItemClickListener

    interface onItemClickListener{
        fun onItemClick(position: Int)
    }
    fun setOnItemClicklistener(listener: onItemClickListener){
        mlistener = listener
    }


    inner class ViewHolder(itemView : View, listener: RecyclerAdapterTipKes.onItemClickListener) : RecyclerView.ViewHolder(itemView){
        val imagetips : ShapeableImageView = itemView.findViewById(R.id.tipkes_gambar)
        val titletips : TextView = itemView.findViewById(R.id.tipkes_judul)

        init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_layout_tipkes,parent,false)
        return ViewHolder(v,mlistener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = tiplist[position]
        holder.imagetips.setImageResource(currentItem.gambar_tip)
        holder.titletips.text = currentItem.judul_tip
    }

    override fun getItemCount(): Int {
        return tiplist.size
    }
}