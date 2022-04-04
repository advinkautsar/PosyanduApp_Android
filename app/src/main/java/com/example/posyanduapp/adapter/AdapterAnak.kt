package com.example.posyanduapp.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.example.posyanduapp.R
import com.example.posyanduapp.databinding.ItemLayoutAnakBinding
import com.example.posyanduapp.model.ListAnak
import com.example.posyanduapp.ui.bidan.DetailAnakBidan
import com.example.posyanduapp.ui.bidan.RiwayatRujukanPerAnakBidan
import com.example.posyanduapp.ui.bidan.GrafikPertumbuhanActivity
import com.example.posyanduapp.ui.bidan.RiwayatPemKesActivity
import kotlin.collections.ArrayList

class AdapterAnak (

    private val context: Context,
    private val data: ArrayList<ListAnak.Result>,


    ) :
    RecyclerView.Adapter<AdapterAnak.PesananViewHolder>() {

        class PesananViewHolder(var binding: ItemLayoutAnakBinding) :
            RecyclerView.ViewHolder(binding.root) {
            // define itemView from xml

        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PesananViewHolder {

            return PesananViewHolder(
                ItemLayoutAnakBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }

        override fun onBindViewHolder(holder: PesananViewHolder, position: Int) {
            // set current item of result response tarif data
            val currentItem = data[position]
            // set value to the holder view
            holder.binding.tvanakNamanak.text = currentItem.nama_anak
            holder.binding.tvanakNamaibu.text = currentItem.nama_ibu
            holder.binding.tvanakNamaposyandu.text = currentItem.nama_posyandu

            holder.binding.imgPopupmenuanak.setOnClickListener {
                currentItem.nama_anak?.let { it1 ->
                    popUpMenus(it,currentItem.nik_anak.toString(),
                        it1
                    )
                }
            }


//            holder.itemView.setOnClickListener {
//                val intent = Intent(context, DetailAnakActivity::class.java)
//                intent.putExtra("idnya", currentItem.nik_anak.toString())
//                context.startActivity(intent)
//            }

        }

    private fun popUpMenus(itemView: View, nik_anak: String, nama_anak: String) {
        val popupMenus = PopupMenu(context,itemView)
        popupMenus.inflate(R.menu.menu_anak)
        popupMenus.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.menu_detail->{
                    val intent = Intent(itemView.context, DetailAnakBidan::class.java)
                    intent.putExtra("nik", nik_anak)
                    intent.putExtra("nama", nama_anak)
                    itemView.getContext().startActivity(intent);
                    true
                }
                R.id.menu_grafik->{
                    val intent = Intent(itemView.context, GrafikPertumbuhanActivity::class.java)
                    intent.putExtra("nik", nik_anak)
                    intent.putExtra("nama", nama_anak)
                    itemView.getContext().startActivity(intent);
                    true
                }
                R.id.menu_riwayatperiksa->{
                    val intent = Intent(itemView.context, RiwayatPemKesActivity::class.java)
//                    itemView.getContext().startActivity(intent);
                    intent.putExtra("nik", nik_anak)
                    intent.putExtra("nama", nama_anak)
                    context.startActivity(intent)
                    true
                }
                R.id.menu_rujukan->{
                    val intent = Intent(itemView.context, RiwayatRujukanPerAnakBidan::class.java)
                    intent.putExtra("nik", nik_anak)
                    intent.putExtra("nama", nama_anak)
                    itemView.getContext().startActivity(intent);
                    true
                }
                else-> true
            }
        }
        popupMenus.show()
    }

    override fun getItemCount() = data.size

}