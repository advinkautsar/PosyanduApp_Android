package com.example.posyanduapp.adapter.Orangtua

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.example.posyanduapp.Model.ListAnakOrtu
import com.example.posyanduapp.Model.Orangtua
import com.example.posyanduapp.R
import com.example.posyanduapp.databinding.ItemLayoutAnakBinding
import com.example.posyanduapp.ui.kader.*
import com.example.posyanduapp.ui.orangtua.*

class AdapterFiturAnakOrangtua(
    private val context: Context,
    private val data: ArrayList<Orangtua.Result>,
) :
    RecyclerView.Adapter<AdapterFiturAnakOrangtua.ViewHolder>(){
        class ViewHolder(var binding: ItemLayoutAnakBinding) :
            RecyclerView.ViewHolder(binding.root){

            }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return AdapterFiturAnakOrangtua.ViewHolder(
            ItemLayoutAnakBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
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
    }
    private fun popUpMenus(itemView: View, nik_anak: String, nama_anak: String) {
        val popupMenus = PopupMenu(context,itemView)
        popupMenus.inflate(R.menu.menu_anak)
        popupMenus.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.menu_detail->{
                    val intent = Intent(itemView.context, DetailAnakOrtuActivity::class.java)
                    intent.putExtra("nik", nik_anak)
                    intent.putExtra("nama", nama_anak)
                    itemView.getContext().startActivity(intent);
                    true
                }
                R.id.menu_grafik->{
                    val intent = Intent(itemView.context, RiwayatStatusGiziAnakOrtuActivity::class.java)
                    intent.putExtra("nik", nik_anak)
                    intent.putExtra("nama", nama_anak)
                    itemView.getContext().startActivity(intent);
                    true
                }
                R.id.menu_riwayatperiksa->{
                    val intent = Intent(itemView.context, RiwayatPemkesAnakOrtuActivity::class.java)
//                    itemView.getContext().startActivity(intent);
                    intent.putExtra("nik", nik_anak)
                    intent.putExtra("nama", nama_anak)
                    context.startActivity(intent)
                    true
                }
                R.id.menu_rujukan->{
                    val intent = Intent(itemView.context, RiwayatRujukAnakOrangtuaActivity::class.java)
                    intent.putExtra("nik", nik_anak)
                    intent.putExtra("nama", nama_anak)
                    itemView.getContext().startActivity(intent);
                    true
                }
                R.id.grafik_bb_tb->{
                    val intent = Intent(itemView.context, Grafik_BB_TB::class.java)
                    intent.putExtra("nik", nik_anak)
                    intent.putExtra("nama", nama_anak)
                    itemView.getContext().startActivity(intent);
                    true
                }
                R.id.grafik_bb_pb->{
                    val intent = Intent(itemView.context, Grafik_BB_PB::class.java)
                    intent.putExtra("nik", nik_anak)
                    intent.putExtra("nama", nama_anak)
                    itemView.getContext().startActivity(intent);
                    true
                }
                R.id.grafik_bb_u->{
                    val intent = Intent(itemView.context, Grafik_BB_U::class.java)
                    intent.putExtra("nik", nik_anak)
                    intent.putExtra("nama", nama_anak)
                    itemView.getContext().startActivity(intent);
                    true
                }
                R.id.grafik_tb_u->{
                    val intent = Intent(itemView.context, Grafik_TB_U::class.java)
                    intent.putExtra("nik", nik_anak)
                    intent.putExtra("nama", nama_anak)
                    itemView.getContext().startActivity(intent);
                    true
                }
                R.id.grafik_pb_u->{
                    val intent = Intent(itemView.context, Grafik_PB_U::class.java)
                    intent.putExtra("nik", nik_anak)
                    intent.putExtra("nama", nama_anak)
                    itemView.getContext().startActivity(intent);
                    true
                }
                R.id.grafik_lk_u->{
                    val intent = Intent(itemView.context, Grafik_LK_U::class.java)
                    intent.putExtra("nik", nik_anak)
                    intent.putExtra("nama", nama_anak)
                    itemView.getContext().startActivity(intent);
                    true
                }
                R.id.grafik_imt_u->{
                    val intent = Intent(itemView.context, Grafik_IMT_U::class.java)
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