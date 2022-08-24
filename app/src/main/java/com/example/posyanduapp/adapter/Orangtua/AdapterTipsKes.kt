package com.example.posyanduapp.adapter.Orangtua

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.posyanduapp.Model.ListTipsKes
import com.example.posyanduapp.Model.Orangtua
import com.example.posyanduapp.databinding.ItemLayoutTipkesBinding
import com.example.posyanduapp.ui.bidan.RujukanUpdateActivity
import com.example.posyanduapp.ui.orangtua.ContentTipkes
import com.example.posyanduapp.ui.orangtua.Tipkes

class AdapterTipsKes(
    private val context: Context,
    private val data: ArrayList<ListTipsKes.Result>,
) :
    RecyclerView.Adapter<AdapterTipsKes.ViewHolder>(){
        class ViewHolder(var binding:ItemLayoutTipkesBinding) :
            RecyclerView.ViewHolder(binding.root){

        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return AdapterTipsKes.ViewHolder(
            ItemLayoutTipkesBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // set current item of result response tarif data
        val currentItem = data[position]
        // set value to the holder view
        holder.binding.tipkesJudul.text = currentItem.judul_tips

        holder.itemView.setOnClickListener {
            val intent = Intent(context, ContentTipkes::class.java)
            intent.putExtra("id_tipkes", currentItem.id.toString())
            context.startActivity(intent)
        }
    }

    override fun getItemCount()= data.size
}
