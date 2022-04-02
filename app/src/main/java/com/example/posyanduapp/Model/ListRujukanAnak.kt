package com.example.posyanduapp.Model

import com.example.posyanduapp.model.ListRujukan

class ListRujukanAnak (
    val data: ArrayList<Result>?,
    val status: String,
    val sukses :Boolean,
    val message: String,
){
    data class Result(
        val id: String?,
        val nik_anak: String?,
        val nama_bidan: String?,
        val keluhan_anak: String?,
        val nama_puskesmas: String?,
        val nama_posyandu :String?,
        val nama_anak :String?,
        val nama :String?,
        val tanggal_rujukan: String?,
        val updatedAt: String?
    )
}