package com.example.posyanduapp.adapter

import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.posyanduapp.databinding.ItemLayoutRujukanBinding
import com.example.posyanduapp.model.ListRujukan
import com.example.posyanduapp.model.ResponsePesan
import com.example.posyanduapp.retrofit.ApiService
import com.example.posyanduapp.ui.bidan.BerandabidanActivity
import com.example.posyanduapp.ui.bidan.RujukanUpdateActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AdapterRujukanAnak (
    private val context: Context,
    private val data: ArrayList<ListRujukan.Result>
) :
    RecyclerView.Adapter<AdapterRujukanAnak.PesananViewHolder>() {
    class PesananViewHolder(var binding: ItemLayoutRujukanBinding) :
        RecyclerView.ViewHolder(binding.root) {
        // define itemView from xml

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PesananViewHolder {

        return PesananViewHolder(
            ItemLayoutRujukanBinding.inflate(
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
        holder.binding.tvrujukanNamaanak.text = currentItem.nama_anak
        holder.binding.tvrujukanNamaposyandu.text = currentItem.nama_posyandu
        holder.binding.tvrujukanNamabidan.text = currentItem.nama_bidan
        holder.binding.tvrujukanPuskesmas.text = currentItem.nama_puskesmas
        holder.binding.tvrujukanNamapenyakit.text = currentItem.keluhan_anak
        holder.binding.tvrujukanTanggal.text = currentItem.tanggal_rujukan



        holder.itemView.setOnClickListener {
            val intent = Intent(context, RujukanUpdateActivity::class.java)
            intent.putExtra("idrujukan", currentItem.id.toString())
            context.startActivity(intent)
        }

        holder.itemView.setOnLongClickListener { v: View? ->
            AlertDialog.Builder(holder.itemView.context)
                .setMessage("Ingin menghapus  "+ "Rujukan Ini ?")
                .setCancelable(false)
                .setPositiveButton(
                    "Ya"
                ) { dialog: DialogInterface?, which: Int ->
                    currentItem.id?.let {
                        ApiService.endpoint.getdeleterujukan(
                            it.toInt()

                        ).enqueue(object : Callback<ResponsePesan> {
                            override fun onResponse(
                                call: Call<ResponsePesan>,
                                response: Response<ResponsePesan>
                            ) {
                                val status = response.body()?.status
                                val pesanan = response.body()?.pesan

                                if (status!!) {
                                    Toast.makeText(context, pesanan, Toast.LENGTH_LONG).show()
                                    val i = Intent(context, BerandabidanActivity::class.java)
                                    val activity = context as Activity
                                    activity.startActivity(i)
                                    activity.finish()
                //                    this@KelolajadwalimunisasiActivity.fragmentManager?.popBackStack()

                                } else {
                                    Toast.makeText(context, pesanan, Toast.LENGTH_LONG).show()
                                }
                            }

                            override fun onFailure(call: Call<ResponsePesan>, t: Throwable) {
                                t.printStackTrace()
                                Toast.makeText(context, "Kesalahan tidak terduga!", Toast.LENGTH_LONG).show()

                            }

                        })
                    }

                }
                .setNegativeButton(
                    "Tidak"
                ) { dialog: DialogInterface, which: Int -> dialog.cancel() }
                .show()
            false
        }


    }



    override fun getItemCount() = data.size
}