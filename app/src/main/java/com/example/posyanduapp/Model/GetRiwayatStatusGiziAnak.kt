package com.example.posyanduapp.Model

import com.example.posyanduapp.model.GetPemeriksaan

class GetRiwayatStatusGiziAnak(
    val data: ArrayList<Result>?,
    val status: String,
    val sukses :Boolean,
    val message: String,
) {

    data class Result(
        val id: String?,
        val nik_anak: String?,
        val berat_badan: String?,
        val tinggi_badan: String?,
        val lingkar_kepala: String?,
        val status_bb_u: String?,
        val status_tb_u: String?,
        val status_lk_u: String?,
        val status_bb_tb: String?,
        val status_imt_u: String?,
        val created_at: String?,



        )
}