package com.example.posyanduapp.Model

import com.example.posyanduapp.model.ListAnak

class ListProfilOrtu(
    val data: ArrayList<Result>?,
    val status: String,
    val sukses :Boolean,
    val message: String,
){
    data class Result(
        val id: String?,
        val user_id: String?,
        val posyandu_id: String?,
        val desa_kelurahan_id: String?,
        val kecamatan_id: String?,
        val nik_ayah: String?,
        val nama_ayah: String?,
        val nik_ibu: String?,
        val nama_ibu: String?,
        val alamat: String?,
        val rt: String?,
        val rw: String?,
        val status_persetujuan: String?,
        val nama_posyandu: String?,

    ){
        override fun toString(): String {
            return nama_ibu!!
        }
    }

}