package com.example.posyanduapp.adapter.adapterOrtu

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.posyanduapp.Model.ListProfilOrtu
import com.example.posyanduapp.adapter.AdapterAnak
import com.example.posyanduapp.databinding.ItemLayoutAnakBinding
import com.example.posyanduapp.databinding.ItemLayoutOrangtuaBinding
import com.example.posyanduapp.model.ListAnak
import com.example.posyanduapp.ui.kader.ReadProfilOrtuActivity
import com.example.posyanduapp.ui.orangtua.ProfilOrtuActivity

class AdapterListOrtu(
    private val context: Context,
    private val data: ArrayList<ListProfilOrtu.Result>,
) :
    RecyclerView.Adapter<AdapterListOrtu.PesananViewHolder>() {

    class PesananViewHolder(var binding:ItemLayoutOrangtuaBinding) :
        RecyclerView.ViewHolder(binding.root) {
        // define itemView from xml
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PesananViewHolder {
        return AdapterListOrtu.PesananViewHolder(
            ItemLayoutOrangtuaBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PesananViewHolder, position: Int) {
        val currentItem = data[position]
        // set value to the holder view
        holder.binding.tvorangtuaNama.text = currentItem.nama_ibu
        holder.binding.tvorangtuaNamaPosyandu.text = currentItem.nama_posyandu

        holder.itemView.setOnClickListener {
                val intent = Intent(context, ReadProfilOrtuActivity::class.java)
                intent.putExtra("idnya", currentItem.id.toString())
                context.startActivity(intent)
            }
    }

    override fun getItemCount() = data.size
    }

