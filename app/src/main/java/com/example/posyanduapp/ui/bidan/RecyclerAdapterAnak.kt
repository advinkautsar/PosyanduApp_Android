package com.example.posyanduapp.ui.bidan

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.posyanduapp.R
import com.example.posyanduapp.ui.orangtua.*


class RecyclerAdapterAnak (val c : Context, private var anak: List<String>, private var ibu: List<String>, private var pos: List<String>) :
RecyclerView.Adapter<RecyclerAdapterAnak.ViewHolder>(){

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val nama_anak : TextView = itemView.findViewById(R.id.tvanak_namanak)
        val nama_ibu : TextView = itemView.findViewById(R.id.tvanak_namaibu)
        val nama_pos : TextView = itemView.findViewById(R.id.tvanak_namaposyandu)
        val menu : ImageView = itemView.findViewById(R.id.img_popupmenuanak)

        init {
            menu.setOnClickListener {
                popUpMenus(it)
            }
        }

        private fun popUpMenus( itemView: View) {
            val popupMenus = PopupMenu(c,itemView)
            popupMenus.inflate(R.menu.menu_anak)
            popupMenus.setOnMenuItemClickListener {
                when(it.itemId){
                    R.id.menu_detail->{
                        val intent = Intent(itemView.context, DetailAnakActivity::class.java)
                        itemView.getContext().startActivity(intent);
                        true
                    }
                    R.id.menu_grafik->{
                        val intent = Intent(itemView.context, GrafikPertumbuhanActivity::class.java)
                        itemView.getContext().startActivity(intent);
                        true
                    }
                    R.id.menu_riwayatperiksa->{
                        val intent = Intent(itemView.context, RiwayatPemKesActivity::class.java)
                        itemView.getContext().startActivity(intent);
                        true
                    }
                    R.id.menu_rujukan->{
                        val intent = Intent(itemView.context, RiwayatRujukAnakActivity::class.java)
                        itemView.getContext().startActivity(intent);
                        true
                    }
                    else-> true
                }
            }
            popupMenus.show()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_layout_anak,parent,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.nama_anak.text = anak[position]
        holder.nama_ibu.text = ibu[position]
        holder.nama_pos.text = pos[position]

    }

    override fun getItemCount(): Int {
        return anak.size
    }
}