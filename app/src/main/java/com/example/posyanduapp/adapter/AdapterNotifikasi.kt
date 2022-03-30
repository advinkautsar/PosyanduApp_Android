package com.example.posyanduapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.posyanduapp.databinding.ItemLayoutNotifikasiBinding
import com.example.posyanduapp.model.ListNotifikasi
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class AdapterNotifikasi (
    private val context: Context,
    private val data: ArrayList<ListNotifikasi.Result>
) :
    RecyclerView.Adapter<AdapterNotifikasi.NotifViewHolder>() {
    class NotifViewHolder(var binding: ItemLayoutNotifikasiBinding) :
        RecyclerView.ViewHolder(binding.root) {
        // define itemView from xml

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotifViewHolder {

        return NotifViewHolder(
            ItemLayoutNotifikasiBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: NotifViewHolder, position: Int) {
        // set current item of result response tarif data
        val currentItem = data[position]
        // set value to the holder view
        holder.binding.txtContentnotification.text = currentItem.key1
        holder.binding.txtTitlenotification.text =currentItem.key2

    }
    fun convertFormat(inputDate: String?): String? {
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
        var date: Date? = null
        try {
            date = simpleDateFormat.parse(inputDate)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        if (date == null) {
            return ""
        }
        val convetDateFormat = SimpleDateFormat("dd-MMMM-yyyy hh:mm")
        return convetDateFormat.format(date)
    }



    override fun getItemCount() = data.size
}