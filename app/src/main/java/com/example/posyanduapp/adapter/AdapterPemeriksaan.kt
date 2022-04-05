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
import com.example.posyanduapp.databinding.ItemLayoutRiwayatpemkesBinding
import com.example.posyanduapp.databinding.ItemLayoutRujukanBinding
import com.example.posyanduapp.model.ListPemeriksaan
import com.example.posyanduapp.model.ListRujukan
import com.example.posyanduapp.model.ResponsePesan
import com.example.posyanduapp.retrofit.ApiService
import com.example.posyanduapp.ui.bidan.BerandabidanActivity
import com.example.posyanduapp.ui.bidan.PemeriksaanKesehatanUpdateActivity
import com.example.posyanduapp.ui.bidan.RujukanUpdateActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class AdapterPemeriksaan (
    private val context: Context,
    private val data: ArrayList<ListPemeriksaan.Result>
) :
    RecyclerView.Adapter<AdapterPemeriksaan.PesananViewHolder>() {
    class PesananViewHolder(var binding: ItemLayoutRiwayatpemkesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        // define itemView from xml

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PesananViewHolder {

        return PesananViewHolder(
            ItemLayoutRiwayatpemkesBinding.inflate(
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
        holder.binding.riwayatpemkesTgl.text = currentItem.tanggal_pemeriksaan
        holder.binding.riwpemkesAsi.text = currentItem.asi_ekslusif
        holder.binding.riwpemkesFe1.text = currentItem.Fe_1
        holder.binding.riwpemkesFe2.text = currentItem.Fe_2
        holder.binding.riwpemkesImun1.text = currentItem.imun1
        holder.binding.riwpemkesImun2.text = currentItem.imun2
        holder.binding.riwpemkesImun3.text = currentItem.imun3
        holder.binding.riwpemkesVitAbiru.text = currentItem.vitA_biru
        holder.binding.riwpemkesVitAmerah.text = currentItem.vitA_merah
        holder.binding.riwpemkesOralit.text = currentItem.oralit
        holder.binding.riwpemkesObatcacing.text = currentItem.obat_cacing
        holder.binding.riwpemkesBidan.text = currentItem.nama_bidan



        holder.itemView.setOnClickListener {
            val intent = Intent(context, PemeriksaanKesehatanUpdateActivity::class.java)
            intent.putExtra("idpemkes", currentItem.id.toString())
            context.startActivity(intent)
        }

        holder.itemView.setOnLongClickListener { v: View? ->
            AlertDialog.Builder(holder.itemView.context)
                .setMessage("Ingin menghapus  "+ "Pemeriksaan Ini ?")
                .setCancelable(false)
                .setPositiveButton(
                    "Ya"
                ) { dialog: DialogInterface?, which: Int ->
                    currentItem.id?.let {
                        ApiService.endpoint.getdeletepemkes(
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

    fun convertFormat(inputDate: String?): String? {
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS")
        var date: Date? = null
        try {
            date = simpleDateFormat.parse(inputDate)
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        if (date == null) {
            return ""
        }
        val convetDateFormat = SimpleDateFormat("dd MMM yyyy")
        return convetDateFormat.format(date)
    }



    override fun getItemCount() = data.size
}