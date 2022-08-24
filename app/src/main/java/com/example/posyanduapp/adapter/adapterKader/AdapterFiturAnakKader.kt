package com.example.posyanduapp.adapter.adapterKader

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.example.posyanduapp.R
import com.example.posyanduapp.adapter.AdapterAnak
import com.example.posyanduapp.databinding.ItemLayoutAnakBinding
import com.example.posyanduapp.model.ListAnak
import com.example.posyanduapp.ui.bidan.DetailAnakBidan
import com.example.posyanduapp.ui.bidan.GrafikPertumbuhanActivity
import com.example.posyanduapp.ui.bidan.RiwayatPemKesActivity
import com.example.posyanduapp.ui.bidan.RiwayatRujukanPerAnakBidan
import com.example.posyanduapp.ui.kader.*

class AdapterFiturAnakKader(
    private val context: Context,
    private val data: ArrayList<ListAnak.Result>,
) :
    RecyclerView.Adapter<AdapterFiturAnakKader.ViewHolder>() {
        class ViewHolder(var binding: ItemLayoutAnakBinding) :
                RecyclerView.ViewHolder(binding.root){

                }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return AdapterFiturAnakKader.ViewHolder(
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
                    val intent = Intent(itemView.context, DetailAnakKaderActivity::class.java)
                    intent.putExtra("nik", nik_anak)
                    intent.putExtra("nama", nama_anak)
                    itemView.getContext().startActivity(intent);
                    true
                }
                R.id.menu_grafik->{
                    val intent = Intent(itemView.context, RiwayatStatusGizAnakKaderActivity::class.java)
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
                R.id.menu_riwayatperiksa->{
                    val intent = Intent(itemView.context, RiwayatPemkesKaderActivity::class.java)
//                    itemView.getContext().startActivity(intent);
                    intent.putExtra("nik", nik_anak)
                    intent.putExtra("nama", nama_anak)
                    context.startActivity(intent)
                    true
                }
                R.id.menu_rujukan->{
                    val intent = Intent(itemView.context, RiwayatRujukanAnakKaderActivity::class.java)
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

